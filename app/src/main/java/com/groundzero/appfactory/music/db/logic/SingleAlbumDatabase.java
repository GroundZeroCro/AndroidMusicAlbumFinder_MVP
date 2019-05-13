package com.groundzero.appfactory.music.db.logic;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.groundzero.appfactory.music.db.dao.SingleAlbumDao;
import com.groundzero.appfactory.music.models.SingleAlbum;

@Database(entities = {SingleAlbum.class}, version = 1, exportSchema = false)
public abstract class SingleAlbumDatabase extends RoomDatabase {

  public static final String DATABASE_NAME = "music";

  public abstract SingleAlbumDao getAlbumDao();
}
