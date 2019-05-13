package com.groundzero.appfactory.music.view;

import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.groundzero.appfactory.common.di.components.FoundationComponent;

public interface BaseView {
  RecyclerView fetchRecyclerView();

  Toast generateToast(String message);

  FoundationComponent fetchFoundationComponent();
}
