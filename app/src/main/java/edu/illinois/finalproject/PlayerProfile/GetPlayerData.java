package edu.illinois.finalproject.PlayerProfile;

import net.rithms.riot.api.ApiConfig;
import net.rithms.riot.api.RiotApi;
import net.rithms.riot.api.RiotApiAsync;
import net.rithms.riot.api.endpoints.league.constant.LeagueQueue;
import net.rithms.riot.api.endpoints.league.dto.LeaguePosition;
import net.rithms.riot.api.request.AsyncRequest;
import net.rithms.riot.api.request.RequestAdapter;
import net.rithms.riot.api.request.ratelimit.DefaultRateLimitHandler;
import net.rithms.riot.api.request.ratelimit.RateLimitHandler;
import net.rithms.riot.constant.Platform;

import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;

import edu.illinois.finalproject.ExtendedSummoner;
import edu.illinois.finalproject.LoLApiKey;

/**
 * Created by liam on 11/21/17.
 */

public class GetPlayerData {
 public static ExtendedSummoner getData(String summonerName, Platform platform) throws InterruptedException {
   ApiConfig config = new ApiConfig().setKey(LoLApiKey.apiKey);
   RiotApi api = new RiotApi(config);
   RiotApiAsync apiAsync = api.getAsyncApi();

   final ExtendedSummoner eSummoner = new ExtendedSummoner();

   // Asynchronously get summoner information
   AsyncRequest requestSummoner = null;
   requestSummoner = apiAsync.getSummonerByName(platform, summonerName);
   requestSummoner.addListeners(new RequestAdapter() {
     @Override
     public void onRequestSucceeded(AsyncRequest request) {
       eSummoner.summoner = request.getDto();
     }
   });
  requestSummoner.await();


   // Asynchronously get league information
   AsyncRequest requestLeague = apiAsync.getLeaguePositionsBySummonerId(platform, eSummoner.summoner.getId());
   requestLeague.addListeners(new RequestAdapter() {
     @Override
     public void onRequestSucceeded(AsyncRequest request) {
       Set<LeaguePosition> leaguePositions = request.getDto();
       if (leaguePositions == null || leaguePositions.isEmpty()) {
         return;
       }
       for (LeaguePosition leaguePosition : leaguePositions) {
         if (leaguePosition.getQueueType().equals(LeagueQueue.RANKED_SOLO_5x5.name())) {
           eSummoner.leagueSolo = leaguePosition;
         } else if (leaguePosition.getQueueType().equals(LeagueQueue.RANKED_FLEX_SR.name())) {
           eSummoner.leagueFlexSR = leaguePosition;
         } else if (leaguePosition.getQueueType().equals(LeagueQueue.RANKED_FLEX_TT.name())) {
           eSummoner.leagueFlexTT = leaguePosition;
         }
       }
     }
   });

   // get match information
   AsyncRequest requestMatches = apiAsync.getMatchListByAccountId(platform, eSummoner.summoner.getAccountId());
   requestMatches.addListeners(new RequestAdapter() {
     @Override
     public void onRequestSucceeded(AsyncRequest request) {
       eSummoner.matches = request.getDto();
     }
   });


   // get champion information
   AsyncRequest requestChampion = apiAsync.getDataChampionList(platform);
   requestChampion.addListeners(new RequestAdapter() {
     @Override
     public void onRequestSucceeded(AsyncRequest request) {
       eSummoner.champions = request.getDto();
     }
   });

   // getting patch information
   AsyncRequest requestRealm = apiAsync.getDataRealm(platform);
   requestRealm.addListeners(new RequestAdapter() {
     @Override
     public void onRequestSucceeded(AsyncRequest request) {
       eSummoner.relm = request.getDto();
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
   for (net.rithms.riot.api.endpoints.static_data.dto.Champion c : eSummoner.champions.getData().values()) {
     eSummoner.championMap.put(c.getId(), c);
   }

   return eSummoner;
 }
}
