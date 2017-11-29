/**
 * Copyright (C) 2015 Fernando Cejas Open Source Project
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
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
