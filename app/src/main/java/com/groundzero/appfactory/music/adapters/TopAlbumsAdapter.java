package com.groundzero.appfactory.music.adapters;

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
import com.groundzero.appfactory.music.models.TopAlbums;

import java.util.List;

public class TopAlbumsAdapter extends RecyclerView.Adapter<TopAlbumsAdapter.ViewHolder> {

  private Context context;
  private List<TopAlbums.Album.AlbumDetails> albumDetails;
  private String artistName;

  public TopAlbumsAdapter(
          Context context,
          List<TopAlbums.Album.AlbumDetails> albumDetails,
          String artistName) {
    this.context = context;
    this.albumDetails = albumDetails;
    this.artistName = artistName;
  }

  @NonNull
  @Override
  public TopAlbumsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    View view = LayoutInflater.from(context)
            .inflate(R.layout.item_top_albums, parent, false);

    return new ViewHolder(view);
  }

  @Override
  public void onBindViewHolder(@NonNull TopAlbumsAdapter.ViewHolder h, int i) {

    h.albumIndex.setText(i + 1 + ".");
    h.albumName.setText(albumDetails.get(i).getName());
    h.albumUrl.setText(albumDetails.get(i).getUrl());
    Glide.with(context).load(albumDetails.get(i).getImages().get(2).getImageUrl()).into(h.albumImage);

    h.albumParent.setOnClickListener(view -> FragmentNavigation
            .nextFragment(
                    view,
                    R.id.single_album,
                    new String[][]{
                            new String[]{"artistName", artistName},
                            new String[]{"albumName", albumDetails.get(i).getName()}
                    }
            ));
  }

  @Override
  public int getItemCount() {
    return albumDetails.size();
  }

  class ViewHolder extends RecyclerView.ViewHolder {

    private LinearLayout albumParent;
    private TextView albumIndex, albumName, albumUrl;
    private ImageView albumImage;

    ViewHolder(@NonNull View itemView) {
      super(itemView);

      albumParent = itemView.findViewById(R.id.album_parent);
      albumIndex = itemView.findViewById(R.id.album_index);
      albumName = itemView.findViewById(R.id.album_name);
      albumUrl = itemView.findViewById(R.id.album_url);
      albumImage = itemView.findViewById(R.id.album_image);
    }
  }
}
