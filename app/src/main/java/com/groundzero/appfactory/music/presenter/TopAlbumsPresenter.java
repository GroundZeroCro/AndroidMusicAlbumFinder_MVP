package com.groundzero.appfactory.music.presenter;

import android.content.Context;

import com.groundzero.appfactory.music.handlers.TopAlbumsResponseHandler;
import com.groundzero.appfactory.music.service.DisposableService;
import com.groundzero.appfactory.music.service.MusicService;
import com.groundzero.appfactory.music.view.TopAlbumsView;

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
