package edu.illinois.finalproject.PlayerGuides;

import android.os.Parcel;
import android.os.Parcelable;

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
public class Guide implements Parcelable {
  public static final Parcelable.Creator<Guide> CREATOR = new Parcelable.Creator<Guide>() {
    @Override
    public Guide createFromParcel(Parcel source) {
      return new Guide(source);
    }

    @Override
    public Guide[] newArray(int size) {
      return new Guide[size];
    }
  };
  private String user;
  private String title;
  private String introduction;
  private String body;
  private List<String> StartingItems;
  private List<String> CoreItems;
  private List<String> SituationalItems;
  private List<String> Summoners;
  private List<String> abilitySkillOrder;
  private List<String> counters;
  private List<String> counteredBy;
  private List<String> quickTips;

  public Guide() {
  }

  protected Guide(Parcel in) {
    this.user = in.readString();
    this.title = in.readString();
    this.introduction = in.readString();
    this.body = in.readString();
    this.StartingItems = in.createStringArrayList();
    this.CoreItems = in.createStringArrayList();
    this.SituationalItems = in.createStringArrayList();
    this.Summoners = in.createStringArrayList();
    this.abilitySkillOrder = in.createStringArrayList();
    this.counters = in.createStringArrayList();
    this.counteredBy = in.createStringArrayList();
    this.quickTips = in.createStringArrayList();
  }

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

  @Override
  public int describeContents() {
    return 0;
  }

  @Override
  public void writeToParcel(Parcel dest, int flags) {
    dest.writeString(this.user);
    dest.writeString(this.title);
    dest.writeString(this.introduction);
    dest.writeString(this.body);
    dest.writeStringList(this.StartingItems);
    dest.writeStringList(this.CoreItems);
    dest.writeStringList(this.SituationalItems);
    dest.writeStringList(this.Summoners);
    dest.writeStringList(this.abilitySkillOrder);
    dest.writeStringList(this.counters);
    dest.writeStringList(this.counteredBy);
    dest.writeStringList(this.quickTips);
  }
}
