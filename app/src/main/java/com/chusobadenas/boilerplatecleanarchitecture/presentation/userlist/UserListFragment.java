package com.chusobadenas.boilerplatecleanarchitecture.presentation.userlist;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import androidx.annotation.NonNull;
import com.chusobadenas.boilerplatecleanarchitecture.R;
import com.chusobadenas.boilerplatecleanarchitecture.databinding.FragmentUserListBinding;
import com.chusobadenas.boilerplatecleanarchitecture.presentation.base.BaseMvpFragment;
import com.chusobadenas.boilerplatecleanarchitecture.presentation.model.UserModel;
import dagger.hilt.android.AndroidEntryPoint;

import javax.inject.Inject;
import java.util.*;

@AndroidEntryPoint
public class UserListFragment extends BaseMvpFragment implements UserListMvpView {

  public interface UserListListener {

    void onUserClicked(final UserModel userModel);
  }

  @Inject
  UserListPresenter userListPresenter;
  @Inject
  UsersAdapter usersAdapter;

  private FragmentUserListBinding binding;
  private RelativeLayout viewProgress;
  private RelativeLayout viewRetry;
  private UserListListener userListListener;

  public static UserListFragment newInstance() {
    return new UserListFragment();
  }

  @Override
  public void onAttach(@NonNull Context context) {
    super.onAttach(context);
    if (context instanceof UserListListener) {
      userListListener = (UserListListener) context;
    }
  }

  @Override
  public View onCreateView(
      @NonNull LayoutInflater inflater,
      ViewGroup container,
      Bundle savedInstanceState
  ) {
    binding = FragmentUserListBinding.inflate(inflater, container, false);

    userListPresenter.attachView(this);
    setupViews();
    setupRecyclerView();

    return binding.getRoot();
  }

  @Override
  public void onStart() {
    super.onStart();
    loadUserList();
  }

  @Override
  public void onDestroyView() {
    super.onDestroyView();
    binding.rvUsers.setAdapter(null);
  }

  @Override
  public void onDestroy() {
    super.onDestroy();
    userListPresenter.detachView();
  }

  @Override
  public void showLoading() {
    binding.swipeContainer.setVisibility(View.GONE);
    binding.swipeContainer.setRefreshing(true);
    viewProgress.setVisibility(View.VISIBLE);
  }

  @Override
  public void hideLoading() {
    binding.swipeContainer.setVisibility(View.VISIBLE);
    binding.swipeContainer.setRefreshing(false);
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

  @Override
  public void renderUserList(Collection<UserModel> userModelCollection) {
    if (userModelCollection != null) {
      usersAdapter.setUsersCollection(userModelCollection);
    }
  }

  @Override
  public void viewUser(UserModel userModel) {
    if (userListListener != null) {
      userListListener.onUserClicked(userModel);
    }
  }

  private void setupViews() {
    // Progress and retry
    viewProgress = binding.getRoot().findViewById(R.id.rl_progress);
    viewRetry = binding.getRoot().findViewById(R.id.rl_retry);
    // Retry button
    binding.getRoot().findViewById(R.id.bt_retry).setOnClickListener(view -> onButtonRetryClick());
  }

  private void setupRecyclerView() {
    usersAdapter.setOnItemClickListener(onItemClickListener);
    binding.rvUsers.setLayoutManager(new UsersLayoutManager(context()));
    binding.rvUsers.setAdapter(usersAdapter);

    binding.swipeContainer.setColorSchemeResources(R.color.primary);
    binding.swipeContainer.setOnRefreshListener(this::loadUserList);
  }

  private void loadUserList() {
    userListPresenter.initialize();
  }

  void onButtonRetryClick() {
    loadUserList();
  }

  private final UsersAdapter.OnItemClickListener onItemClickListener = new UsersAdapter.OnItemClickListener() {
    @Override
    public void onUserItemClicked(UserModel userModel) {
      if (userListPresenter != null && userModel != null) {
        userListPresenter.onUserClicked(userModel);
      }
    }
  };
}
