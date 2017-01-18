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
package com.chusobadenas.boilerplatecleanarchitecture.presentation.userlist;

import com.chusobadenas.boilerplatecleanarchitecture.common.di.PerActivity;
import com.chusobadenas.boilerplatecleanarchitecture.common.exception.DefaultErrorBundle;
import com.chusobadenas.boilerplatecleanarchitecture.domain.User;
import com.chusobadenas.boilerplatecleanarchitecture.domain.interactor.DefaultSubscriber;
import com.chusobadenas.boilerplatecleanarchitecture.domain.interactor.UseCase;
import com.chusobadenas.boilerplatecleanarchitecture.presentation.base.BasePresenter;
import com.chusobadenas.boilerplatecleanarchitecture.presentation.base.Presenter;
import com.chusobadenas.boilerplatecleanarchitecture.presentation.model.UserModel;
import com.chusobadenas.boilerplatecleanarchitecture.presentation.model.UserModelDataMapper;

import java.util.Collection;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

/**
 * {@link Presenter} that controls communication between views and models of the presentation
 * layer.
 */
@PerActivity
public class UserListPresenter extends BasePresenter<UserListMvpView> {

    private final UseCase mGetUserListUseCase;
    private final UserModelDataMapper mUserModelDataMapper;

    @Inject
    public UserListPresenter(@Named("userList") UseCase getUserListUserCase, UserModelDataMapper userModelDataMapper) {
        this.mGetUserListUseCase = getUserListUserCase;
        this.mUserModelDataMapper = userModelDataMapper;
    }

    @Override
    public void detachView() {
        super.detachView();
        mGetUserListUseCase.unsubscribe();
    }

    /**
     * Initializes the presenter by start retrieving the user list.
     */
    void initialize() {
        checkViewAttached();
        loadUserList();
    }

    /**
     * Loads all users.
     */
    private void loadUserList() {
        UserListMvpView mvpView = getMvpView();
        mvpView.hideRetry();
        mvpView.showLoading();
        getUserList();
    }

    void onUserClicked(UserModel userModel) {
        getMvpView().viewUser(userModel);
    }

    private void showUsersCollectionInView(Collection<User> usersCollection) {
        final Collection<UserModel> userModelsCollection = mUserModelDataMapper.transform(usersCollection);
        getMvpView().renderUserList(userModelsCollection);
    }

    private void getUserList() {
        mGetUserListUseCase.execute(new UserListSubscriber());
    }

    private final class UserListSubscriber extends DefaultSubscriber<List<User>> {

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
        public void onNext(List<User> users) {
            showUsersCollectionInView(users);
        }
    }
}
