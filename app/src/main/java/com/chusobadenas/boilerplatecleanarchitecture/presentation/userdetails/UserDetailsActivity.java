/**
 * Copyright (C) 2014 android10.org. All rights reserved.
 *
 * @author Fernando Cejas (the android10 coder)
 */
package com.chusobadenas.boilerplatecleanarchitecture.presentation.userdetails;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.chusobadenas.boilerplatecleanarchitecture.R;
import com.chusobadenas.boilerplatecleanarchitecture.presentation.base.BaseActivity;

/**
 * Activity that shows details of a certain user.
 */
public class UserDetailsActivity extends BaseActivity {

  private static final String INTENT_EXTRA_PARAM_USER_ID = "org.android10.INTENT_PARAM_USER_ID";
  private static final String INSTANCE_STATE_PARAM_USER_ID = "org.android10.STATE_PARAM_USER_ID";

  @BindView(R.id.toolbar)
  Toolbar toolbar;

  private int userId;

  public static Intent getCallingIntent(Context context, int userId) {
    Intent callingIntent = new Intent(context, UserDetailsActivity.class);
    callingIntent.putExtra(INTENT_EXTRA_PARAM_USER_ID, userId);
    return callingIntent;
  }

  public int getUserId() {
    return userId;
  }

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_layout);
    this.initializeActivity(savedInstanceState);
    ButterKnife.bind(this);
    setupToolbar();
  }

  @Override
  protected void onSaveInstanceState(Bundle outState) {
    if (outState != null) {
      outState.putInt(INSTANCE_STATE_PARAM_USER_ID, this.userId);
    }

    super.onSaveInstanceState(outState);
  }

  private void setupToolbar() {
    setSupportActionBar(toolbar);
    ActionBar actionBar = getSupportActionBar();

    if (actionBar != null) {
      actionBar.setDisplayHomeAsUpEnabled(true);
    }
  }

  /**
   * Initializes this activity.
   */
  private void initializeActivity(Bundle savedInstanceState) {
    if (savedInstanceState == null) {
      userId = getIntent().getIntExtra(INTENT_EXTRA_PARAM_USER_ID, -1);
      addFragment(R.id.fragmentContainer, UserDetailsFragment.newInstance());
    } else {
      userId = savedInstanceState.getInt(INSTANCE_STATE_PARAM_USER_ID);
    }
  }
}
