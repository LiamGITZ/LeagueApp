package edu.illinois.finalproject.PlayerGuides;

import java.util.List;

/**
 * Created by liam on 12/3/17.
 */
  /*
{"Title": "Test",
        "User": "id"
		"Introduction": "to play jax one must be good at the game of league of legends",
		"Items": [
			{"Starting Items": ["Dorans Blade", "Health Potion"]},
			{"Core Items": ["Trinity Force", "Titanic Hydra"]},
			{"Situational Items": ["Hexdrinker", "Randuins Omen"]}
		],
		"Summoners": ["flash", "teleport"],
		"Ability Skill Order": ["w","e","q","w","w","r","w","q","w","q","r","q","q","e","e","r","e","e"],
		"Body": "Early game: win lane \n Mid Game: Get towers \n Late Game: rat Dota",
		"Countered By": ["Fiora","Teemo"],
		"Counters": ["Malphite", "Camille"],
		"Quick Tips": ["W is an Auto Reset","Can hop to Wards"]
		}
 */
public class Guide {
  private String user;
  private String title;
  private String introduction;
  private String body;
  private List StartingItems;
  private List CoreItems;
  private List SituationalItems;
  private List Summoners;
  private List abilitySkillOrder;
  private List counters;
  private List counteredBy;
  private List quickTips;

  public String getUser() {
    return user;
  }

  public void setUser(String user) {
    this.user = user;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getIntroduction() {
    return introduction;
  }

  public void setIntroduction(String introduction) {
    this.introduction = introduction;
  }

  public String getBody() {
    return body;
  }

  public void setBody(String body) {
    this.body = body;
  }

  public List getStartingItems() {
    return StartingItems;
  }

  public void setStartingItems(List startingItems) {
    StartingItems = startingItems;
  }

  public List getCoreItems() {
    return CoreItems;
  }

  public void setCoreItems(List coreItems) {
    CoreItems = coreItems;
  }

  public List getSituationalItems() {
    return SituationalItems;
  }

  public void setSituationalItems(List situationalItems) {
    SituationalItems = situationalItems;
  }

  public List getSummoners() {
    return Summoners;
  }

  public void setSummoners(List summoners) {
    Summoners = summoners;
  }

  public List getAbilitySkillOrder() {
    return abilitySkillOrder;
  }

  public void setAbilitySkillOrder(List abilitySkillOrder) {
    this.abilitySkillOrder = abilitySkillOrder;
  }

  public List getCounters() {
    return counters;
  }

  public void setCounters(List counters) {
    this.counters = counters;
  }

  public List getCounteredBy() {
    return counteredBy;
  }

  public void setCounteredBy(List counteredBy) {
    this.counteredBy = counteredBy;
  }

  public List getQuickTips() {
    return quickTips;
  }

  public void setQuickTips(List quickTips) {
    this.quickTips = quickTips;
  }
}
