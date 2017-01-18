/**
 * Copyright (C) 2015 Fernando Cejas Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.chusobadenas.boilerplatecleanarchitecture.presentation.userdetails;

import com.chusobadenas.boilerplatecleanarchitecture.common.di.PerActivity;
import com.chusobadenas.boilerplatecleanarchitecture.common.exception.DefaultErrorBundle;
import com.chusobadenas.boilerplatecleanarchitecture.domain.User;
import com.chusobadenas.boilerplatecleanarchitecture.domain.interactor.DefaultSubscriber;
import com.chusobadenas.boilerplatecleanarchitecture.domain.interactor.UseCase;
import com.chusobadenas.boilerplatecleanarchitecture.presentation.base.BasePresenter;
import com.chusobadenas.boilerplatecleanarchitecture.presentation.base.Presenter;
import com.chusobadenas.boilerplatecleanarchitecture.presentation.model.UserModel;
import com.chusobadenas.boilerplatecleanarchitecture.presentation.model.UserModelDataMapper;

import javax.inject.Inject;
import javax.inject.Named;

/**
 * {@link Presenter} that controls communication between views and models of the presentation
 * layer.
 */
@PerActivity
public class UserDetailsPresenter extends BasePresenter<UserDetailsMvpView> {

    private final UseCase mGetUserDetailsUseCase;
    private final UserModelDataMapper mUserModelDataMapper;

    @Inject
    public UserDetailsPresenter(@Named("userDetails") UseCase getUserDetailsUseCase, UserModelDataMapper
            userModelDataMapper) {
        this.mGetUserDetailsUseCase = getUserDetailsUseCase;
        this.mUserModelDataMapper = userModelDataMapper;
    }

    @Override
    public void detachView() {
        super.detachView();
        mGetUserDetailsUseCase.unsubscribe();
    }

    /**
     * Initializes the presenter by start retrieving user details.
     */
    void initialize() {
        checkViewAttached();
        loadUserDetails();
    }

    /**
     * Loads user details.
     */
    private void loadUserDetails() {
        getMvpView().hideRetry();
        getMvpView().showLoading();
        getUserDetails();
    }

    private void showUserDetailsInView(User user) {
        UserModel userModel = mUserModelDataMapper.transform(user);
        getMvpView().renderUser(userModel);
    }

    private void getUserDetails() {
        mGetUserDetailsUseCase.execute(new UserDetailsSubscriber());
    }

    private final class UserDetailsSubscriber extends DefaultSubscriber<User> {

        @Override
        public void onCompleted() {
            getMvpView().hideLoading();
        }

        @Override
        public void onError(Throwable throwable) {
            getMvpView().hideLoading();
            showErrorMessage(new DefaultErrorBundle(throwable));
            getMvpView().showRetry();
        }

        @Override
        public void onNext(User user) {
            showUserDetailsInView(user);
        }
    }
}
