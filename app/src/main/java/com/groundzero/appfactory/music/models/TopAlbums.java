package com.groundzero.appfactory.music.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class TopAlbums {

  @SerializedName("topalbums")
  private Album topAlbums;

  public Album getTopAlbums() {
    return topAlbums;
  }

  public class Album {

    @SerializedName("album")
    private List<AlbumDetails> albumDetails;
    @SerializedName("@attr")
    private AlbumArtist artist;

    public List<AlbumDetails> getAlbumDetails() {
      return albumDetails;
    }

    public AlbumArtist getArtist() {
      return artist;
    }

    public class AlbumArtist {
      @SerializedName("artist")
      private String artist;

      public String getArtist() {
        return artist;
      }
    }


    public class AlbumDetails {

      @SerializedName("name")
      private String name;
      @SerializedName("mbid")
      private String mbid;
      @SerializedName("listeners")
      private int listeners;
      @SerializedName("url")
      private String url;
      @SerializedName("image")
      private List<Image> images;

      public String getName() {
        return name;
      }

      public String getMbid() {
        return mbid;
      }

      public int getListeners() {
        return listeners;
      }

      public String getUrl() {
        return url;
      }

      public List<Image> getImages() {
        return images;
      }
    }
  }
}


