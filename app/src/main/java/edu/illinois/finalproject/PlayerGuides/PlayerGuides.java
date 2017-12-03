package edu.illinois.finalproject.PlayerGuides;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import net.rithms.riot.api.ApiConfig;
import net.rithms.riot.api.RiotApi;
import net.rithms.riot.api.RiotApiException;
import net.rithms.riot.api.endpoints.summoner.dto.Summoner;
import net.rithms.riot.constant.Platform;

import edu.illinois.finalproject.LoLApiKey;
import edu.illinois.finalproject.R;

public class PlayerGuides extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_player_guides);
    Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
    setSupportActionBar(toolbar);

    Button viewGuides = findViewById(R.id.View_Guides_Button);
    Button createGuides = findViewById(R.id.Create_Guides_Button);

    createGuides.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        final Context context = view.getContext();
        Intent guideIntent = new Intent(context, CreateGuide.class);
        context.startActivity(guideIntent);
      }
    });




//    FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//    fab.setOnClickListener(new View.OnClickListener() {
//      @Override
//      public void onClick(View view) {
//        Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                .setAction("Action", null).show();
//      }
//    });
  }

}
