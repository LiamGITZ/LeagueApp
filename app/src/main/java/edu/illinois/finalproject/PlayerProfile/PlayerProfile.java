package edu.illinois.finalproject.PlayerProfile;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;

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

    String summonerName = "leaf sin"; // summonerId to request
    long summonerID = 31093518;
    Platform platform = Platform.NA; // platform to request

    ExtendedSummoner eSummoner = null;
    try {
      eSummoner = GetPlayerData.getData(summonerName,platform);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }

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
            new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));


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
