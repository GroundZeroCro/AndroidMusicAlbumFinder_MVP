package com.groundzero.appfactory.search.presenters;

import android.content.Context;

import com.groundzero.appfactory.search.handlers.TopAlbumsResponseHandler;
import com.groundzero.appfactory.common.service.DisposableService;
import com.groundzero.appfactory.common.service.MusicService;
import com.groundzero.appfactory.search.views.TopAlbumsView;

import javax.inject.Inject;

import io.reactivex.disposables.Disposable;
import retrofit2.Retrofit;

public class TopAlbumsPresenter implements DisposableService {

  @Inject
  Retrofit retrofit;
  @Inject
  Context context;
  private TopAlbumsView view;
  private MusicService musicService;
  private Disposable disposable;
  private TopAlbumsResponseHandler handler;

  public TopAlbumsPresenter(TopAlbumsView view) {
    this.view = view;
    view.fetchFoundationComponent().inject(this);
    this.musicService = new MusicService(retrofit);
    this.handler = new TopAlbumsResponseHandler(context, view);
  }

  public void showArtistTopAlbums() {
    disposable = musicService.showTopAlbums(getPassedArgumentArtistName())
            .doOnError(throwable -> handler.onError())
            .subscribe(response -> handler.topAlbumResponseSuccess(response));
  }

  public String getPassedArgumentArtistName() {
    return view.getPassedArguments().getString("artistName");
  }

  @Override
  public void disposeSubscription() {
    disposable.dispose();
  }
}
