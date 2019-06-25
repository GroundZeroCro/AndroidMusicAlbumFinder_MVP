package com.groundzero.appfactory.bookmarks.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.groundzero.appfactory.R;
import com.groundzero.appfactory.bookmarks.presenter.FavoritesPresenter;
import com.groundzero.appfactory.bookmarks.view.FavoritesView;
import com.groundzero.appfactory.common.di.components.FoundationComponent;
import com.groundzero.appfactory.common.fragments.BaseFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FavoritesFragment extends BaseFragment implements FavoritesView {

  @BindView(R.id.favorites_subtitle)
  TextView favoritesSubtitle;
  @BindView(R.id.favorites_recycler_view)
  RecyclerView favoritesRecyclerView;

  private FavoritesPresenter presenter;

  @Override
  public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                           Bundle savedInstanceState) {
    View mView = inflater.inflate(R.layout.fragment_favorites, container, false);
    ButterKnife.bind(this, mView);
    setRecyclerPreferences();
    presenter = new FavoritesPresenter(this);
    return mView;
  }

  private void setRecyclerPreferences() {

    ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(200, 200) {
      @Override
      public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
        return false;
      }

      @Override
      public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
        presenter.deleteAlbumFromBookmarks(viewHolder.getLayoutPosition());
      }
    });

    itemTouchHelper.attachToRecyclerView(favoritesRecyclerView);

    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
    linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
    favoritesRecyclerView.setHasFixedSize(true);
    favoritesRecyclerView.setLayoutManager(linearLayoutManager);
  }

  @Override
  public RecyclerView fetchRecyclerView() {
    return favoritesRecyclerView;
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
  public void onStart() {
    super.onStart();
    presenter.getBookmarkedAlbums();
  }

  @Override
  public void onStop() {
    super.onStop();
    presenter.disposeSubscription();
  }

  @Override
  public void setFavoritesSubtitle(boolean hasBookmarks) {
    if (hasBookmarks) {
      favoritesSubtitle.setText(getContext().getResources().getString(R.string.album_bookmarks_subtitle));
    } else {
      favoritesSubtitle.setText(getContext().getResources().getString(R.string.empty_album_bookmarks_subtitle));
    }
  }
}
