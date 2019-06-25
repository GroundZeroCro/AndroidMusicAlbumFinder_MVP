package com.groundzero.appfactory.search.views;

import android.widget.EditText;

import com.groundzero.appfactory.common.views.BaseView;
import com.groundzero.appfactory.common.views.LoadingView;

public interface SearchView extends BaseView, LoadingView {
  EditText getSearchInputView();

  void hideKeyboard();
}
