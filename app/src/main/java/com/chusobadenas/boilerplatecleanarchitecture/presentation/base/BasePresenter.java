package com.chusobadenas.boilerplatecleanarchitecture.presentation.base;

import android.content.DialogInterface;

import com.chusobadenas.boilerplatecleanarchitecture.common.exception.DefaultErrorBundle;

import timber.log.Timber;

/**
 * Base class that implements the Presenter interface and provides a base implementation for
 * attachView() and detachView(). It also handles keeping a reference to the mvpView that
 * can be accessed from the children classes by calling getMvpView().
 */
public class BasePresenter<T extends MvpView> implements Presenter<T> {

  private T mvpView;

  @Override
  public void attachView(T mvpView) {
    this.mvpView = mvpView;
  }

  @Override
  public void detachView() {
    mvpView = null;
  }

  private boolean isViewAttached() {
    return mvpView != null;
  }

  public T getMvpView() {
    return mvpView;
  }

  public void checkViewAttached() {
    if (!isViewAttached()) {
      throw new MvpViewNotAttachedException();
    }
  }

  private void showError(Throwable throwable, String logMessage, Integer errorMsgId,
                         DialogInterface.OnClickListener action) {
    // Show log message
    Timber.e(throwable, logMessage);
    // Show dialog
    DefaultErrorBundle errorBundle = new DefaultErrorBundle(mvpView.context(), throwable, errorMsgId);
    mvpView.showError(errorBundle.getErrorMessage(), action);
  }

  /**
   * Shows an error message in the view
   *
   * @param throwable  the exception
   * @param logMessage the message to show in the logs
   * @param errorMsgId to customize the error message to show (optional)
   */
  public void showErrorMessage(Throwable throwable, String logMessage, Integer errorMsgId) {
    showError(throwable, logMessage, errorMsgId, null);
  }

  /**
   * Shows an error message in the view with an associated action
   *
   * @param throwable  the exception
   * @param logMessage the message to show in the logs
   * @param errorMsgId to customize the error message to show (optional)
   * @param action     the action
   */
  public void showErrorMessage(Throwable throwable, String logMessage, Integer errorMsgId,
                               DialogInterface.OnClickListener action) {
    showError(throwable, logMessage, errorMsgId, action);
  }

  private static class MvpViewNotAttachedException extends RuntimeException {

    MvpViewNotAttachedException() {
      super("Please call Presenter.attachView(MvpView) before requesting data to the Presenter");
    }
  }
}
