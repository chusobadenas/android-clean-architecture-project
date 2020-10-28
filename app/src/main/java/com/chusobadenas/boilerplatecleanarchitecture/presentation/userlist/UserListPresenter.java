package com.chusobadenas.boilerplatecleanarchitecture.presentation.userlist;

import com.chusobadenas.boilerplatecleanarchitecture.common.di.PerActivity;
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

  private final UseCase getUserListUseCase;
  private final UserModelDataMapper userModelDataMapper;

  @Inject
  public UserListPresenter(@Named("userList") UseCase getUserListUserCase, UserModelDataMapper userModelDataMapper) {
    this.getUserListUseCase = getUserListUserCase;
    this.userModelDataMapper = userModelDataMapper;
  }

  @Override
  public void detachView() {
    super.detachView();
    getUserListUseCase.unsubscribe();
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
    final Collection<UserModel> userModelsCollection = userModelDataMapper.transform(usersCollection);
    getMvpView().renderUserList(userModelsCollection);
  }

  private void getUserList() {
    getUserListUseCase.execute(new UserListSubscriber());
  }

  private final class UserListSubscriber extends DefaultSubscriber<List<User>> {

    @Override
    public void onError(Throwable throwable) {
      getMvpView().hideLoading();
      showErrorMessage(throwable, "Error loading user list", null);
      getMvpView().showRetry();
    }

    @Override
    public void onNext(List<User> users) {
      getMvpView().hideLoading();
      showUsersCollectionInView(users);
    }
  }
}
