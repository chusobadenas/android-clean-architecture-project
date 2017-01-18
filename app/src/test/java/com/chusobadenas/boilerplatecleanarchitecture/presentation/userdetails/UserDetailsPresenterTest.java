package com.chusobadenas.boilerplatecleanarchitecture.presentation.userdetails;

import com.chusobadenas.boilerplatecleanarchitecture.domain.interactor.DefaultSubscriber;
import com.chusobadenas.boilerplatecleanarchitecture.domain.interactor.GetUserDetails;
import com.chusobadenas.boilerplatecleanarchitecture.presentation.model.UserModelDataMapper;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;

public class UserDetailsPresenterTest {

    private UserDetailsPresenter mUserDetailsPresenter;

    @Mock
    private GetUserDetails mMockGetUserDetails;
    @Mock
    private UserDetailsMvpView mMockUserDetailsMvpView;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        mUserDetailsPresenter = new UserDetailsPresenter(mMockGetUserDetails, new UserModelDataMapper());
        mUserDetailsPresenter.attachView(mMockUserDetailsMvpView);
    }

    @Test
    public void testAttachViewSuccess() {
        assertNotNull(mUserDetailsPresenter.getMvpView());
    }

    @Test
    public void testDetachViewSuccess() {
        mUserDetailsPresenter.detachView();
        assertNull(mUserDetailsPresenter.getMvpView());
        verify(mMockGetUserDetails).unsubscribe();
    }

    @Test
    public void testInitializeSuccess() {
        mUserDetailsPresenter.initialize();
        verify(mMockUserDetailsMvpView).hideRetry();
        verify(mMockUserDetailsMvpView).showLoading();
        verify(mMockGetUserDetails).execute(any(DefaultSubscriber.class));
    }
}
