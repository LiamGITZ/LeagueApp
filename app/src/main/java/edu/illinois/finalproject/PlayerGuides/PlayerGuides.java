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

import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.auth.IdpResponse;
import com.firebase.ui.auth.ResultCodes;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import net.rithms.riot.api.ApiConfig;
import net.rithms.riot.api.RiotApi;
import net.rithms.riot.api.RiotApiException;
import net.rithms.riot.api.endpoints.summoner.dto.Summoner;
import net.rithms.riot.constant.Platform;

import java.util.Arrays;
import java.util.List;

import edu.illinois.finalproject.LoLApiKey;
import edu.illinois.finalproject.R;

public class PlayerGuides extends AppCompatActivity {
  private static final int  RC_SIGN_IN = 123;
  private FirebaseUser user = null;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_player_guides);
    Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
    setSupportActionBar(toolbar);

    Button viewGuides = findViewById(R.id.View_Guides_Button);
    Button createGuides = findViewById(R.id.Create_Guides_Button);

    FirebaseApp.initializeApp(this);
    createGuides.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        final Context context = view.getContext();
        Intent guideIntent = new Intent(context, CreateGuide.class);
        if (user != null) {
          context.startActivity(guideIntent);
        } else {
          // code from
          // https://firebase.google.com/docs/auth/android/firebaseui



// Choose authentication providers
          List<AuthUI.IdpConfig> providers = Arrays.asList(
                  new AuthUI.IdpConfig.Builder(AuthUI.EMAIL_PROVIDER).build());
//                new AuthUI.IdpConfig.Builder(AuthUI.PHONE_VERIFICATION_PROVIDER).build(),
//                new AuthUI.IdpConfig.Builder(AuthUI.GOOGLE_PROVIDER).build(),
//                new AuthUI.IdpConfig.Builder(AuthUI.FACEBOOK_PROVIDER).build(),
//                new AuthUI.IdpConfig.Builder(AuthUI.TWITTER_PROVIDER).build());

// Create and launch sign-in intent
          startActivityForResult(
                  AuthUI.getInstance()
                          .createSignInIntentBuilder()
                          .setAvailableProviders(providers)
                          .build(),
                  RC_SIGN_IN);

          if (requestCode == RC_SIGN_IN) {
            IdpResponse response = IdpResponse.fromResultIntent(data);

            if (resultCode == ResultCodes.OK) {
              // Successfully signed in
              user = FirebaseAuth.getInstance().getCurrentUser();
              context.startActivity(guideIntent);
            } else {
              // Sign in failed, check response for error code
              // ...
            }
          }

        }
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
