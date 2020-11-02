package com.chusobadenas.boilerplatecleanarchitecture.domain.interactor;

import com.chusobadenas.boilerplatecleanarchitecture.common.executor.PostExecutionThread;
import com.chusobadenas.boilerplatecleanarchitecture.common.executor.ThreadExecutor;

import org.reactivestreams.Subscriber;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

/**
 * Abstract class for a Use Case (Interactor in terms of Clean Architecture).
 * This interface represents a execution unit for different use cases (this means any use case
 * in the application should implement this contract).
 * <p>
 * By convention each UseCase implementation will return the result using a {@link Subscriber}
 * that will execute its job in a background thread and will post the result in the UI thread.
 */
public abstract class UseCase {

  private final ThreadExecutor threadExecutor;
  private final PostExecutionThread postExecutionThread;

  private DisposableObserver disposableObserver;

  public UseCase(ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread) {
    this.threadExecutor = threadExecutor;
    this.postExecutionThread = postExecutionThread;
  }

  /**
   * Builds an {@link Observable} which will be used when executing the current {@link UseCase}.
   *
   * @param params optional parameters
   */
  public abstract Observable buildUseCaseObservable(Object... params);

  /**
   * Executes the current use case.
   *
   * @param observer The guy who will be listen to the observable build
   *                 with {@link #buildUseCaseObservable(Object...)}.
   * @param params   optional parameters
   */
  @SuppressWarnings("unchecked")
  public void execute(Observer observer, Object... params) {
    this.disposableObserver = (DisposableObserver) this.buildUseCaseObservable(params)
        .subscribeOn(Schedulers.from(threadExecutor))
        .observeOn(postExecutionThread.getScheduler())
        .subscribeWith(observer);
  }

  /**
   * Unsubscribes from current {@link DisposableObserver}.
   */
  public void unsubscribe() {
    if (disposableObserver != null && !disposableObserver.isDisposed()) {
      disposableObserver.dispose();
    }
  }
}
