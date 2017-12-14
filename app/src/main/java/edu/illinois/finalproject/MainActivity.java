package edu.illinois.finalproject;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import edu.illinois.finalproject.PlayerGuides.PlayerGuides;
import edu.illinois.finalproject.PlayerProfile.PlayerProfile;


public class MainActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    LolConstants.getData();

    // seeting up the redirect buttons
    Button guides = findViewById(R.id.Guides_button);
    Button profiles = findViewById(R.id.Player_Profiles_button);
//        Button champstats = findViewById(R.id.Champion_statistics_button);

    guides.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        final Context context = view.getContext();
        Intent guideIntent = new Intent(context, PlayerGuides.class);
        context.startActivity(guideIntent);
      }
    });

    profiles.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        final Context context = view.getContext();
        Intent profilesIntent = new Intent(context, PlayerProfile.class);
        context.startActivity(profilesIntent);
      }
    });

//        champstats.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                final Context context = view.getContext();
//                Intent champstatsIntent = new Intent(context, ChampionStatistics.class);
//                context.startActivity(champstatsIntent);
//            }
//        });
  }


}
