package com.groundzero.appfactory.search.fragments;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.groundzero.appfactory.R;
import com.groundzero.appfactory.common.di.components.FoundationComponent;
import com.groundzero.appfactory.common.fragments.BaseFragment;
import com.groundzero.appfactory.search.presenters.TopAlbumsPresenter;
import com.groundzero.appfactory.search.views.TopAlbumsView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TopAlbumsFragment extends BaseFragment implements TopAlbumsView {

  @BindView(R.id.top_albums_subtitle)
  TextView topAlbumsSubtitle;
  @BindView(R.id.top_albums_recycler_view)
  RecyclerView topAlbumsRecyclerView;
  private TopAlbumsPresenter presenter;

  @Override
  public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                           Bundle savedInstanceState) {
    View mView = inflater.inflate(R.layout.fragment_top_albums, container, false);
    ButterKnife.bind(this, mView);
    setRecyclerPreferences();
    presenter = new TopAlbumsPresenter(this);
    setTopAlbumsSubtitle();
    return mView;
  }

  private void setRecyclerPreferences() {
    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
    linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
    topAlbumsRecyclerView.setHasFixedSize(true);
    topAlbumsRecyclerView.setLayoutManager(linearLayoutManager);
  }

  private void setTopAlbumsSubtitle() {
    topAlbumsSubtitle.setText(
            getContext().getString(R.string.top_albums_subtitle) + " - " +
                    presenter.getPassedArgumentArtistName());
  }

  @Override
  public RecyclerView fetchRecyclerView() {
    return topAlbumsRecyclerView;
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
  public Bundle getPassedArguments() {
    return getArguments();
  }

  @Override
  public void onStart() {
    super.onStart();
    presenter.showArtistTopAlbums();
  }

  @Override
  public void onStop() {
    super.onStop();
    presenter.disposeSubscription();
  }
}
