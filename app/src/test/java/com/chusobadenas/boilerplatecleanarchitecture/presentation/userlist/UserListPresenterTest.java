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

  private UserListPresenter userListPresenter;

  @Mock
  private GetUserList getUserList;
  @Mock
  private UserListMvpView userListMvpView;

  @Before
  public void setUp() {
    MockitoAnnotations.initMocks(this);
    userListPresenter = new UserListPresenter(getUserList, new UserModelDataMapper());
    userListPresenter.attachView(userListMvpView);
  }

  @Test
  public void testAttachViewSuccess() {
    assertNotNull(userListPresenter.getMvpView());
  }

  @Test
  public void testDetachViewSuccess() {
    userListPresenter.detachView();
    assertNull(userListPresenter.getMvpView());
    verify(getUserList).unsubscribe();
  }

  @Test
  public void testInitializeSuccess() {
    userListPresenter.initialize();
    verify(userListMvpView).hideRetry();
    verify(userListMvpView).showLoading();
    verify(getUserList).execute(any(DefaultSubscriber.class));
  }

  @Test
  public void testOnUserClickedSuccess() {
    UserModel userModel = new UserModel(1);
    userListPresenter.onUserClicked(userModel);
    verify(userListMvpView).viewUser(userModel);
  }
}
