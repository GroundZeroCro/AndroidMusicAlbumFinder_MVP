package com.groundzero.appfactory.common.di.modules;

import android.app.Activity;
import android.content.Context;

import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;

import dagger.Module;
import dagger.Provides;

@Module
public class FoundationModule {

  private final FragmentActivity mFragmentActivity;

  public FoundationModule(FragmentActivity fragmentActivity) {
    this.mFragmentActivity = fragmentActivity;
  }

  @Provides
  Context provideContext() {
    return mFragmentActivity;
  }

  @Provides
  Activity provideActivity() {
    return mFragmentActivity;
  }

  @Provides
  FragmentManager provideFragmentManager() {
    return mFragmentActivity.getSupportFragmentManager();
  }
}
