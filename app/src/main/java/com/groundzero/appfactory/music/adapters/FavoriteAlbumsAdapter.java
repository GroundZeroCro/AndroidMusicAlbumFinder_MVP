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
import com.groundzero.appfactory.music.models.SingleAlbum;

import java.util.List;

public class FavoriteAlbumsAdapter extends RecyclerView.Adapter<FavoriteAlbumsAdapter.ViewHolder> {

  private Context context;
  private List<SingleAlbum> albumDetails;

  public FavoriteAlbumsAdapter(
          Context context,
          List<SingleAlbum> albumDetails) {
    this.context = context;
    this.albumDetails = albumDetails;
  }

  @NonNull
  @Override
  public FavoriteAlbumsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    View view = LayoutInflater.from(context)
            .inflate(R.layout.item_top_albums, parent, false);

    return new ViewHolder(view);
  }

  @Override
  public void onBindViewHolder(@NonNull FavoriteAlbumsAdapter.ViewHolder h, int i) {

    h.albumIndex.setText(i + 1 + ".");
    h.albumName.setText(albumDetails.get(i).album.getName());
    h.albumUrl.setText(albumDetails.get(i).album.getUrl());
    Glide.with(context).load(albumDetails.get(i).album.getImages().get(2).getImageUrl()).into(h.albumImage);

    h.albumParent.setOnClickListener(view -> FragmentNavigation
            .nextFragment(
                    view,
                    R.id.single_album,
                    new String[][]{
                            new String[]{"artistName", albumDetails.get(i).album.getArtist()},
                            new String[]{"albumName", albumDetails.get(i).album.getName()}
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
