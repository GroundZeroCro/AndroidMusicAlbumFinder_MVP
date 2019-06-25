package com.groundzero.appfactory.search.dialogs;

import android.app.AlertDialog;
import android.content.Context;

import com.groundzero.appfactory.R;
import com.groundzero.appfactory.common.presenter.SingleAlbumPresenter;

public class ChoiceDialog {

  private Context context;
  private SingleAlbumPresenter presenter;

  public ChoiceDialog(Context context, SingleAlbumPresenter presenter) {
    this.context = context;
    this.presenter = presenter;
  }

  public AlertDialog generateDialog() {
    return new AlertDialog.Builder(context)
            .setTitle(context.getResources().getString(R.string.dialog_title_bookmark_album))
            .setMessage(context.getResources().getString(R.string.dialog_msg_bookmark_album))
            .setPositiveButton("Accept", (dialogInterface, i) -> {
              presenter.bookmarkAlbum();
            })
            .setNegativeButton("Cancel", (dialogInterface, i) -> dialogInterface.dismiss())
            .create();
  }
}
