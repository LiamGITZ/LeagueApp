package edu.illinois.finalproject.PlayerGuides;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import net.rithms.riot.api.endpoints.static_data.dto.Item;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import edu.illinois.finalproject.LolConstants;
import edu.illinois.finalproject.R;

public class CreateGuide extends AppCompatActivity {
  private int currentSummonerArrayPos = 0;
  private String[] summonersChosen = new String[2];

  // search view variables
  private android.support.v7.widget.SearchView searchView;
  private MyExpandableListAdapter listAdapter;
  private ExpandableListView myList;
  private ArrayList<ParentRow> parentList = new ArrayList<ParentRow>();
  private ArrayList<ParentRow> filteredParentList = new ArrayList<ParentRow>();


  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_create_guide);
    Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
    setSupportActionBar(toolbar);

    // create item selector
    // code from:
    // http://robinsonprogramming.com/tuts/
    setContentView(R.layout.activity_create_guide);
    searchView = (android.support.v7.widget.SearchView) findViewById(R.id.action_search2);
    searchView.setQueryHint("Enter Search");
    myList = (ExpandableListView) findViewById(R.id.expandableListView2);

    searchView.setOnQueryTextListener(new android.support.v7.widget.SearchView.OnQueryTextListener() {
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
    filteredParentList = new ArrayList<ParentRow>();

    // The app will crash if display list is not called here.
    displayList();

    // This expands the list.
    expandAll();



    // getIntent() is a method from the started activity for selected champion
    final String championName = getIntent().getExtras().getString("key","defaultKey");
    ImageView championIcon = findViewById(R.id.championGuidePicture);
    if (championName!=null){
      Picasso.with(this)
              .load("http://ddragon.leagueoflegends.com/cdn/7.23.1/img/champion/" +
                      championName + ".png")
              .into(championIcon);
    }

    // summoner selecting
    final ImageView smite = findViewById(R.id.smiteSelect);
    final ImageView flash = findViewById(R.id.flashSelect);
    final ImageView heal = findViewById(R.id.healSelect);
    final ImageView exhaust = findViewById(R.id.exhuastSelect);
    final ImageView teleport = findViewById(R.id.teleportSelect);
    final ImageView ignite = findViewById(R.id.igniteSelect);
    final ImageView cleanse = findViewById(R.id.cleanseSelect);
    final ImageView ghost = findViewById(R.id.ghostSelect);
    final ImageView barrier = findViewById(R.id.barrierSelect);
    final Map<String, ImageView> summonerMap = new HashMap<String, ImageView>();
    summonerMap.put("Smite", smite);
    summonerMap.put("Flash", flash);
    summonerMap.put("Heal", heal);
    summonerMap.put("Exhaust", exhaust);
    summonerMap.put("Teleport", teleport);
    summonerMap.put("Ignite", ignite);
    summonerMap.put("Cleanse", cleanse);
    summonerMap.put("Ghost", ghost);
    summonerMap.put("Barrier", barrier);

    barrier.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        Drawable highlight = getResources().getDrawable(R.drawable.hightlight);
        if (summonersChosen[currentSummonerArrayPos] != null) {
          ImageView replace = summonerMap.get(summonersChosen[currentSummonerArrayPos]);
          replace.setBackground(null);
        }
        barrier.setBackground(highlight);
        summonersChosen[currentSummonerArrayPos] = "Barrier";
        if (currentSummonerArrayPos == 0) {
          currentSummonerArrayPos = 1;
        } else {
          currentSummonerArrayPos = 0;
        }
      }
    });

    ghost.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        Drawable highlight = getResources().getDrawable(R.drawable.hightlight);
        if (summonersChosen[currentSummonerArrayPos] != null) {
          ImageView replace = summonerMap.get(summonersChosen[currentSummonerArrayPos]);
          replace.setBackground(null);
        }
        ghost.setBackground(highlight);
        summonersChosen[currentSummonerArrayPos] = "Ghost";
        if (currentSummonerArrayPos == 0) {
          currentSummonerArrayPos = 1;
        } else {
          currentSummonerArrayPos = 0;
        }
      }
    });

    cleanse.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        Drawable highlight = getResources().getDrawable(R.drawable.hightlight);
        if (summonersChosen[currentSummonerArrayPos] != null) {
          ImageView replace = summonerMap.get(summonersChosen[currentSummonerArrayPos]);
          replace.setBackground(null);
        }
        cleanse.setBackground(highlight);
        summonersChosen[currentSummonerArrayPos] = "Cleanse";
        if (currentSummonerArrayPos == 0) {
          currentSummonerArrayPos = 1;
        } else {
          currentSummonerArrayPos = 0;
        }
      }
    });

    ignite.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        Drawable highlight = getResources().getDrawable(R.drawable.hightlight);
        if (summonersChosen[currentSummonerArrayPos] != null) {
          ImageView replace = summonerMap.get(summonersChosen[currentSummonerArrayPos]);
          replace.setBackground(null);
        }
        ignite.setBackground(highlight);
        summonersChosen[currentSummonerArrayPos] = "Ignite";
        if (currentSummonerArrayPos == 0) {
          currentSummonerArrayPos = 1;
        } else {
          currentSummonerArrayPos = 0;
        }
      }
    });

    teleport.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        Drawable highlight = getResources().getDrawable(R.drawable.hightlight);
        if (summonersChosen[currentSummonerArrayPos] != null) {
          ImageView replace = summonerMap.get(summonersChosen[currentSummonerArrayPos]);
          replace.setBackground(null);
        }
        teleport.setBackground(highlight);
        summonersChosen[currentSummonerArrayPos] = "Teleport";
        if (currentSummonerArrayPos == 0) {
          currentSummonerArrayPos = 1;
        } else {
          currentSummonerArrayPos = 0;
        }
      }
    });

    exhaust.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        Drawable highlight = getResources().getDrawable(R.drawable.hightlight);
        if (summonersChosen[currentSummonerArrayPos] != null) {
          ImageView replace = summonerMap.get(summonersChosen[currentSummonerArrayPos]);
          replace.setBackground(null);
        }
        exhaust.setBackground(highlight);
        summonersChosen[currentSummonerArrayPos] = "Exhaust";
        if (currentSummonerArrayPos == 0) {
          currentSummonerArrayPos = 1;
        } else {
          currentSummonerArrayPos = 0;
        }
      }
    });

    heal.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        Drawable highlight = getResources().getDrawable(R.drawable.hightlight);
        if (summonersChosen[currentSummonerArrayPos] != null) {
          ImageView replace = summonerMap.get(summonersChosen[currentSummonerArrayPos]);
          replace.setBackground(null);
        }
        heal.setBackground(highlight);
        summonersChosen[currentSummonerArrayPos] = "Heal";
        if (currentSummonerArrayPos == 0) {
          currentSummonerArrayPos = 1;
        } else {
          currentSummonerArrayPos = 0;
        }
      }
    });

    flash.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        Drawable highlight = getResources().getDrawable(R.drawable.hightlight);
        if (summonersChosen[currentSummonerArrayPos] != null) {
          ImageView replace = summonerMap.get(summonersChosen[currentSummonerArrayPos]);
          replace.setBackground(null);
        }
        flash.setBackground(highlight);
        summonersChosen[currentSummonerArrayPos] = "Flash";
        if (currentSummonerArrayPos == 0) {
          currentSummonerArrayPos = 1;
        } else {
          currentSummonerArrayPos = 0;
        }
      }
    });

    smite.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        Drawable highlight = getResources().getDrawable(R.drawable.hightlight);
        if (summonersChosen[currentSummonerArrayPos] != null) {
          ImageView replace = summonerMap.get(summonersChosen[currentSummonerArrayPos]);
          replace.setBackground(null);
        }
        smite.setBackground(highlight);
        summonersChosen[currentSummonerArrayPos] = "Smite";
        if (currentSummonerArrayPos == 0) {
          currentSummonerArrayPos = 1;
        } else {
          currentSummonerArrayPos = 0;
        }
      }
    });

    // create guide
    final EditText guideNameView = findViewById(R.id.guideName);
    final EditText guideIntroductionView = findViewById(R.id.introduction);
    final LinearLayout startingItemsLayout = findViewById(R.id.startingItemsList);

    final Context context = this;
    Button createGuide = findViewById(R.id.Create_Guides_Button);
    createGuide.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        String guideName = guideNameView.getText().toString();
        String guideIntroduction = guideIntroductionView.getText().toString();
        List<String> summoners = Arrays.asList(summonersChosen);

        List<String> startingItems = new ArrayList<>();
        // getting starting items from layout
        final int childCount = startingItemsLayout.getChildCount();
        for (int i = 1; i < childCount; i++) {
          View v = startingItemsLayout.getChildAt(i);
          startingItems.add(String.valueOf(v.getTag()));
        }

        Guide champGuide = new Guide();
        champGuide.setTitle(guideName);
        champGuide.setIntroduction(guideIntroduction);
        champGuide.setSummoners(summoners);
        champGuide.setStartingItems(startingItems);
        champGuide.setUser(PlayerGuides.user.getUid());

        // push to firebase
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference guideRef = database.getReference("champions/"+championName+"/guides/");
        String guideId = guideRef.push().getKey();
        guideRef.push().setValue(champGuide);
        // add guide to User
        DatabaseReference userRef = database.getReference("users/"+PlayerGuides.user.getUid()+"/");
        userRef.push().setValue(guideId);


        Intent intent = new Intent(context, PlayerGuides.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.putExtra("EXIT", true);
        startActivity(intent);
      }
    });

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

    myList = (ExpandableListView) findViewById(R.id.expandableListView2);
    listAdapter = new MyExpandableListAdapter(this, parentList , null);

    myList.setAdapter(listAdapter);
  }
}



