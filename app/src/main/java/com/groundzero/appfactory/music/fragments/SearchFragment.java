package com.groundzero.appfactory.music.fragments;


import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.groundzero.appfactory.R;
import com.groundzero.appfactory.common.di.components.FoundationComponent;
import com.groundzero.appfactory.music.presenter.SearchPresenter;
import com.groundzero.appfactory.music.view.SearchView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SearchFragment extends BaseFragment implements SearchView {

  @BindView(R.id.search_recycler_view)
  RecyclerView searchRecyclerView;
  @BindView(R.id.search_input)
  EditText searchInput;
  @BindView(R.id.search_button)
  Button searchButton;
  @BindView(R.id.progress_bar)
  ProgressBar progressBar;

  private View mView;

  private SearchPresenter presenter;

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
                           Bundle savedInstanceState) {
    mView = inflater.inflate(R.layout.fragment_search, container, false);
    ButterKnife.bind(this, mView);
    setRecyclerPreferences();
    presenter = new SearchPresenter(this);

    return mView;
  }

  private void setRecyclerPreferences() {
    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
    linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
    searchRecyclerView.setHasFixedSize(true);
    searchRecyclerView.setLayoutManager(linearLayoutManager);
  }

  @Override
  public RecyclerView fetchRecyclerView() {
    return searchRecyclerView;
  }

  @Override
  public EditText getSearchInputView() {
    return searchInput;
  }

  @Override
  public Toast generateToast(String message) {
    return Toast.makeText(getContext(), message, Toast.LENGTH_LONG);
  }

  @Override
  public FoundationComponent fetchFoundationComponent() {
    return getFoundationComponent();
  }

  @Override
  public void hideKeyboard() {
    InputMethodManager inputMethodManager = (InputMethodManager) getActivity().getSystemService(Activity.INPUT_METHOD_SERVICE);
    if (inputMethodManager != null) {
      inputMethodManager.hideSoftInputFromWindow(mView.getWindowToken(), 0);
    }
  }

  @OnClick(R.id.search_button)
  void submitButton() {
    presenter.onSearchBtnClick();
  }

  @Override
  public void onStop() {
    super.onStop();
    presenter.disposeSubscription();
  }

  @Override
  public void viewOnLoad(boolean isDataLoading) {
    if (isDataLoading) {
      progressBar.setVisibility(View.VISIBLE);
    } else {
      progressBar.setVisibility(View.GONE);
    }
  }
}
