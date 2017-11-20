package edu.illinois.finalproject.PlayerProfile;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import edu.illinois.finalproject.R;

public class PlayerProfile extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_player_profile);
    Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
    setSupportActionBar(toolbar);


    // populating match data for given player
    final RecyclerView recyclerLayout = (RecyclerView) findViewById(R.id.single_match_rview);

    Button refresh = (Button) findViewById(R.id.Refresh_button);
    refresh.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
//        ZomatoDataAsyncTask zomatoDataAsyncTask = new ZomatoDataAsyncTask
//                (MainActivity.this, recyclerLayout);
//        String givenCityName = String.valueOf(cityName.getText());
//        zomatoDataAsyncTask.execute(givenCityName);
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
