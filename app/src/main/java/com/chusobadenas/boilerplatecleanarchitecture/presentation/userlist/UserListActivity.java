package com.chusobadenas.boilerplatecleanarchitecture.presentation.userlist;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.widget.Toolbar;
import com.chusobadenas.boilerplatecleanarchitecture.R;
import com.chusobadenas.boilerplatecleanarchitecture.databinding.ActivityLayoutBinding;
import com.chusobadenas.boilerplatecleanarchitecture.presentation.base.BaseActivity;
import com.chusobadenas.boilerplatecleanarchitecture.presentation.model.UserModel;
import com.chusobadenas.boilerplatecleanarchitecture.presentation.userlist.UserListFragment.UserListListener;
import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class UserListActivity extends BaseActivity implements UserListListener {

  private ActivityLayoutBinding binding;

  public static Intent getCallingIntent(Context context) {
    return new Intent(context, UserListActivity.class);
  }

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    binding = ActivityLayoutBinding.inflate(getLayoutInflater());
    setContentView(binding.getRoot());
    setupToolbar();
    if (savedInstanceState == null) {
      addFragment(R.id.fragmentContainer, UserListFragment.newInstance());
    }
  }

  private void setupToolbar() {
    Toolbar toolbar = binding.getRoot().findViewById(R.id.toolbar);
    setSupportActionBar(toolbar);
  }

  @Override
  public void onUserClicked(UserModel userModel) {
    navigator.navigateToUserDetails(this, userModel.getUserId());
  }
}
