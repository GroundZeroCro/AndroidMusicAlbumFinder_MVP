package com.groundzero.appfactory.common.di.components;

import com.groundzero.appfactory.common.di.modules.FoundationModule;
import com.groundzero.appfactory.common.di.modules.InternalDbModule;
import com.groundzero.appfactory.common.di.modules.NetworkModule;
import com.groundzero.appfactory.music.presenter.FavoritesPresenter;
import com.groundzero.appfactory.music.presenter.SearchPresenter;
import com.groundzero.appfactory.music.presenter.SingleAlbumPresenter;
import com.groundzero.appfactory.music.presenter.TopAlbumsPresenter;
import com.groundzero.appfactory.music.service.MusicService;

import javax.inject.Singleton;

import dagger.Subcomponent;

@Singleton
@Subcomponent(modules = {FoundationModule.class, NetworkModule.class, InternalDbModule.class})
public interface FoundationComponent {

  void inject(SearchPresenter presenter);

  void inject(TopAlbumsPresenter presenter);

  void inject(SingleAlbumPresenter presenter);

  void inject(FavoritesPresenter presenter);

  void inject(MusicService musicService);
}
