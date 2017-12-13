package edu.illinois.finalproject.PlayerGuides;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import net.rithms.riot.api.endpoints.static_data.dto.Item;

import edu.illinois.finalproject.LolConstants;
import edu.illinois.finalproject.R;

public class SingleGuideViewer extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_single_guide_viewer);
    Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
    setSupportActionBar(toolbar);

    final Guide currentGuide = getIntent().getParcelableExtra("guide");
    final String championName = getIntent().getExtras().getString("champion","LeeSin");

    ImageView guideChampionPicture = findViewById(R.id.singleGuideViewChampionIMG);
    TextView guideTitle = findViewById(R.id.single_Guide_Title);
    TextView guideIntroduction = findViewById(R.id.singleGuideViewIntroduction);
    ImageView guideSummoner1 = findViewById(R.id.singleGuideViewSummoner1);
    ImageView guideSummoner2 = findViewById(R.id.singleGuideViewSummoner2);
    LinearLayout guideStartingItems = findViewById(R.id.viewSingleGuideStartingItems);

    // set text fields
    guideTitle.setText((currentGuide.getTitle()+": "));
    guideIntroduction.setText(("Introduction:\n"+currentGuide.getIntroduction()));

    // get champion picture
    Picasso.with(this)
            .load("http://ddragon.leagueoflegends.com/cdn/7.23.1/img/champion/" +
               championName + ".png")
            .into(guideChampionPicture);

    // add all starting items to layout
    for (Object item : currentGuide.getStartingItems()) {
      String currentItem = item.toString();

      final ImageView addedItem = new ImageView(this);
      LinearLayout.LayoutParams param = new LinearLayout.
              LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT
              , ViewGroup.LayoutParams.MATCH_PARENT);
      param.setMargins(5, 2, 2, 2);
      addedItem.setLayoutParams(param);
      addedItem.setImageResource(R.drawable.barrier);
      addedItem.getLayoutParams().height = 300;

      guideStartingItems.addView(addedItem);
      Item actualItem = LolConstants.itemMap.get(currentItem);
      Picasso.with(this)
              .load("http://ddragon.leagueoflegends.com/cdn/6.24.1/img/item/" + actualItem.getId() + ".png")
              .into(addedItem);
    }

    // set summoner pictures
    int resID1 = getResources().getIdentifier(currentGuide.getSummoners().get(0).toString().toLowerCase(), "drawable", getPackageName());
    int resID2 = getResources().getIdentifier(currentGuide.getSummoners().get(1).toString().toLowerCase(), "drawable", getPackageName());
    guideSummoner1.setImageResource(resID1);
    guideSummoner2.setImageResource(resID2);

  }

}
