package edu.illinois.finalproject.PlayerGuides;

import android.app.SearchManager;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.SearchView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import edu.illinois.finalproject.R;

public class CreateGuide extends AppCompatActivity {
  private int currentSummonerArrayPos = 0;
  private String[] summonersChosen = new String[2];


  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_create_guide);
    Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
    setSupportActionBar(toolbar);

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
        summonersChosen[currentSummonerArrayPos] = "Barrier";
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

  }


}


