package com.groundzero.appfactory.common.utils;

import android.os.Bundle;
import android.view.View;

import androidx.navigation.Navigation;

public class FragmentNavigation {

  public static void nextFragment(View view, int fragmentId, String[][] bundleKeyValue) {
    Navigation.findNavController(view).navigate(fragmentId, passingBundle(bundleKeyValue));
  }

  private static Bundle passingBundle(String[][] bundleKeyValue) {
    Bundle bundle = new Bundle();
    for (String[] i : bundleKeyValue) {
      bundle.putString(i[0], i[1]);
    }
    return bundle;
  }
}
