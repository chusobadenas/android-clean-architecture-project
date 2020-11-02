package com.chusobadenas.boilerplatecleanarchitecture.presentation.userlist;

import android.os.Bundle;
import com.chusobadenas.boilerplatecleanarchitecture.databinding.ActivityMainBinding;
import com.chusobadenas.boilerplatecleanarchitecture.presentation.base.BaseActivity;
import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class MainActivity extends BaseActivity {

  private ActivityMainBinding binding;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    binding = ActivityMainBinding.inflate(getLayoutInflater());
    setContentView(binding.getRoot());
    setupViews();
  }

  private void setupViews() {
    binding.btnLoadData.setOnClickListener(view -> navigateToUserList());
  }

  /**
   * Goes to the user list screen.
   */
  void navigateToUserList() {
    navigator.navigateToUserList(this);
  }
}
