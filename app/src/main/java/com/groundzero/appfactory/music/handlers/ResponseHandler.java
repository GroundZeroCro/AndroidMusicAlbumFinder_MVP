package com.groundzero.appfactory.music.handlers;

import android.content.Context;

public abstract class ResponseHandler<T> {

  protected Context context;
  protected T view;

  public ResponseHandler(Context context, T view) {
    this.context = context;
    this.view = view;
  }

  public abstract void onError();
}
