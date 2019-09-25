/**
 * Copyright (C) 2014 android10.org. All rights reserved.
 *
 * @author Fernando Cejas (the android10 coder)
 */
package com.chusobadenas.boilerplatecleanarchitecture.presentation.userlist;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.widget.Toolbar;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.chusobadenas.boilerplatecleanarchitecture.R;
import com.chusobadenas.boilerplatecleanarchitecture.presentation.base.BaseActivity;
import com.chusobadenas.boilerplatecleanarchitecture.presentation.model.UserModel;
import com.chusobadenas.boilerplatecleanarchitecture.presentation.userlist.UserListFragment.UserListListener;

/**
 * Activity that shows a list of Users.
 */
public class UserListActivity extends BaseActivity implements UserListListener {

  @BindView(R.id.toolbar)
  Toolbar toolbar;

  public static Intent getCallingIntent(Context context) {
    return new Intent(context, UserListActivity.class);
  }

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_layout);
    ButterKnife.bind(this);
    setSupportActionBar(toolbar);
    if (savedInstanceState == null) {
      addFragment(R.id.fragmentContainer, UserListFragment.newInstance());
    }
  }

  @Override
  public void onUserClicked(UserModel userModel) {
    navigator.navigateToUserDetails(this, userModel.getUserId());
  }
}
