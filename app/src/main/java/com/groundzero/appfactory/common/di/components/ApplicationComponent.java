package com.groundzero.appfactory.common.di.components;

import android.app.Application;

import com.groundzero.appfactory.common.di.modules.ApplicationModule;
import com.groundzero.appfactory.common.di.modules.FoundationModule;
import com.groundzero.appfactory.common.di.modules.InternalDbModule;
import com.groundzero.appfactory.common.di.modules.NetworkModule;

import dagger.Component;

@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {

  void inject(Application application);

  FoundationComponent newFoundationComponent(
          FoundationModule foundationModule,
          NetworkModule networkModule,
          InternalDbModule internalDbModule
  );
}
