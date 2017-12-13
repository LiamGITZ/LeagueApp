package edu.illinois.finalproject.PlayerGuides;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

import edu.illinois.finalproject.R;

import static android.content.ContentValues.TAG;

public class EditGuides extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_edit_guides);
    Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
    setSupportActionBar(toolbar);

    final FirebaseDatabase database = com.google.firebase.database.FirebaseDatabase.getInstance();
    final String currentUser = PlayerGuides.user.getUid();
//    DatabaseReference ref = database.getReference("users/"+currentUser);
    final DatabaseReference ref = database.getReference("champions/");
    final List<Guide> guides = new ArrayList<Guide>();
    final ArrayList<String> championNames = new ArrayList<String>();
    final ArrayList<String> guideIDS = new ArrayList<String>();
    final Context thisContext = this;


    // Add all polls in ref as rows
    ref.addListenerForSingleValueEvent(new ValueEventListener() {
      @Override
      public void onDataChange(final DataSnapshot snapshot) {
              for (DataSnapshot championSnapshot : snapshot.getChildren()) {
                for (DataSnapshot guideNameSnapshot : championSnapshot.getChildren()) {
                  for (DataSnapshot guideSnapshot : guideNameSnapshot.getChildren()) {
                    String currentGuideCreator = (String) guideSnapshot.child("user").getValue();
                    if (currentGuideCreator != null && currentGuideCreator.equals(currentUser)) {
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
                      championNames.add(championSnapshot.getKey());
                      guideIDS.add(guideSnapshot.getKey());
                    }
                  }
                }
              }
        final RecyclerView recyclerLayout = (RecyclerView) findViewById(R.id.guide_Edit_Recycler);
        GuideVIewAdapter guideVIewAdapter = new GuideVIewAdapter(guides, championNames, guideIDS);
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
