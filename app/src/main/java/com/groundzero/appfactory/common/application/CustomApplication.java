package com.groundzero.appfactory.common.application;

import android.app.Application;

import androidx.annotation.UiThread;

import com.groundzero.appfactory.common.di.components.ApplicationComponent;
import com.groundzero.appfactory.common.di.components.DaggerApplicationComponent;

public class CustomApplication extends Application {

  private ApplicationComponent mApplicationComponent;

  @Override
  public void onCreate() {
    getApplicationComponent().inject(this);
    super.onCreate();
  }

  @UiThread
  public ApplicationComponent getApplicationComponent() {
    if (mApplicationComponent == null) {
      mApplicationComponent =
              DaggerApplicationComponent.builder().build();
    }
    return mApplicationComponent;
  }
}
