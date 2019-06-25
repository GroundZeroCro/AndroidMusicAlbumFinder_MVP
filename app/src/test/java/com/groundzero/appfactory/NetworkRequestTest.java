package com.groundzero.appfactory;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.groundzero.appfactory.common.service.MusicService;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;

import io.reactivex.android.plugins.RxAndroidPlugins;
import io.reactivex.plugins.RxJavaPlugins;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class NetworkRequestTest {

  private static final String BASE_URL = "http://ws.audioscrobbler.com/2.0/";
  private static final String ARTIST_NAME = "cher";
  private static final String ALBUM_NAME = "believe";

  @Before
  public void setUp() {
    RxAndroidPlugins.reset();
    RxJavaPlugins.reset();
    RxJavaPlugins.setIoSchedulerHandler(scheduler -> Schedulers.trampoline());
    RxAndroidPlugins.setInitMainThreadSchedulerHandler(schedulerCallable -> Schedulers.trampoline());
  }

  @Test
  public void musicServiceRequestTest() {
    MusicService musicService = new MusicService(provideRetrofit());

    musicService.getAlbumDetails(ARTIST_NAME, ALBUM_NAME).test()
            .assertNoErrors()
            .assertValue(response -> {
              assert response.code() == 200;
              return true;
            })
            .awaitTerminalEvent();

    musicService.getArtists(ARTIST_NAME).test()
            .assertNoErrors()
            .assertValue(artistResponse -> {
              assert artistResponse.code() == 200;
              return true;
            });

    musicService.showTopAlbums(ARTIST_NAME).test()
            .assertNoErrors()
            .assertValue(artistResponse -> {
              assert artistResponse.code() == 200;
              return true;
            });
  }

  private Retrofit provideRetrofit() {
    return new Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create(provideGson()))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .baseUrl(BASE_URL)
            .build();
  }

  private Gson provideGson() {
    return new GsonBuilder()
            .setLenient().create();
  }

  @AfterClass
  public static void after() {
    RxAndroidPlugins.reset();
    RxJavaPlugins.reset();
  }
}
