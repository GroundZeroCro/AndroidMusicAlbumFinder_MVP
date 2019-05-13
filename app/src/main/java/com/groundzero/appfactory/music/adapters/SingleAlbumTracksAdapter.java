package com.groundzero.appfactory.music.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.groundzero.appfactory.R;
import com.groundzero.appfactory.music.models.SingleAlbum;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SingleAlbumTracksAdapter extends BaseAdapter {

  @BindView(R.id.track_index)
  TextView trackIndex;
  @BindView(R.id.track_name)
  TextView trackName;
  @BindView(R.id.track_duration)
  TextView trackDuration;

  private Context context;
  private List<SingleAlbum.Album.Tracks.Track> tracks;

  public SingleAlbumTracksAdapter(Context context, List<SingleAlbum.Album.Tracks.Track> tracks) {
    this.context = context;
    this.tracks = tracks;
  }

  @Override
  public int getCount() {
    return tracks.size();
  }

  @Override
  public Object getItem(int i) {
    return null;
  }

  @Override
  public long getItemId(int i) {
    return 0;
  }

  @SuppressLint({"ViewHolder", "SetTextI18n"})
  @Override
  public View getView(int i, View view, ViewGroup viewGroup) {
    view = LayoutInflater.from(context).inflate(R.layout.item_track, viewGroup, false);
    ButterKnife.bind(this, view);

    trackIndex.setText(i + 1 + ".");
    trackName.setText(tracks.get(i).getName());
    trackDuration.setText(tracks.get(i).getDuration() + " sec duration");

    return view;
  }
}
