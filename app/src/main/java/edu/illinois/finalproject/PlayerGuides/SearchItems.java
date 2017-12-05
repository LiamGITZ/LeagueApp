package edu.illinois.finalproject.PlayerGuides;

import android.app.SearchManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ExpandableListView;
import android.support.v7.widget.SearchView;

import net.rithms.riot.api.endpoints.static_data.dto.Item;

import java.util.ArrayList;

import edu.illinois.finalproject.LolConstants;
import edu.illinois.finalproject.R;

public class SearchItems extends AppCompatActivity {
  private SearchManager searchManager;
  private android.support.v7.widget.SearchView searchView;
  private MyExpandableListAdapter listAdapter;
  private ExpandableListView myList;
  private ArrayList<ParentRow> parentList = new ArrayList<ParentRow>();
  private ArrayList<ParentRow> showTheseParentList = new ArrayList<ParentRow>();
  private MenuItem searchItem;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_search_items);
    searchView = (SearchView) findViewById(R.id.action_search);
    searchView.setQueryHint("Enter Search");
    myList = (ExpandableListView) findViewById(R.id.expandableListView);

    searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
      @Override
      public boolean onQueryTextSubmit(String query) {
//        List<ItemObject> dictionaryObject = databaseObject.searchDictionaryWords(query);
//        listAdapter = new MyExpandableListAdapter(this, dictionaryObject);
        listAdapter.filterData(query);
        expandAll();
        myList.setAdapter(listAdapter);
        myList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
          @Override
          public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
          }
        });
        return true;
      }

      @Override
      public boolean onQueryTextChange(String newText) {
        expandAll();
        return false;
      }
    });

    parentList = new ArrayList<ParentRow>();
    showTheseParentList = new ArrayList<ParentRow>();

    // The app will crash if display list is not called here.
    displayList();

    // This expands the list.
    expandAll();
  }

  private void loadData() {
    ArrayList<ChildRow> childRows = new ArrayList<ChildRow>();
    ParentRow parentRow = null;

    for (Item i : LolConstants.itemList.getData().values()){
      childRows.add(new ChildRow(i.getId(), i.getName()));
    }
    parentRow = new ParentRow("Items", childRows);
    parentList.add(parentRow);

//    childRows.add(new ChildRow(R.drawable.exhaust
//            , "Lorem ipsum dolor sit amet, consectetur adipiscing elit."));
//    childRows.add(new ChildRow(R.mipmap.exhaust
//            , "Sit Fido, sit."));
//    parentRow = new ParentRow("First Group", childRows);
//    parentList.add(parentRow);
//
//    childRows = new ArrayList<ChildRow>();
//    childRows.add(new ChildRow(R.mipmap.exhaust
//            , "Fido is the name of my dog."));
//    childRows.add(new ChildRow(R.mipmap.exhaust
//            , "Add two plus two."));
//    parentRow = new ParentRow("Second Group", childRows);
//    parentList.add(parentRow);

  }

  private void expandAll() {
    int count = listAdapter.getGroupCount();
    for (int i = 0; i < count; i++) {
      myList.expandGroup(i);
    } //end for (int i = 0; i < count; i++)
  }

  private void displayList() {
    loadData();

    myList = (ExpandableListView) findViewById(R.id.expandableListView);
    listAdapter = new MyExpandableListAdapter(this, parentList);

    myList.setAdapter(listAdapter);
  }
}

//  @Override
//  public boolean onCreateOptionsMenu(Menu menu) {
//    // Inflate the menu; this adds items to the action bar if it is present.
//    getMenuInflater().inflate(R.menu.menu_main, menu);
//    searchItem = menu.findItem(R.id.action_search);
//    searchView = (SearchView) MenuItemCompat.getActionView(searchItem);
//    searchView.setSearchableInfo
//            (searchManager.getSearchableInfo(getComponentName()));
//    searchView.setIconifiedByDefault(false);
//    searchView.setOnQueryTextListener(this);
//    searchView.setOnCloseListener(this);
//    searchView.requestFocus();
//
//    return true;
//  }

//  @Override
//  public boolean onOptionsItemSelected(MenuItem item) {
//    // Handle action bar item clicks here. The action bar will
//    // automatically handle clicks on the Home/Up button, so long
//    // as you specify a parent activity in AndroidManifest.xml.
//    int id = item.getItemId();
//
//    //noinspection SimplifiableIfStatement
//    if (id == R.id.action_settings) {
//      return true;
//    }
//
//    return super.onOptionsItemSelected(item);
//  }
//
//
//  @Override
//  public boolean onClose() {
//    listAdapter.filterData("");
//    expandAll();
//    return false;
//  }
//
//  @Override
//  public boolean onQueryTextSubmit(String query) {
//    listAdapter.filterData(query);
//    expandAll();
//    return false;
//  }
//
//  @Override
//  public boolean onQueryTextChange(String newText) {
//    listAdapter.filterData(newText);
//    expandAll();
//    return false;
//  }
//}
