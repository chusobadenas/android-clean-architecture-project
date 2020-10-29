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
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.verify;

public class UserDetailsPresenterTest {

  private UserDetailsPresenter userDetailsPresenter;

  @Mock
  private GetUserDetails getUserDetails;
  @Mock
  private UserDetailsMvpView userDetailsMvpView;

  @Before
  public void setUp() {
    MockitoAnnotations.openMocks(this);
    userDetailsPresenter = new UserDetailsPresenter(getUserDetails, new UserModelDataMapper());
    userDetailsPresenter.attachView(userDetailsMvpView);
  }

  @Test
  public void testAttachViewSuccess() {
    assertNotNull(userDetailsPresenter.getMvpView());
  }

  @Test
  public void testDetachViewSuccess() {
    userDetailsPresenter.detachView();
    assertNull(userDetailsPresenter.getMvpView());
    verify(getUserDetails).unsubscribe();
  }

  @Test
  public void testInitializeSuccess() {
    userDetailsPresenter.initialize(1);
    verify(userDetailsMvpView).hideRetry();
    verify(userDetailsMvpView).showLoading();
    verify(getUserDetails).execute(any(DefaultSubscriber.class), eq(1));
  }
}
