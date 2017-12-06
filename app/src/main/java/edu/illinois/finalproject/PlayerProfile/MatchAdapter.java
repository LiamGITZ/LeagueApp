package edu.illinois.finalproject.PlayerProfile;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import net.rithms.riot.api.ApiConfig;
import net.rithms.riot.api.RiotApi;
import net.rithms.riot.api.RiotApiAsync;
import net.rithms.riot.api.endpoints.champion.dto.Champion;
import net.rithms.riot.api.endpoints.match.dto.Match;
import net.rithms.riot.api.endpoints.match.dto.MatchList;
import net.rithms.riot.api.endpoints.match.dto.MatchReference;
import net.rithms.riot.api.endpoints.static_data.dto.ChampionList;
import net.rithms.riot.api.request.AsyncRequest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import edu.illinois.finalproject.ExtendedSummoner;
import edu.illinois.finalproject.LoLApiKey;
import edu.illinois.finalproject.LolConstants;
import edu.illinois.finalproject.R;


/**
 * Created by liam on 11/20/17.
 */

public class MatchAdapter extends RecyclerView.Adapter<MatchAdapter.ViewHolder>{
  private List<MatchReference> MatchList = new ArrayList<>();
  ExtendedSummoner extendedSummoner = null;

  public MatchAdapter(ExtendedSummoner extendedSummoner){
    MatchList.addAll(extendedSummoner.matches.getMatches());
    this.extendedSummoner = extendedSummoner;
  }

  public MatchAdapter(java.util.List<MatchReference> matches){
    MatchList.addAll(matches);
  }

  public void addMatch(MatchReference givenMatch) {
    MatchList.add(givenMatch);
  }

  @Override
  public int getItemViewType(int position) {
    MatchReference match = MatchList.get(position);
    return R.layout.single_match_layout;
  }

  @Override
  public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    View singleMatch = LayoutInflater.from(parent.getContext()).
            inflate(viewType,parent,false);
    return new ViewHolder(singleMatch);
  }

  @Override
  public void onBindViewHolder(MatchAdapter.ViewHolder holder, int position) {
    final MatchReference currentMatch = MatchList.get(position);
    String queueType = "";
    if (currentMatch.getQueue() == 420){
      queueType = "5v5 Ranked Solo";
    } else if (currentMatch.getQueue() == 440) {
      queueType = "5v5 Ranked Flex";
    } else if (currentMatch.getQueue() == 400) {
      queueType = "5v5 Draft Pick";
    }else if (currentMatch.getQueue() == 430) {
      queueType = "5v5 Blind Pick";
    }
    holder.headerTextview.setText(String.valueOf(queueType));
    holder.score.setText(String.valueOf(currentMatch.getLane()));

    net.rithms.riot.api.endpoints.static_data.dto.Champion currentChampion =
            LolConstants.championMap.get(currentMatch.getChampion());
    String champName = currentChampion.getKey();
    String summonerIconUrl = "http://ddragon.leagueoflegends.com/cdn/" +
            extendedSummoner.relm.getDd() + "/img/champion/" +
            champName + ".png";

    final Context context = holder.itemView.getContext();
    Picasso.with(context).load(summonerIconUrl).into(holder.champPicture);

  }

  @Override
  public int getItemCount() {
    return MatchList.size();
  }

  public class ViewHolder extends RecyclerView.ViewHolder {
    public View itemView;
    public TextView headerTextview;
    public TextView score;
    public ImageView champPicture;

    public ViewHolder(View itemView) {
      super(itemView);
      this.itemView = itemView;
      this.headerTextview = itemView.findViewById(R.id.MatchHeading);
      this.score = itemView.findViewById(R.id.MatchScore);
      this.champPicture =  itemView.findViewById(R.id.MatchChampion);

    }
  }
}
