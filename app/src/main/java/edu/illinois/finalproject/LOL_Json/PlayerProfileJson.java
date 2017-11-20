package edu.illinois.finalproject.LOL_Json;

/**
 * Created by liam on 11/20/17.
 */

/*
request url: https://na1.api.riotgames.com/lol/summoner/v3/summoners/by-name/leaf%20sin
{
    "profileIconId": 3194,
    "name": "Leaf Sin",
    "summonerLevel": 35,
    "accountId": 43115474,
    "id": 31093518,
    "revisionDate": 1510989596000
}
 */
public class PlayerProfileJson {
  private int profileIconId;
  private String name;
  private int summonerLevel;
  private int accountId;
  private int id;
}
