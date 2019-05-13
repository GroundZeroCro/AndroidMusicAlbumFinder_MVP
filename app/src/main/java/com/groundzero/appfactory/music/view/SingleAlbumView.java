package com.groundzero.appfactory.music.view;

import android.app.AlertDialog;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public interface SingleAlbumView extends LoadingView {

  ListView fetchListView();

  Bundle fetchPassingBundle();

  ImageView getSingleAlbumImageView();

  TextView getSingleAlbumTitleView();

  TextView getSingleAlbumArtistView();

  Toast generateToast(String message);

  void changeBookmarkButtonColor(boolean isBookmarked);

  AlertDialog generateChoiceDialog();
}
