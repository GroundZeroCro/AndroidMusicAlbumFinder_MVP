package com.groundzero.appfactory.music.handlers;

import android.content.Context;

import com.groundzero.appfactory.R;
import com.groundzero.appfactory.music.adapters.SearchAdapter;
import com.groundzero.appfactory.music.models.Artist;
import com.groundzero.appfactory.music.view.SearchView;

import retrofit2.Response;

public class SearchResponseHandler extends ResponseHandler<SearchView> {

  public SearchResponseHandler(Context context, SearchView view) {
    super(context, view);
  }

  @Override
  public void onError() {
    view.generateToast(context.getResources().getString(R.string.warning_no_data_fetched)).show();
    view.viewOnLoad(false);
  }

  public void onSearchSuccess(Response<Artist> artistResponse) {
    if (artistResponse.isSuccessful()) {
      SearchAdapter searchAdapter = new SearchAdapter(context, artistResponse.body().getResults().getArtistmatches().getArtist());
      view.fetchRecyclerView().setAdapter(searchAdapter);
      view.viewOnLoad(false);
    }
  }
}
