package edu.illinois.finalproject;

import net.rithms.riot.api.ApiConfig;
import net.rithms.riot.api.RiotApi;
import net.rithms.riot.api.RiotApiAsync;
import net.rithms.riot.api.endpoints.static_data.dto.Champion;
import net.rithms.riot.api.endpoints.static_data.dto.ChampionList;
import net.rithms.riot.api.endpoints.static_data.dto.Item;
import net.rithms.riot.api.endpoints.static_data.dto.ItemList;
import net.rithms.riot.api.endpoints.static_data.dto.SummonerSpellList;
import net.rithms.riot.api.request.AsyncRequest;
import net.rithms.riot.api.request.RequestAdapter;
import net.rithms.riot.constant.Platform;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;

/**
 * Created by liam on 12/3/17.
 */

public class LolConstants {
  public static ChampionList champions;
  public static Map<Integer,Champion> championMap = new HashMap<>();
  public static ItemList itemList;
  public static Map<String,Item> itemMap = new HashMap<>();
  public static SummonerSpellList spellList;

  public static void getData(){
    ApiConfig config = new ApiConfig().setKey(LoLApiKey.apiKey);
    RiotApi api = new RiotApi(config);
    RiotApiAsync apiAsync = api.getAsyncApi();

    Platform platform = Platform.TR; // platform to request


    // get champion information
    AsyncRequest requestChampion = apiAsync.getDataChampionList(platform);
    requestChampion.addListeners(new RequestAdapter() {
      @Override
      public void onRequestSucceeded(AsyncRequest request) {
        champions = request.getDto();
      }
    });

    // get item list
    AsyncRequest requestItems = apiAsync.getDataItemList(platform);
    requestItems.addListeners(new RequestAdapter() {
      @Override
      public void onRequestSucceeded(AsyncRequest request) {
        itemList = request.getDto();
      }
    });

    // get summoner spells
    AsyncRequest requestSummonerSpells = apiAsync.getDataSummonerSpellList(platform);
    requestSummonerSpells.addListeners(new RequestAdapter() {
      @Override
      public void onRequestSucceeded(AsyncRequest request) {
        spellList = request.getDto();
      }
    });

    try {
      // Wait for all asynchronous requests to finish
      apiAsync.awaitAll();
    } catch (InterruptedException e) {
      // We can use the Api's logger
      RiotApi.log.log(Level.SEVERE, "Waiting Interrupted", e);
    }

    // creating champion map by id
    for (net.rithms.riot.api.endpoints.static_data.dto.Champion c : champions.getData().values()) {
      championMap.put(c.getId(), c);
    }

    // creating item map by id
    for (Item i : itemList.getData().values()) {
      itemMap.put(i.getName(), i);
    }

  }
}
