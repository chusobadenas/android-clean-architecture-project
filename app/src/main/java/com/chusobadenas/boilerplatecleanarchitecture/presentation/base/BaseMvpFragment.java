package com.chusobadenas.boilerplatecleanarchitecture.presentation.base;

import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import com.chusobadenas.boilerplatecleanarchitecture.R;
import com.chusobadenas.boilerplatecleanarchitecture.common.util.DialogFactory;
import com.chusobadenas.boilerplatecleanarchitecture.common.util.DialogFactory.DialogType;

/**
 * Base {@link Fragment} class for MVP views.
 */
public abstract class BaseMvpFragment extends BaseFragment implements MvpView {

  /**
   * {@inheritDoc}
   */
  @Override
  public void showLoading() {
    // No-op by default
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void hideLoading() {
    // No-op by default
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void showRetry() {
    // No-op by default
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void hideRetry() {
    // No-op by default
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void showError(String message, @Nullable DialogInterface.OnClickListener action) {
    String title = context().getString(R.string.error_title_generic);
    DialogFactory.showDialog(context(), DialogType.SIMPLE, title, message, android.R.string.ok, action);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Context context() {
    return getActivity();
  }
}