package com.chusobadenas.boilerplatecleanarchitecture.presentation.userdetails;

import androidx.annotation.NonNull;
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
public class UserDetailsPresenter extends BasePresenter<UserDetailsMvpView> {

  private final UseCase getUserDetailsUseCase;
  private final UserModelDataMapper userModelDataMapper;

  @Inject
  public UserDetailsPresenter(
      @Named("userDetails") UseCase getUserDetailsUseCase,
      UserModelDataMapper userModelDataMapper
  ) {
    this.getUserDetailsUseCase = getUserDetailsUseCase;
    this.userModelDataMapper = userModelDataMapper;
  }

  @Override
  public void detachView() {
    super.detachView();
    getUserDetailsUseCase.unsubscribe();
  }

  /**
   * Initializes the presenter by start retrieving user details.
   */
  void initialize(int userId) {
    checkViewAttached();
    loadUserDetails(userId);
  }

  /**
   * Loads user details.
   */
  private void loadUserDetails(int userId) {
    getMvpView().hideRetry();
    getMvpView().showLoading();
    getUserDetails(userId);
  }

  private void showUserDetailsInView(User user) {
    UserModel userModel = userModelDataMapper.transform(user);
    getMvpView().renderUser(userModel);
  }

  private void getUserDetails(int userId) {
    getUserDetailsUseCase.execute(new UserDetailsSubscriber(), userId);
  }

  private final class UserDetailsSubscriber extends DefaultSubscriber<User> {

    @Override
    public void onError(@NonNull Throwable throwable) {
      getMvpView().hideLoading();
      showErrorMessage(throwable, "Error loading user details", null);
      getMvpView().showRetry();
    }

    @Override
    public void onNext(@NonNull User user) {
      getMvpView().hideLoading();
      showUserDetailsInView(user);
    }
  }
}
