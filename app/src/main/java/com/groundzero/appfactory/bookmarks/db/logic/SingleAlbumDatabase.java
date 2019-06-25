package com.groundzero.appfactory.bookmarks.db.logic;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.groundzero.appfactory.bookmarks.db.dao.SingleAlbumDao;
import com.groundzero.appfactory.common.models.SingleAlbum;

@Database(entities = {SingleAlbum.class}, version = 1, exportSchema = false)
public abstract class SingleAlbumDatabase extends RoomDatabase {

  public static final String DATABASE_NAME = "music";

  public abstract SingleAlbumDao getAlbumDao();
}
