package com.groundzero.appfactory.common.fragments;

import androidx.annotation.UiThread;
import androidx.fragment.app.Fragment;

import com.groundzero.appfactory.common.application.CustomApplication;
import com.groundzero.appfactory.common.di.components.FoundationComponent;
import com.groundzero.appfactory.common.di.modules.FoundationModule;
import com.groundzero.appfactory.common.di.modules.InternalDbModule;
import com.groundzero.appfactory.common.di.modules.NetworkModule;

public class BaseFragment extends Fragment {

  @UiThread
  public FoundationComponent getFoundationComponent() {
    return ((CustomApplication) getActivity().getApplication())
            .getApplicationComponent()
            .newFoundationComponent(
                    new FoundationModule(getActivity()),
                    new NetworkModule(),
                    new InternalDbModule()
            );
  }
}
