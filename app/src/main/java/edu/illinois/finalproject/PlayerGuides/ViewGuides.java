package edu.illinois.finalproject.PlayerGuides;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import edu.illinois.finalproject.R;

public class ViewGuides extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_view_guides);
    Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
    setSupportActionBar(toolbar);


    final String championName = getIntent().getExtras().getString("key", "leesin");
    final FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference ref = database.getReference("champions/" + championName + "/guides/");

    final List<Guide> guides = new ArrayList<Guide>();
    final Context thisContext = this;
    // get guides for given champion from firebase
    ref.addListenerForSingleValueEvent(new ValueEventListener() {
      @Override
      public void onDataChange(DataSnapshot snapshot) {
        for (DataSnapshot guideSnapshot : snapshot.getChildren()) {
          Guide guide = new Guide();

          String title = (String) guideSnapshot.child("title").getValue();
          String intro = (String) guideSnapshot.child("introduction").getValue();
          List startingItems = (List) guideSnapshot.child("startingItems").getValue();
          List summoners = (List) guideSnapshot.child("summoners").getValue();

          guide.setTitle(title);
          guide.setIntroduction(intro);
          guide.setStartingItems(startingItems);
          guide.setSummoners(summoners);
          guides.add(guide);
        }

        final RecyclerView recyclerLayout = (RecyclerView) findViewById(R.id.guide_View_Recycler);
        GuideVIewAdapter guideVIewAdapter = new GuideVIewAdapter(guides, championName);
        recyclerLayout.setAdapter(guideVIewAdapter);
        recyclerLayout.setLayoutManager(
                new LinearLayoutManager(thisContext, LinearLayoutManager.VERTICAL, false));
      }

      @Override
      public void onCancelled(DatabaseError databaseError) {
      }
    });


  }

}
