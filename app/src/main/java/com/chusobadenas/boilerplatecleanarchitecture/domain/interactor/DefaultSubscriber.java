package com.chusobadenas.boilerplatecleanarchitecture.domain.interactor;

import io.reactivex.observers.DisposableObserver;

/**
 * Default subscriber base class to be used whenever you want default error handling.
 */
public class DefaultSubscriber<T> extends DisposableObserver<T> {

  @Override
  public void onComplete() {
    // no-op by default.
  }

  @Override
  public void onError(Throwable throwable) {
    // no-op by default.
  }

  @Override
  public void onNext(T t) {
    // no-op by default.
  }
}
