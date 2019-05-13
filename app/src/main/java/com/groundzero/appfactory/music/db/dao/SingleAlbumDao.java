package com.groundzero.appfactory.music.db.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.groundzero.appfactory.music.models.SingleAlbum;

import java.util.List;

import io.reactivex.Single;

@Dao
public interface SingleAlbumDao {


  @Insert
  void addAlbumToFavorites(SingleAlbum singleAlbums);

  @Delete
  void deleteAlbumFromFavorites(SingleAlbum singleAlbums);

  @Query("SELECT * FROM 'favorite_albums'")
  Single<List<SingleAlbum>> getFavoriteAlbums();
}
