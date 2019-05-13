package com.groundzero.appfactory.music.handlers;

import com.bumptech.glide.Glide;
import com.groundzero.appfactory.R;
import com.groundzero.appfactory.music.adapters.SingleAlbumTracksAdapter;
import com.groundzero.appfactory.music.db.dao.SingleAlbumDao;
import com.groundzero.appfactory.music.models.SingleAlbum;
import com.groundzero.appfactory.music.presenter.SingleAlbumPresenter;
import com.groundzero.appfactory.music.view.SingleAlbumView;

import java.util.List;

import retrofit2.Response;

public class SingleAlbumResponseHandler extends ResponseHandler<SingleAlbumView> {

  private SingleAlbumDao singleAlbumDao;
  private SingleAlbumPresenter presenter;

  public SingleAlbumResponseHandler(SingleAlbumPresenter presenter) {
    super(presenter.getContext(), presenter.getView());
    this.presenter = presenter;
    this.singleAlbumDao = presenter.getSingleAlbumDao();
  }

  @Override
  public void onError() {
    view.viewOnLoad(false);
    view.generateToast(context.getResources().getString(R.string.warning_no_data_fetched)).show();
  }

  public void onGetAlbumDetailsResponse(Response<SingleAlbum> response) {
    view.viewOnLoad(false);
    if (response.body() != null) {
      updateFragmentUi(response);
      loadListView(response);
    }
  }

  private void updateFragmentUi(Response<SingleAlbum> response) {
    String albumImage = response.body().album.getImages().get(3).getImageUrl();
    String albumTitle = response.body().album.getName();
    String albumArtist = response.body().album.getArtist();

    setSingleAlbumImage(albumImage);
    setSingleAlbumTitle(albumTitle);
    setSingleAlbumArtist(albumArtist);
  }


  private void setSingleAlbumImage(String imageUri) {
    Glide.with(context).load(imageUri).into(view.getSingleAlbumImageView());
  }

  private void setSingleAlbumTitle(String title) {
    view.getSingleAlbumTitleView().setText(title);
  }

  private void setSingleAlbumArtist(String artist) {
    view.getSingleAlbumArtistView().setText(artist);
  }

  private void loadListView(Response<SingleAlbum> response) {
    SingleAlbumTracksAdapter adapter =
            new SingleAlbumTracksAdapter(context, response.body().album.getTracks().getTrackList());
    view.fetchListView().setAdapter(adapter);
  }

  public void onBookmarkAlbumError() {
    view.generateToast(context.getResources().getString(R.string.warning_no_data_fetched)).show();
  }

  public void onBookmarkAlbumResponse(List<SingleAlbum> singleAlbums) {
    boolean isBookmarked = false;
    for (SingleAlbum a : singleAlbums) {
      if (a.getId().equals(getAlbumUniqueIdForInternalDb(presenter.getSingleAlbum()))) {
        isBookmarked = true;
        break;
      }
    }
    if (isBookmarked) {
      alreadyBookmarkedWarningToast();
    } else {
      addAlbumToBookmarks();
    }
  }

  private void alreadyBookmarkedWarningToast() {
    view.generateToast(context.getResources().getString(R.string.album_bookmarked_message_error)).show();
  }

  private void addAlbumToBookmarks() {
    presenter.getSingleAlbum().setId(getAlbumUniqueIdForInternalDb(presenter.getSingleAlbum()));
    singleAlbumDao.addAlbumToFavorites(presenter.getSingleAlbum());
    view.generateToast(context.getResources().getString(R.string.album_bookmarked_message))
            .show();
  }

  public void changeViewOnBookmarked(List<SingleAlbum> singleAlbums, Response<SingleAlbum> response) {
    for (SingleAlbum a : singleAlbums) {
      if (a.getId().equals(getAlbumUniqueIdForInternalDb(response.body()))) {
        view.changeBookmarkButtonColor(true);
        break;
      }
    }
  }

  private String getAlbumUniqueIdForInternalDb(SingleAlbum singleAlbum) {
    return singleAlbum.album.getName() +
            singleAlbum.album.getArtist() +
            singleAlbum.album.getMbid() +
            singleAlbum.album.getReleaseDate();
  }
}
