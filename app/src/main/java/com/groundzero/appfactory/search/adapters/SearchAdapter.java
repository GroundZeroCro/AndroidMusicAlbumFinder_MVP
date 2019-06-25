package com.groundzero.appfactory.search.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.groundzero.appfactory.R;
import com.groundzero.appfactory.common.utils.FragmentNavigation;
import com.groundzero.appfactory.search.models.Artist;

import java.util.List;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.ViewHolder> {

  private Context context;
  private List<Artist.Results.ArtistDetails> artistList;

  public SearchAdapter(Context context, List<Artist.Results.ArtistDetails> artistList) {
    this.context = context;
    this.artistList = artistList;
  }

  @NonNull
  @Override
  public SearchAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    View view = LayoutInflater.from(context).inflate(R.layout.item_search, parent, false);

    return new ViewHolder(view);
  }

  @Override
  public void onBindViewHolder(@NonNull SearchAdapter.ViewHolder h, int i) {

    h.artistIndex.setText((i + 1) + ".");
    h.artistName.setText(artistList.get(i).getName());
    Glide.with(context).load(artistList.get(i).getImages().get(1).getImageUrl()).into(h.artistImage);

    h.artistParent.setOnClickListener(view -> FragmentNavigation
            .nextFragment(
                    view,
                    R.id.topAlbumsFragment,
                    new String[][]{
                            new String[]{"artistName", artistList.get(i).getName()}
                    }
            ));
  }

  @Override
  public int getItemCount() {
    return artistList.size();
  }

  class ViewHolder extends RecyclerView.ViewHolder {

    private LinearLayout artistParent;
    private TextView artistIndex, artistName;
    private ImageView artistImage;

    ViewHolder(@NonNull View itemView) {
      super(itemView);

      artistParent = itemView.findViewById(R.id.artist_parent);
      artistIndex = itemView.findViewById(R.id.artist_index);
      artistName = itemView.findViewById(R.id.artist_name);
      artistImage = itemView.findViewById(R.id.artist_image);
    }
  }
}
