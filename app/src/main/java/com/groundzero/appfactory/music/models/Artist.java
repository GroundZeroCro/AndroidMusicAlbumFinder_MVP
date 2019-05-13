package com.groundzero.appfactory.music.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Artist {

  @SerializedName("results")
  private Results results;

  public Results getResults() {
    return results;
  }

  public class Results {

    @SerializedName("artistmatches")
    private ArtistMatches artistmatches;

    public ArtistMatches getArtistmatches() {
      return artistmatches;
    }

    public class ArtistMatches {

      @SerializedName("artist")
      List<ArtistDetails> artist;

      public List<ArtistDetails> getArtist() {
        return artist;
      }
    }

    public class ArtistDetails {

      @SerializedName("name")
      private String name;
      @SerializedName("mbid")
      private String mbid;
      @SerializedName("url")
      private String url;
      @SerializedName("image_small")
      private String imageSmall;
      @SerializedName("streamable")
      private String streamable;
      @SerializedName("image")
      private List<Image> images;

      public String getName() {
        return name;
      }

      public String getMbid() {
        return mbid;
      }

      public String getUrl() {
        return url;
      }

      public String getImageSmall() {
        return imageSmall;
      }

      public String getStreamable() {
        return streamable;
      }

      public List<Image> getImages() {
        return images;
      }
    }
  }
}
