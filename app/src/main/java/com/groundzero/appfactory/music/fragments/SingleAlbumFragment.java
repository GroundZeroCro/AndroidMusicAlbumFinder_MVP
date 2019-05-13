package com.groundzero.appfactory.music.fragments;


import android.app.AlertDialog;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.groundzero.appfactory.R;
import com.groundzero.appfactory.music.dialogs.ChoiceDialog;
import com.groundzero.appfactory.music.presenter.SingleAlbumPresenter;
import com.groundzero.appfactory.music.view.SingleAlbumView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SingleAlbumFragment extends BaseFragment implements SingleAlbumView {

  @BindView(R.id.tracks_list_view)
  ListView tracksListView;
  @BindView(R.id.album_details_image)
  ImageView albumDetailsImage;
  @BindView(R.id.album_details_name)
  TextView albumDetailsName;
  @BindView(R.id.album_details_artist)
  TextView albumDetailsArtist;
  @BindView(R.id.album_bookmark_button)
  ImageButton albumDetailsBookmarkButton;
  @BindView(R.id.progress_bar)
  ProgressBar progressBar;
  @BindView(R.id.line_divider)
  View lineDivider;

  private SingleAlbumPresenter presenter;
  private ChoiceDialog choiceDialog;

  @Override
  public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                           Bundle savedInstanceState) {
    View mView = inflater.inflate(R.layout.fragment_single_album, container, false);
    ButterKnife.bind(this, mView);
    presenter = new SingleAlbumPresenter(this, getFoundationComponent());
    choiceDialog = new ChoiceDialog(getContext(), presenter);
    return mView;
  }

  @Override
  public ListView fetchListView() {
    return tracksListView;
  }

  @Override
  public Bundle fetchPassingBundle() {
    return getArguments();
  }

  @Override
  public ImageView getSingleAlbumImageView() {
    return albumDetailsImage;
  }

  @Override
  public TextView getSingleAlbumTitleView() {
    return albumDetailsName;
  }

  @Override
  public TextView getSingleAlbumArtistView() {
    return albumDetailsArtist;
  }

  @Override
  public Toast generateToast(String message) {
    return Toast.makeText(getContext(), message, Toast.LENGTH_LONG);
  }

  @Override
  public void changeBookmarkButtonColor(boolean isBookmarked) {
    int color = isBookmarked ? Color.parseColor(getResourceColor(R.color.green)) :
            Color.parseColor(getResourceColor(R.color.coffee));
    albumDetailsBookmarkButton.setColorFilter(color, PorterDuff.Mode.SRC_IN);
  }

  @Override
  public AlertDialog generateChoiceDialog() {
    return choiceDialog.generateDialog();
  }


  private String getResourceColor(int resourceColor) {
    return getContext().getResources().getString(resourceColor);
  }

  @Override
  public void viewOnLoad(boolean isDataLoaded) {
    if (true) {
      progressBar.setVisibility(View.GONE);
      lineDivider.setVisibility(View.VISIBLE);
      albumDetailsBookmarkButton.setVisibility(View.VISIBLE);
    }
  }

  @OnClick(R.id.album_bookmark_button)
  void onBookmark() {
    presenter.onBookmarkClick();
  }

  @Override
  public void onStart() {
    super.onStart();
    presenter.getAlbumDetails(presenter.getArtistName(), presenter.getAlbumName());
  }

  @Override
  public void onStop() {
    super.onStop();
    presenter.disposeSubscription();
  }
}
