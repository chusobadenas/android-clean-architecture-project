package com.chusobadenas.boilerplatecleanarchitecture.common.exception;

import android.content.Context;

import com.chusobadenas.boilerplatecleanarchitecture.R;

/**
 * Wrapper around Exceptions used to manage default errors.
 */
public class DefaultErrorBundle implements ErrorBundle {

  private final Context context;
  private final Integer errorMsgId;
  private Exception exception;

  /**
   * Constructor
   *
   * @param context    application context
   * @param throwable  the exception
   * @param errorMsgId resource id of the error message
   */
  public DefaultErrorBundle(Context context, Throwable throwable, Integer errorMsgId) {
    this.context = context;
    this.errorMsgId = errorMsgId;
    if (throwable instanceof Exception) {
      this.exception = (Exception) throwable;
    }
  }

  @Override
  public Exception getException() {
    return exception;
  }

  @Override
  public String getErrorMessage() {
    String message;

    if (errorMsgId == null) {
      message = context.getString(R.string.error_message_generic);
    } else {
      message = context.getString(errorMsgId);
    }

    return message;
  }
}
