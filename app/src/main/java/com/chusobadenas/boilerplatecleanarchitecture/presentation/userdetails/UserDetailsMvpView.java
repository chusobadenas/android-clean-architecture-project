package com.chusobadenas.boilerplatecleanarchitecture.presentation.userdetails;

import com.chusobadenas.boilerplatecleanarchitecture.presentation.base.MvpView;
import com.chusobadenas.boilerplatecleanarchitecture.presentation.model.UserModel;

/**
 * Interface representing a View in a model view presenter (MVP) pattern.
 * In this case is used as a view representing a user profile.
 */
interface UserDetailsMvpView extends MvpView {

  /**
   * Render a user in the UI.
   *
   * @param user The {@link UserModel} that will be shown.
   */
  void renderUser(UserModel user);
}
