package edu.illinois.finalproject.PlayerProfile;

import android.os.AsyncTask;

import edu.illinois.finalproject.LOL_Json.PlayerProfileJson;

/**
 * Created by liam on 11/20/17.
 */

public class PlayerProfileAsyncTask extends AsyncTask<String, Integer, PlayerProfileJson> {
  @Override
  protected PlayerProfileJson doInBackground(String... strings) {
    return null;
  }

  @Override
  protected void onPreExecute() {
    super.onPreExecute();
  }

  @Override
  protected void onPostExecute(PlayerProfileJson playerProfileJson) {
    super.onPostExecute(playerProfileJson);
  }

  @Override
  protected void onProgressUpdate(Integer... values) {
    super.onProgressUpdate(values);
  }
}
