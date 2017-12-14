package edu.illinois.finalproject;

import net.rithms.riot.api.endpoints.league.dto.LeaguePosition;
import net.rithms.riot.api.endpoints.match.dto.MatchList;
import net.rithms.riot.api.endpoints.static_data.dto.Realm;
import net.rithms.riot.api.endpoints.summoner.dto.Summoner;

/**
 * Created by liam on 11/20/17.
 */

public class ExtendedSummoner {
  public Realm relm;

  public Summoner summoner;
  public LeaguePosition leagueSolo;
  public LeaguePosition leagueFlexSR;
  public LeaguePosition leagueFlexTT;
  public MatchList matches;
}
