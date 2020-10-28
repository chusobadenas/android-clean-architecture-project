package com.chusobadenas.boilerplatecleanarchitecture.presentation.base;

import android.content.Context;
import android.content.DialogInterface;
import androidx.annotation.Nullable;

/**
 * Base interface that any class that wants to act as a View in the MVP (Model View Presenter)
 * pattern must implement. Generally this interface will be extended by a more specific interface
 * that then usually will be implemented by an Activity or Fragment.
 */
public interface MvpView {

  /**
   * Show a view with a progress bar indicating a loading process.
   */
  void showLoading();

  /**
   * Hide a loading view.
   */
  void hideLoading();

  /**
   * Show a retry view in case of an error when retrieving data.
   */
  void showRetry();

  /**
   * Hide a retry view shown if there was an error when retrieving data.
   */
  void hideRetry();

  /**
   * Shows an error message
   *
   * @param message A string representing an error.
   * @param action  the action to perform (optional).
   */
  void showError(String message, @Nullable DialogInterface.OnClickListener action);

  /**
   * Get a {@link android.content.Context}.
   */
  Context context();
}
