package com.chusobadenas.boilerplatecleanarchitecture.presentation.base;

import com.chusobadenas.boilerplatecleanarchitecture.common.exception.ErrorBundle;
import com.chusobadenas.boilerplatecleanarchitecture.common.exception.ErrorMessageFactory;

/**
 * Base class that implements the Presenter interface and provides a base implementation for
 * attachView() and detachView(). It also handles keeping a reference to the mvpView that
 * can be accessed from the children classes by calling getMvpView().
 */
public class BasePresenter<T extends MvpView> implements Presenter<T> {

    private T mMvpView;

    @Override
    public void attachView(T mvpView) {
        mMvpView = mvpView;
    }

    @Override
    public void detachView() {
        mMvpView = null;
    }

    private boolean isViewAttached() {
        return mMvpView != null;
    }

    public T getMvpView() {
        return mMvpView;
    }

    public void checkViewAttached() {
        if (!isViewAttached()) {
            throw new MvpViewNotAttachedException();
        }
    }

    public void showErrorMessage(ErrorBundle errorBundle) {
        String errorMessage = ErrorMessageFactory.create(mMvpView.context(), errorBundle.getException());
        mMvpView.showError(errorMessage);
    }

    private static class MvpViewNotAttachedException extends RuntimeException {

        MvpViewNotAttachedException() {
            super("Please call Presenter.attachView(MvpView) before requesting data to the Presenter");
        }
    }
}
