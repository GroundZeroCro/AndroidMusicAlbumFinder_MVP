package com.groundzero.appfactory.search.presenters;

import android.content.Context;

import com.groundzero.appfactory.R;
import com.groundzero.appfactory.search.handlers.SearchResponseHandler;
import com.groundzero.appfactory.common.service.DisposableService;
import com.groundzero.appfactory.common.service.MusicService;
import com.groundzero.appfactory.search.views.SearchView;

import javax.inject.Inject;

import io.reactivex.disposables.Disposable;
import retrofit2.Retrofit;

public class SearchPresenter implements DisposableService {

  private static final String TAG = "tagger";
  @Inject
  Retrofit retrofit;
  @Inject
  Context context;
  private SearchView view;
  private MusicService musicService;
  private Disposable disposable;
  private SearchResponseHandler handler;

  public SearchPresenter(SearchView searchView) {
    this.view = searchView;
    view.fetchFoundationComponent().inject(this);
    this.musicService = new MusicService(retrofit);
    this.handler = new SearchResponseHandler(context, view);
  }

  public void onSearchBtnClick() {
    if (!getSearchInputText().isEmpty()) {
      searchArtists();
      this.view.hideKeyboard();
      view.viewOnLoad(true);
    } else {
      view.generateToast(context.getResources().getString(R.string.warning_valid_input_name)).show();
    }
  }

  private void searchArtists() {
    disposable = musicService.getArtists(getSearchInputText())
            .doOnError(throwable -> handler.onError())
            .subscribe(artistResponse -> handler.onSearchSuccess(artistResponse));
  }

  private String getSearchInputText() {
    return view.getSearchInputView().getText().toString();
  }

  @Override
  public void disposeSubscription() {
    if (disposable != null) {
      disposable.dispose();
    }
  }
}

