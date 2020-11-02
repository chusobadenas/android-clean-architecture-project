package com.chusobadenas.boilerplatecleanarchitecture.presentation.userdetails;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.chusobadenas.boilerplatecleanarchitecture.R;
import com.chusobadenas.boilerplatecleanarchitecture.common.util.UIUtils;
import com.chusobadenas.boilerplatecleanarchitecture.databinding.FragmentUserDetailsBinding;
import com.chusobadenas.boilerplatecleanarchitecture.presentation.base.BaseMvpFragment;
import com.chusobadenas.boilerplatecleanarchitecture.presentation.model.UserModel;
import dagger.hilt.android.AndroidEntryPoint;

import javax.inject.Inject;

@AndroidEntryPoint
public class UserDetailsFragment extends BaseMvpFragment implements UserDetailsMvpView {

  @Inject
  UserDetailsPresenter userDetailsPresenter;

  private FragmentUserDetailsBinding binding;
  private ConstraintLayout viewUserDetail;
  private ConstraintLayout viewProgress;
  private ConstraintLayout viewRetry;

  public static UserDetailsFragment newInstance(int userId) {
    UserDetailsFragment fragment = new UserDetailsFragment();
    Bundle extras = new Bundle();
    extras.putInt(UserDetailsActivity.INTENT_EXTRA_PARAM_USER_ID, userId);
    fragment.setArguments(extras);
    return fragment;
  }

  @Override
  public View onCreateView(
      @NonNull LayoutInflater inflater,
      ViewGroup container,
      Bundle savedInstanceState
  ) {
    binding = FragmentUserDetailsBinding.inflate(inflater, container, false);

    userDetailsPresenter.attachView(this);
    setupViews();

    return binding.getRoot();
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
      View view = binding.getRoot();
      ImageView imageViewCover = view.findViewById(R.id.iv_cover);
      UIUtils.loadImageUrl(context(), imageViewCover, user.getCoverUrl());

      ((TextView) view.findViewById(R.id.tv_fullname)).setText(user.getFullName());
      ((TextView) view.findViewById(R.id.tv_email)).setText(user.getEmail());
      ((TextView) view.findViewById(R.id.tv_followers)).setText(String.valueOf(user.getFollowers()));
      ((TextView) view.findViewById(R.id.tv_description)).setText(user.getDescription());
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

  private void setupViews() {
    // Detail view
    viewUserDetail = binding.getRoot().findViewById(R.id.user_detail_view);
    // Progress and retry
    viewProgress = binding.getRoot().findViewById(R.id.rl_progress);
    viewRetry = binding.getRoot().findViewById(R.id.rl_retry);
    // Retry button
    binding.getRoot().findViewById(R.id.bt_retry).setOnClickListener(view -> onButtonRetryClick());
  }

  private void loadUserDetails() {
    if (userDetailsPresenter != null) {
      Bundle arguments = getArguments();
      if (arguments != null) {
        int userId = arguments.getInt(UserDetailsActivity.INTENT_EXTRA_PARAM_USER_ID, -1);
        userDetailsPresenter.initialize(userId);
      }
    }
  }

  void onButtonRetryClick() {
    loadUserDetails();
  }
}
