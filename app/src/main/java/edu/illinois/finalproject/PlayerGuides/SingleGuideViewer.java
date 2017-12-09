package edu.illinois.finalproject.PlayerGuides;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import edu.illinois.finalproject.R;

public class SingleGuideViewer extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_single_guide_viewer);
    Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
    setSupportActionBar(toolbar);

    final Guide currentGuide = getIntent().getParcelableExtra("guide");

    TextView guideTitle = findViewById(R.id.single_Guide_Title);
    guideTitle.setText((currentGuide.getTitle()+": "));

  }

}
