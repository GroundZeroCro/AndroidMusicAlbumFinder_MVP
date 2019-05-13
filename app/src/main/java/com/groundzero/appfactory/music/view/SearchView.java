package com.groundzero.appfactory.music.view;

import android.widget.EditText;

public interface SearchView extends BaseView, LoadingView {
  EditText getSearchInputView();

  void hideKeyboard();
}
