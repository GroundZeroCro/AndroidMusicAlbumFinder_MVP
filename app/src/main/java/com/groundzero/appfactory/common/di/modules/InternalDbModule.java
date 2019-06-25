package com.groundzero.appfactory.common.di.modules;

import android.content.Context;

import androidx.room.Room;

import com.groundzero.appfactory.favorites.db.dao.SingleAlbumDao;
import com.groundzero.appfactory.favorites.db.logic.SingleAlbumDatabase;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class InternalDbModule {

  @Singleton
  @Provides
  public SingleAlbumDatabase provideAlbumDatabase(Context context) {
    return Room.databaseBuilder(context, SingleAlbumDatabase.class, SingleAlbumDatabase.DATABASE_NAME)
            .fallbackToDestructiveMigration()
            .allowMainThreadQueries()
            .build();
  }

  @Singleton
  @Provides
  public SingleAlbumDao provideAlbumDao(SingleAlbumDatabase singleAlbumDatabase) {
    return singleAlbumDatabase.getAlbumDao();
  }
}
