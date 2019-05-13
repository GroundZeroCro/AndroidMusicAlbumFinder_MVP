package com.groundzero.appfactory.music.models;

import com.google.gson.annotations.SerializedName;

public class Image {

  @SerializedName("#text")
  private String imageUrl;
  @SerializedName("size")
  private String imageSize;

  public String getImageUrl() {
    return imageUrl;
  }

  public String getImageSize() {
    return imageSize;
  }
}
