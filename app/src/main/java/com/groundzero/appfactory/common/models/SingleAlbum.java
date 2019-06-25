package com.groundzero.appfactory.common.models;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.google.gson.annotations.SerializedName;
import com.groundzero.appfactory.favorites.db.utils.SingleAlbumEntityConverter;

import java.util.List;

@Entity(tableName = "favorite_albums")
public class SingleAlbum {

  @SerializedName("album")
  @TypeConverters(SingleAlbumEntityConverter.class)
  public Album album;
  @NonNull
  @PrimaryKey
  private String id;

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public class Album {

    @SerializedName("name")
    private String name;
    @SerializedName("artist")
    private String artist;
    @SerializedName("mbid")
    private String mbid;
    @SerializedName("url")
    private String url;
    @SerializedName("releasedate")
    private String releaseDate;
    @SerializedName("listeners")
    private int listeners;
    @SerializedName("playcount")
    private int playCount;
    @SerializedName("tracks")
    private Tracks tracks;
    @SerializedName("image")
    private List<Image> images;


    public String getName() {
      return name;
    }

    public String getArtist() {
      return artist;
    }

    public String getMbid() {
      return mbid;
    }

    public String getUrl() {
      return url;
    }

    public String getReleaseDate() {
      return releaseDate;
    }

    public int getListeners() {
      return listeners;
    }

    public int getPlayCount() {
      return playCount;
    }

    public Tracks getTracks() {
      return tracks;
    }

    public List<Image> getImages() {
      return images;
    }

    public class Tracks {

      @SerializedName("track")
      private List<Track> trackList;

      public List<Track> getTrackList() {
        return trackList;
      }

      public class Track {
        @SerializedName("name")
        private String name;
        @SerializedName("duration")
        private String duration;

        public String getName() {
          return name;
        }

        public String getDuration() {
          return duration;
        }
      }
    }
  }
}

