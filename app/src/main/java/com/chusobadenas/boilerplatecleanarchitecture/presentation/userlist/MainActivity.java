package com.chusobadenas.boilerplatecleanarchitecture.presentation.userlist;

import android.os.Bundle;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.chusobadenas.boilerplatecleanarchitecture.R;
import com.chusobadenas.boilerplatecleanarchitecture.presentation.base.BaseActivity;
import dagger.hilt.android.AndroidEntryPoint;

/**
 * Main application screen. This is the app entry point.
 */
@AndroidEntryPoint
public class MainActivity extends BaseActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    ButterKnife.bind(this);
  }

  /**
   * Goes to the user list screen.
   */
  @OnClick(R.id.btn_LoadData)
  void navigateToUserList() {
    navigator.navigateToUserList(this);
  }
}
