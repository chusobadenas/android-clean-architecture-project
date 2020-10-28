package com.chusobadenas.boilerplatecleanarchitecture.presentation.userlist;

import com.chusobadenas.boilerplatecleanarchitecture.presentation.base.MvpView;
import com.chusobadenas.boilerplatecleanarchitecture.presentation.model.UserModel;

import java.util.Collection;

/**
 * Interface representing a View in a model view presenter (MVP) pattern.
 * In this case is used as a view representing a list of {@link UserModel}.
 */
interface UserListMvpView extends MvpView {

  /**
   * Render a user list in the UI.
   *
   * @param userModelCollection The collection of {@link UserModel} that will be shown.
   */
  void renderUserList(Collection<UserModel> userModelCollection);

  /**
   * View a {@link UserModel} profile/details.
   *
   * @param userModel The user that will be shown.
   */
  void viewUser(UserModel userModel);
}
