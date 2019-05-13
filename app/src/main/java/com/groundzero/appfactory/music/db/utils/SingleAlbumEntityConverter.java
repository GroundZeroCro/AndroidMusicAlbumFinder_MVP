package com.groundzero.appfactory.music.db.utils;

import androidx.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.groundzero.appfactory.music.models.SingleAlbum;

import java.lang.reflect.Type;

public class SingleAlbumEntityConverter {

  @TypeConverter
  public String fromOptionValuesList(SingleAlbum.Album optionValues) {
    if (optionValues == null) {
      return null;
    }
    Gson gson = new Gson();
    Type type = new TypeToken<SingleAlbum.Album>() {
    }.getType();

    return gson.toJson(optionValues, type);
  }

  @TypeConverter
  public SingleAlbum.Album toOptionValuesList(String optionValuesString) {
    if (optionValuesString == null) {
      return null;
    }
    Gson gson = new Gson();
    Type type = new TypeToken<SingleAlbum.Album>() {
    }.getType();
    return gson.fromJson(optionValuesString, type);
  }
}
