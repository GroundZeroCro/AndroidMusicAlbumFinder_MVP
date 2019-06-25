package com.groundzero.appfactory.common.network;

import com.groundzero.appfactory.search.models.Artist;
import com.groundzero.appfactory.common.models.SingleAlbum;
import com.groundzero.appfactory.search.models.TopAlbums;

import io.reactivex.Single;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MusicAPI {

  String URL_ADD = "/2.0/";

  @GET(URL_ADD)
  Single<Response<Artist>> getArtists(
          @Query("method") String method,
          @Query("artist") String artist,
          @Query("api_key") String key,
          @Query("format") String format
  );

  @GET(URL_ADD)
  Single<Response<TopAlbums>> getTopAlbums(
          @Query("method") String method,
          @Query("artist") String artist,
          @Query("api_key") String key,
          @Query("format") String format
  );

  @GET(URL_ADD)
  Single<Response<SingleAlbum>> getSingleAlbum(
          @Query("method") String method,
          @Query("api_key") String key,
          @Query("artist") String artist,
          @Query("album") String album,
          @Query("format") String format
  );
}