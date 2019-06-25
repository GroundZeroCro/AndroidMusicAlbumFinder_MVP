package com.groundzero.appfactory.favorites.presenter;

import android.content.Context;

import com.groundzero.appfactory.favorites.db.dao.SingleAlbumDao;
import com.groundzero.appfactory.favorites.handlers.FavoritesResponseHandler;
import com.groundzero.appfactory.common.service.DisposableService;
import com.groundzero.appfactory.favorites.view.FavoritesView;

import javax.inject.Inject;

import io.reactivex.disposables.Disposable;

public class FavoritesPresenter implements DisposableService {

  private final FavoritesResponseHandler handler;
  @Inject
  Context context;
  @Inject
  SingleAlbumDao singleAlbumDao;
  private Disposable disposable;

  public FavoritesPresenter(FavoritesView view) {
    view.fetchFoundationComponent().inject(this);
    this.handler = new FavoritesResponseHandler(context, view);
  }

  public void getBookmarkedAlbums() {
    disposable = singleAlbumDao.getFavoriteAlbums()
            .doOnError(throwable -> handler.onError())
            .subscribe(handler::onLoadData);
  }

  public void deleteAlbumFromBookmarks(int itemPosition) {
    singleAlbumDao.deleteAlbumFromFavorites(handler.getSingleAlbums().get(itemPosition));
    getBookmarkedAlbums();
  }

  @Override
  public void disposeSubscription() {
    disposable.dispose();
  }
}
