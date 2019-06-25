package com.groundzero.appfactory.common.service;

import com.groundzero.appfactory.search.models.Artist;
import com.groundzero.appfactory.common.models.SingleAlbum;
import com.groundzero.appfactory.search.models.TopAlbums;
import com.groundzero.appfactory.common.network.MusicAPI;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MusicService {

  private static final String API_KEY = "ea03a1371e784907e193b5e2ed556a26";
  private static final String JSON_FORMAT = "json";

  private Retrofit retrofit;

  public MusicService(Retrofit retrofit) {
    this.retrofit = retrofit;
  }

  public Single<Response<Artist>> getArtists(String artistName) {
    return retrofit.create(MusicAPI.class)
            .getArtists("artist.search", artistName, API_KEY, JSON_FORMAT)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread());
  }

  public Single<Response<TopAlbums>> showTopAlbums(String artistName) {
    return retrofit
            .create(MusicAPI.class)
            .getTopAlbums("artist.gettopalbums", artistName, API_KEY, JSON_FORMAT)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread());
  }

  public Single<Response<SingleAlbum>> getAlbumDetails(String artistName, String albumName) {
    return retrofit
            .create(MusicAPI.class)
            .getSingleAlbum("album.getinfo", API_KEY, artistName, albumName, JSON_FORMAT)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread());
  }

}
