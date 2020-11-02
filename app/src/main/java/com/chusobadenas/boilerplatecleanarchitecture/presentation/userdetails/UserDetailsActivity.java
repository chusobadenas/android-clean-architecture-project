package com.chusobadenas.boilerplatecleanarchitecture.presentation.userdetails;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.widget.Toolbar;
import com.chusobadenas.boilerplatecleanarchitecture.R;
import com.chusobadenas.boilerplatecleanarchitecture.databinding.ActivityLayoutBinding;
import com.chusobadenas.boilerplatecleanarchitecture.presentation.base.BaseActivity;
import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class UserDetailsActivity extends BaseActivity {

  protected static final String INTENT_EXTRA_PARAM_USER_ID = "org.android10.INTENT_PARAM_USER_ID";

  private ActivityLayoutBinding binding;

  public static Intent getCallingIntent(Context context, int userId) {
    Intent callingIntent = new Intent(context, UserDetailsActivity.class);
    callingIntent.putExtra(INTENT_EXTRA_PARAM_USER_ID, userId);
    return callingIntent;
  }

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    binding = ActivityLayoutBinding.inflate(getLayoutInflater());
    setContentView(binding.getRoot());
    initializeActivity(savedInstanceState);
    setupToolbar();
  }

  private void setupToolbar() {
    Toolbar toolbar = binding.getRoot().findViewById(R.id.toolbar);
    setSupportActionBar(toolbar);
    ActionBar actionBar = getSupportActionBar();

    if (actionBar != null) {
      actionBar.setDisplayHomeAsUpEnabled(true);
    }
  }

  private void initializeActivity(Bundle savedInstanceState) {
    if (savedInstanceState == null) {
      int userId = getIntent().getIntExtra(INTENT_EXTRA_PARAM_USER_ID, -1);
      addFragment(R.id.fragmentContainer, UserDetailsFragment.newInstance(userId));
    }
  }
}
