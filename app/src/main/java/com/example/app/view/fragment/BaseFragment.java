/*
 *     Copyright (c) 2015-present Victor Hidalgo Lorenzo
 *
 *     Licensed under the Apache License, Version 2.0 (the "License");
 *     you may not use this file except in compliance with the License.
 *     You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *     Unless required by applicable law or agreed to in writing, software
 *     distributed under the License is distributed on an "AS IS" BASIS,
 *     WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *     See the License for the specific language governing permissions and
 *     limitations under the License.
 */

package com.example.app.view.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.View;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Base {@link android.support.v4.app.Fragment} class for every Fragment in this application.
 */
public abstract class BaseFragment extends Fragment {

  private Unbinder unbinder;

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setRetainInstance(true);
  }

  @Override
  public void onViewCreated(
      View view,
      @Nullable
      Bundle savedInstanceState
  ) {
    super.onViewCreated(view, savedInstanceState);
    unbinder = ButterKnife.bind(this, view);
  }

  @Override
  public void onDestroyView() {
    if (unbinder != null) {
      unbinder.unbind();
      unbinder = null;
    }
    super.onDestroyView();
  }

  /**
   * Shows a {@link android.support.design.widget.Snackbar} message.
   *
   * @param view A view to attach the snackbar.
   * @param message A string representing a message to be shown.
   */
  protected void showSnackbarMessage(View view, String message) {
    Snackbar.make(view, message, Snackbar.LENGTH_LONG).show();
  }

  /**
   * Shows a {@link android.support.design.widget.Snackbar} message.
   *
   * @param message A string representing a message to be shown.
   */
  protected void showSnackbarMessage(String message) {
    if (getView() != null) {
      Snackbar.make(getView(), message, Snackbar.LENGTH_LONG).show();
    }
  }

  /**
   * Shows a {@link android.support.design.widget.Snackbar} message.
   *
   * @param view A view to attach the snackbar.
   * @param message A string representing a message to be shown.
   * @param duration The duration that the snackbar will be shown
   */
  protected void showSnackbarMessage(View view, String message, int duration) {
    if (view != null) {
      Snackbar.make(view, message, duration).show();
    }
  }

  /**
   * Shows a {@link android.support.design.widget.Snackbar} message.
   *
   * @param message A string representing a message to be shown.
   * @param duration The duration that the snackbar will be shown
   */
  protected void showSnackbarMessage(String message, int duration) {
    if (getView() != null) {
      Snackbar.make(getView(), message, duration).show();
    }
  }

  public Context getContext() {
    return getActivity().getApplicationContext();
  }
}
