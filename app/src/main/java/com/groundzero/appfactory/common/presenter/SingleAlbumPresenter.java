package com.groundzero.appfactory.common.presenter;

import android.content.Context;

import com.groundzero.appfactory.common.di.components.FoundationComponent;
import com.groundzero.appfactory.favorites.db.dao.SingleAlbumDao;
import com.groundzero.appfactory.common.handlers.SingleAlbumResponseHandler;
import com.groundzero.appfactory.common.models.SingleAlbum;
import com.groundzero.appfactory.common.service.DisposableService;
import com.groundzero.appfactory.common.service.MusicService;
import com.groundzero.appfactory.common.views.SingleAlbumView;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import retrofit2.Response;
import retrofit2.Retrofit;

public class SingleAlbumPresenter implements DisposableService {

  private final MusicService musicService;
  private final SingleAlbumResponseHandler handler;
  @Inject
  Retrofit retrofit;
  @Inject
  Context context;
  @Inject
  SingleAlbumDao singleAlbumDao;
  private SingleAlbumView view;
  private CompositeDisposable disposables = new CompositeDisposable();
  private SingleAlbum singleAlbum;

  public SingleAlbumPresenter(SingleAlbumView view, FoundationComponent foundationComponent) {
    this.view = view;
    foundationComponent.inject(this);
    musicService = new MusicService(retrofit);
    handler = new SingleAlbumResponseHandler(this);
  }

  public void onBookmarkClick() {
    view.generateChoiceDialog().show();
  }

  public void bookmarkAlbum() {
    Disposable disposable = singleAlbumDao.getFavoriteAlbums()
            .doOnError(throwable -> handler.onBookmarkAlbumError())
            .subscribe(singleAlbums -> {
              getAlbumDetails(getArtistName(), getAlbumName());
              handler.onBookmarkAlbumResponse(singleAlbums);
            });
    disposables.add(disposable);
  }

  public void getAlbumDetails(String artistName, String artistAlbum) {
    Disposable disposable = musicService.getAlbumDetails(artistName, artistAlbum)
            .doOnError(throwable -> handler.onError())
            .subscribe(response -> {
              singleAlbum = response.body();
              handler.onGetAlbumDetailsResponse(response);
              compareBookmarkedWithNewlyBookmarked(response);
              setSingleAlbum(response);
            });
    disposables.add(disposable);
  }

  private void compareBookmarkedWithNewlyBookmarked(Response<SingleAlbum> response) {
    Disposable disposable = singleAlbumDao.getFavoriteAlbums()
            .doOnError(throwable -> handler.onBookmarkAlbumError())
            .subscribe(singleAlbums -> handler.changeViewOnBookmarked(singleAlbums, response));
    disposables.add(disposable);
  }

  public String getArtistName() {
    return view.fetchPassingBundle().getString("artistName");
  }

  public String getAlbumName() {
    return view.fetchPassingBundle().getString("albumName");
  }

  @Override
  public void disposeSubscription() {
    disposables.clear();
  }

  public SingleAlbumView getView() {
    return view;
  }

  public Context getContext() {
    return context;
  }

  public SingleAlbumDao getSingleAlbumDao() {
    return singleAlbumDao;
  }

  public SingleAlbum getSingleAlbum() {
    return singleAlbum;
  }

  private void setSingleAlbum(Response<SingleAlbum> response) {
    this.singleAlbum = response.body();
  }
}
