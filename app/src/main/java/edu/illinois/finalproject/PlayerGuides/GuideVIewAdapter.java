package edu.illinois.finalproject.PlayerGuides;

import android.content.ClipData;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import net.rithms.riot.api.endpoints.static_data.dto.Item;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import edu.illinois.finalproject.ExtendedSummoner;
import edu.illinois.finalproject.LolConstants;;
import edu.illinois.finalproject.R;

/**
 * Created by liam on 12/9/17.
 */

public class GuideVIewAdapter extends RecyclerView.Adapter<GuideVIewAdapter.ViewHolder>{
  private List<Guide> guideList = new ArrayList<>();
  private String championName;
  private ArrayList<String> championNames;
  private ArrayList<String> guideIDs;

  public GuideVIewAdapter(List<Guide> guideList, String championName){
    this.guideList.addAll(guideList);
    this.championName = championName;
  }

  public GuideVIewAdapter(List<Guide> guideList, ArrayList<String> championNames, ArrayList<String> guideIds){
    this.guideList.addAll(guideList);
    this.championNames = championNames;
    this.guideIDs = guideIds;
  }

  public void addGuide(Guide givenGuide) {
    guideList.add(givenGuide);
  }

  @Override
  public int getItemViewType(int position) {
    Guide match = guideList.get(position);
    return R.layout.guide_view;
  }

  @Override
  public GuideVIewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    View singleGuide = LayoutInflater.from(parent.getContext()).
            inflate(viewType,parent,false);
    return new ViewHolder(singleGuide);
  }

  @Override
  public void onBindViewHolder(GuideVIewAdapter.ViewHolder holder, final int position) {
    final Guide currentGuide = guideList.get(position);
    final Context context = holder.itemView.getContext();

    holder.guideTitle.setText(currentGuide.getTitle());

    for (Object item : currentGuide.getStartingItems()) {
      String currentItem = item.toString();

      final ImageView addedItem = new ImageView(context);
      LinearLayout.LayoutParams param = new LinearLayout.
              LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT
              , ViewGroup.LayoutParams.MATCH_PARENT);
      param.setMargins(5, 2, 2, 2);
      addedItem.setLayoutParams(param);
      addedItem.setImageResource(R.drawable.barrier);
      addedItem.getLayoutParams().height = 150;

      holder.startingItems.addView(addedItem);
      Item actualItem = LolConstants.itemMap.get(currentItem);
      Picasso.with(context)
              .load("http://ddragon.leagueoflegends.com/cdn/6.24.1/img/item/" + actualItem.getId() + ".png")
              .into(addedItem);
      if (championName != null) {
        holder.itemView.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
            Intent createViewGuideIntent = new Intent(context, SingleGuideViewer.class);
            createViewGuideIntent.putExtra("guide", currentGuide);
            createViewGuideIntent.putExtra("champion", championName);
            context.startActivity(createViewGuideIntent);
          }
        });
      } else if (championNames != null) {
        Picasso.with(context)
                .load("http://ddragon.leagueoflegends.com/cdn/7.23.1/img/champion/" +
                        championNames.get(position) + ".png")
                .into(holder.situtationChampImg);
          holder.situtationChampImg.setVisibility(1);
          
        holder.itemView.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
            Intent createViewGuideIntent = new Intent(context, CreateGuide.class);
            createViewGuideIntent.putExtra("guide", currentGuide);
            createViewGuideIntent.putExtra("champion", championNames.get(position));
            createViewGuideIntent.putExtra("guideID", guideIDs.get(position));
            context.startActivity(createViewGuideIntent);
          }
        });
      }
    }

  }

  @Override
  public int getItemCount() {
    return guideList.size();
  }

  public class ViewHolder extends RecyclerView.ViewHolder {
    public View itemView;
    public TextView guideTitle;
    public LinearLayout startingItems;
    public ImageView situtationChampImg;

    public ViewHolder(View itemView) {
      super(itemView);
      this.itemView = itemView;
      this.guideTitle = itemView.findViewById(R.id.guideTitle);
      this.startingItems = itemView.findViewById(R.id.startingItemsViewGuide);
      this.situtationChampImg = itemView.findViewById(R.id.champSituationalIMG);

    }
  }
}