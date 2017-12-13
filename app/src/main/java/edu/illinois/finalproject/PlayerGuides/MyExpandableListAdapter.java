package edu.illinois.finalproject.PlayerGuides;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import edu.illinois.finalproject.R;

/**
 * Created by user on 2/27/16.
 */
public class MyExpandableListAdapter extends BaseExpandableListAdapter {

    private Context context;
    private ArrayList<ParentRow> parentRowList;
    private ArrayList<ParentRow> originalList;
    private String createOrView;

    public MyExpandableListAdapter(Context context
            , ArrayList<ParentRow> originalList, String createOrView) {
        this.context = context;
        this.parentRowList = new ArrayList<>();
        this.parentRowList.addAll(originalList);
        this.originalList = new ArrayList<>();
        this.originalList.addAll(originalList);
        this.createOrView = createOrView;
    }

    @Override
    public int getGroupCount() {
        return parentRowList.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return parentRowList.get(groupPosition).getChildList().size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return parentRowList.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return parentRowList.get(groupPosition).getChildList().get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        ParentRow parentRow = (ParentRow) getGroup(groupPosition);

        if (convertView == null) {
            LayoutInflater layoutInflater = (LayoutInflater)
                    context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.parent_row, null);
        }

        TextView heading = (TextView) convertView.findViewById(R.id.parent_text);

        heading.setText(parentRow.getName().trim());
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        final ChildRow childRow = (ChildRow) getChild(groupPosition, childPosition);
        if (convertView == null) {
            LayoutInflater layoutInflater = (LayoutInflater)
                    context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.child_row, null);
        }

        final ImageView childIcon = (ImageView) convertView.findViewById(R.id.child_icon);
//        childIcon.setImageResource(childRow.getIcon());
        if (childRow.getIcon()>500) {
            Picasso.with(context)
                    .load("http://ddragon.leagueoflegends.com/cdn/7.23.1/img/item/" + childRow.getIcon() + ".png")
                    .into(childIcon);
        } else {
            Picasso.with(context)
                    .load("http://ddragon.leagueoflegends.com/cdn/7.23.1/img/champion/" +
                    childRow.getText() + ".png")
                    .into(childIcon);
        }

        final TextView childText = (TextView) convertView.findViewById(R.id.child_text);
        childText.setText(childRow.getText().trim());



        final View finalConvertView = convertView;
        childText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // add item to starting items
                if (childRow.getIcon() > 500) {
                    View rootView = ((Activity) context).getWindow().getDecorView().findViewById(android.R.id.content);
                    final LinearLayout itemList = rootView.findViewById(R.id.startingItemsList);
                    final ImageView addedItem = new ImageView(rootView.getContext());
                    LinearLayout.LayoutParams param = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT
                            , ViewGroup.LayoutParams.MATCH_PARENT);
                    param.setMargins(5, 2, 2, 2);
                    addedItem.setLayoutParams(param);
                    addedItem.setImageResource(R.drawable.barrier);
                    addedItem.getLayoutParams().height = 150;
                    addedItem.setTag(childText.getText());
                    addedItem.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            itemList.removeView(addedItem);
                            Toast.makeText(finalConvertView.getContext()
                                    , childText.getText() + " Removed from items"
                                    , Toast.LENGTH_SHORT).show();
                        }
                    });
                    if (itemList != null) {
                        itemList.addView(addedItem);

                        Picasso.with(context)
                                .load("http://ddragon.leagueoflegends.com/cdn/6.24.1/img/item/" + childRow.getIcon() + ".png")
                                .into(addedItem);

                        Toast.makeText(finalConvertView.getContext()
                                , childText.getText() + " Added to items"
                                , Toast.LENGTH_SHORT).show();

                    }
                } else {
                    if (createOrView.equals("create")) {
                        Intent myIntent = new Intent(context, CreateGuide.class);
                        myIntent.putExtra("champion", childRow.getText());
                        context.startActivity(myIntent);
                    } else if (createOrView.equals("view")) {
                        Intent myIntent = new Intent(context, ViewGuides.class);
                        myIntent.putExtra("key", childRow.getText());
                        context.startActivity(myIntent);
                    }

                }
            }
        });

        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

    public void filterData(String query) {
        query = query.toLowerCase();
        parentRowList.clear();

        if (query.isEmpty()) {
            parentRowList.addAll(originalList);
        }
        else {
            for (ParentRow parentRow : originalList) {
                ArrayList<ChildRow> childList = parentRow.getChildList();
                ArrayList<ChildRow> newList = new ArrayList<ChildRow>();

                for (ChildRow childRow: childList) {
                    if (childRow.getText().toLowerCase().contains(query)) {
                        newList.add(childRow);
                    }
                } // end for (com.example.user.searchviewexpandablelistview.ChildRow childRow: childList)
                if (newList.size() > 0) {
                    ParentRow nParentRow = new ParentRow(parentRow.getName(), newList);
                    parentRowList.add(nParentRow);
                }
            } // end or (com.example.user.searchviewexpandablelistview.ParentRow parentRow : originalList)
        } // end else

        notifyDataSetChanged();
    }
}