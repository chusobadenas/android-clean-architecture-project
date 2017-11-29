/**
 * Copyright (C) 2014 android10.org. All rights reserved.
 *
 * @author Fernando Cejas (the android10 coder)
 */
package com.chusobadenas.boilerplatecleanarchitecture.presentation.userlist;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.chusobadenas.boilerplatecleanarchitecture.R;
import com.chusobadenas.boilerplatecleanarchitecture.common.di.HasComponent;
import com.chusobadenas.boilerplatecleanarchitecture.common.di.components.DaggerUserComponent;
import com.chusobadenas.boilerplatecleanarchitecture.common.di.components.UserComponent;
import com.chusobadenas.boilerplatecleanarchitecture.presentation.base.BaseActivity;
import com.chusobadenas.boilerplatecleanarchitecture.presentation.model.UserModel;
import com.chusobadenas.boilerplatecleanarchitecture.presentation.userlist.UserListFragment.UserListListener;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Activity that shows a list of Users.
 */
public class UserListActivity extends BaseActivity implements HasComponent<UserComponent>,
    UserListListener {

  @BindView(R.id.toolbar)
  Toolbar toolbar;

  private UserComponent userComponent;

  public static Intent getCallingIntent(Context context) {
    return new Intent(context, UserListActivity.class);
  }

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_layout);
    ButterKnife.bind(this);
    initializeInjector();
    setSupportActionBar(toolbar);
    if (savedInstanceState == null) {
      addFragment(R.id.fragmentContainer, UserListFragment.newInstance());
    }
  }

  private void initializeInjector() {
    userComponent = DaggerUserComponent.builder()
        .applicationComponent(getApplicationComponent())
        .activityModule(getActivityModule())
        .build();
  }

  @Override
  public UserComponent getComponent() {
    return userComponent;
  }

  @Override
  public void onUserClicked(UserModel userModel) {
    navigator.navigateToUserDetails(this, userModel.getUserId());
  }
}
