/**
 * Copyright (C) 2014 android10.org. All rights reserved.
 *
 * @author Fernando Cejas (the android10 coder)
 */
package com.chusobadenas.boilerplatecleanarchitecture.presentation.userdetails;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chusobadenas.boilerplatecleanarchitecture.R;
import com.chusobadenas.boilerplatecleanarchitecture.common.di.components.UserComponent;
import com.chusobadenas.boilerplatecleanarchitecture.common.util.UIUtils;
import com.chusobadenas.boilerplatecleanarchitecture.presentation.base.BaseMvpFragment;
import com.chusobadenas.boilerplatecleanarchitecture.presentation.model.UserModel;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Fragment that shows details of a certain user.
 */
public class UserDetailsFragment extends BaseMvpFragment implements UserDetailsMvpView {

  @Inject
  UserDetailsPresenter userDetailsPresenter;

  @BindView(R.id.iv_cover)
  ImageView imageViewCover;
  @BindView(R.id.tv_fullname)
  TextView textViewFullName;
  @BindView(R.id.tv_email)
  TextView textViewEmail;
  @BindView(R.id.tv_followers)
  TextView textViewFollowers;
  @BindView(R.id.tv_description)
  TextView textViewDescription;
  @BindView(R.id.rl_progress)
  RelativeLayout viewProgress;
  @BindView(R.id.rl_retry)
  RelativeLayout viewRetry;
  @BindView(R.id.user_detail_view)
  LinearLayout viewUserDetail;

  private Unbinder unbinder;

  /**
   * Creates a new instance of a UserDetailsFragment.
   */
  public static UserDetailsFragment newInstance() {
    return new UserDetailsFragment();
  }

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    getComponent(UserComponent.class).inject(this);
  }

  @Override
  public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                           Bundle savedInstanceState) {
    final View fragmentView = inflater.inflate(R.layout.fragment_user_details, container, false);
    unbinder = ButterKnife.bind(this, fragmentView);
    userDetailsPresenter.attachView(this);
    return fragmentView;
  }

  @Override
  public void onDestroyView() {
    super.onDestroyView();
    unbinder.unbind();
  }

  @Override
  public void onStart() {
    super.onStart();
    loadUserDetails();
  }

  @Override
  public void onDestroy() {
    super.onDestroy();
    userDetailsPresenter.detachView();
  }

  @Override
  public void renderUser(UserModel user) {
    if (user != null) {
      UIUtils.loadImageUrl(context(), imageViewCover, user.getCoverUrl());
      textViewFullName.setText(user.getFullName());
      textViewEmail.setText(user.getEmail());
      textViewFollowers.setText(String.valueOf(user.getFollowers()));
      textViewDescription.setText(user.getDescription());
    }
  }

  @Override
  public void showLoading() {
    viewUserDetail.setVisibility(View.GONE);
    viewProgress.setVisibility(View.VISIBLE);
  }

  @Override
  public void hideLoading() {
    viewUserDetail.setVisibility(View.VISIBLE);
    viewProgress.setVisibility(View.GONE);
  }

  @Override
  public void showRetry() {
    viewRetry.setVisibility(View.VISIBLE);
  }

  @Override
  public void hideRetry() {
    viewRetry.setVisibility(View.GONE);
  }

  /**
   * Loads all users.
   */
  private void loadUserDetails() {
    if (userDetailsPresenter != null) {
      int userId = ((UserDetailsActivity) getActivity()).getUserId();
      userDetailsPresenter.initialize(userId);
    }
  }

  @OnClick(R.id.bt_retry)
  void onButtonRetryClick() {
    loadUserDetails();
  }
}
