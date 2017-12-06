package edu.illinois.finalproject;

import android.util.SparseArray;

import net.rithms.riot.api.endpoints.league.dto.LeaguePosition;
import net.rithms.riot.api.endpoints.match.dto.Match;
import net.rithms.riot.api.endpoints.match.dto.MatchList;
import net.rithms.riot.api.endpoints.static_data.dto.Champion;
import net.rithms.riot.api.endpoints.static_data.dto.ChampionList;
import net.rithms.riot.api.endpoints.static_data.dto.Realm;
import net.rithms.riot.api.endpoints.summoner.dto.Summoner;

import java.util.HashMap;
import java.util.Map;
import java.util.Queue;

/**
 * Created by liam on 11/20/17.
 */

public class ExtendedSummoner {
  public Realm relm;

  public  Summoner summoner;
  public LeaguePosition leagueSolo;
  public LeaguePosition leagueFlexSR;
  public LeaguePosition leagueFlexTT;
  public MatchList matches;
}
