package com.chusobadenas.boilerplatecleanarchitecture.presentation.userlist;

import com.chusobadenas.boilerplatecleanarchitecture.domain.interactor.DefaultSubscriber;
import com.chusobadenas.boilerplatecleanarchitecture.domain.interactor.GetUserList;
import com.chusobadenas.boilerplatecleanarchitecture.presentation.model.UserModel;
import com.chusobadenas.boilerplatecleanarchitecture.presentation.model.UserModelDataMapper;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;

public class UserListPresenterTest {

    private UserListPresenter mUserListPresenter;

    @Mock
    private GetUserList mMockGetUserList;
    @Mock
    private UserListMvpView mMockUserListMvpView;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        mUserListPresenter = new UserListPresenter(mMockGetUserList, new UserModelDataMapper());
        mUserListPresenter.attachView(mMockUserListMvpView);
    }

    @Test
    public void testAttachViewSuccess() {
        assertNotNull(mUserListPresenter.getMvpView());
    }

    @Test
    public void testDetachViewSuccess() {
        mUserListPresenter.detachView();
        assertNull(mUserListPresenter.getMvpView());
        verify(mMockGetUserList).unsubscribe();
    }

    @Test
    public void testInitializeSuccess() {
        mUserListPresenter.initialize();
        verify(mMockUserListMvpView).hideRetry();
        verify(mMockUserListMvpView).showLoading();
        verify(mMockGetUserList).execute(any(DefaultSubscriber.class));
    }

    @Test
    public void testOnUserClickedSuccess() {
        UserModel userModel = new UserModel(1);
        mUserListPresenter.onUserClicked(userModel);
        verify(mMockUserListMvpView).viewUser(userModel);
    }
}
