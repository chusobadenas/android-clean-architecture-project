package com.chusobadenas.boilerplatecleanarchitecture.presentation.base;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import androidx.annotation.Nullable;
import com.chusobadenas.boilerplatecleanarchitecture.R;
import com.chusobadenas.boilerplatecleanarchitecture.common.util.DialogFactory;
import com.chusobadenas.boilerplatecleanarchitecture.common.util.DialogFactory.DialogType;
import dagger.hilt.android.AndroidEntryPoint;

/**
 * Base {@link Activity} class for MVP views.
 */
@AndroidEntryPoint
public abstract class BaseMvpActivity extends BaseActivity implements MvpView {

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
    String title = getString(R.string.error_title_generic);
    DialogFactory.showDialog(this, DialogType.SIMPLE, title, message, android.R.string.ok, action);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Context context() {
    return this;
  }
}
