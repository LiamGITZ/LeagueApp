package edu.illinois.finalproject.PlayerProfile;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import net.rithms.riot.api.ApiConfig;
import net.rithms.riot.api.RiotApi;
import net.rithms.riot.api.RiotApiAsync;
import net.rithms.riot.api.endpoints.champion.dto.Champion;
import net.rithms.riot.api.endpoints.league.constant.LeagueQueue;
import net.rithms.riot.api.endpoints.league.dto.LeaguePosition;
import net.rithms.riot.api.request.AsyncRequest;
import net.rithms.riot.api.request.RequestAdapter;
import net.rithms.riot.constant.Platform;

import java.util.Set;
import java.util.logging.Level;

import edu.illinois.finalproject.ExtendedSummoner;
import edu.illinois.finalproject.LoLApiKey;
import edu.illinois.finalproject.R;

public class PlayerProfile extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_player_profile);
    Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
    setSupportActionBar(toolbar);

    ImageView searchImg = (ImageView) findViewById(R.id.searchIcon);
    final Context context = this;

    searchImg.setOnClickListener(new View.OnClickListener() {
      ExtendedSummoner eSummoner = null;
      @Override
      public void onClick(View view) {
        EditText enteredText = (EditText) findViewById(R.id.profileSearch);


        String summonerName = enteredText.getText().toString(); // summonerId to request
        Platform platform = Platform.NA; // platform to request



        try {
          eSummoner = GetPlayerData.getData(summonerName, platform);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }

        if (eSummoner != null) {
          // setting headers
          TextView summonerNameView = findViewById(R.id.Summoner_Name_View);
          summonerNameView.setText(eSummoner.summoner.getName());

          // getting and setting the summoner icon
          ImageView summonerIcon = findViewById(R.id.summonerIcon);
          String summonerIconUrl = "http://ddragon.leagueoflegends.com/cdn/" +
                  eSummoner.relm.getDd() + "/img/profileicon/" +
                  eSummoner.summoner.getProfileIconId() + ".png";
          Picasso.with(PlayerProfile.this).load(summonerIconUrl).into(summonerIcon);


          // populating match data for given player
          final RecyclerView recyclerLayout = (RecyclerView) findViewById(R.id.single_match_rview);
          MatchAdapter matchAdapter = new MatchAdapter(eSummoner);
          recyclerLayout.setAdapter(matchAdapter);
          recyclerLayout.setLayoutManager(
                  new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false));

        }else {
          Toast.makeText(context
                  , "No such summoner"
                  , Toast.LENGTH_SHORT).show();
        }
      }
    });


  }

}
