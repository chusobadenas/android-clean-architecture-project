package com.chusobadenas.boilerplatecleanarchitecture.presentation.userlist;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import com.chusobadenas.boilerplatecleanarchitecture.R;
import com.chusobadenas.boilerplatecleanarchitecture.presentation.base.BaseMvpFragment;
import com.chusobadenas.boilerplatecleanarchitecture.presentation.model.UserModel;
import dagger.hilt.android.AndroidEntryPoint;

import javax.inject.Inject;
import java.util.*;

/**
 * Fragment that shows a list of Users.
 */
@AndroidEntryPoint
public class UserListFragment extends BaseMvpFragment implements UserListMvpView {

  /**
   * Interface for listening user list events.
   */
  public interface UserListListener {

    void onUserClicked(final UserModel userModel);
  }

  @Inject
  UserListPresenter userListPresenter;
  @Inject
  UsersAdapter usersAdapter;

  @BindView(R.id.rv_users)
  RecyclerView viewUsers;
  @BindView(R.id.rl_progress)
  RelativeLayout viewProgress;
  @BindView(R.id.rl_retry)
  RelativeLayout viewRetry;
  @BindView(R.id.swipe_container)
  SwipeRefreshLayout swipeRefresh;

  private UserListListener userListListener;
  private Unbinder unbinder;

  /**
   * Creates a new instance of a UserListFragment.
   */
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
  public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    View fragmentView = inflater.inflate(R.layout.fragment_user_list, container, false);
    unbinder = ButterKnife.bind(this, fragmentView);
    userListPresenter.attachView(this);
    setupRecyclerView();
    return fragmentView;
  }

  @Override
  public void onStart() {
    super.onStart();
    loadUserList();
  }

  @Override
  public void onDestroyView() {
    super.onDestroyView();
    viewUsers.setAdapter(null);
    unbinder.unbind();
  }

  @Override
  public void onDestroy() {
    super.onDestroy();
    userListPresenter.detachView();
  }

  @Override
  public void showLoading() {
    swipeRefresh.setVisibility(View.GONE);
    swipeRefresh.setRefreshing(true);
    viewProgress.setVisibility(View.VISIBLE);
  }

  @Override
  public void hideLoading() {
    swipeRefresh.setVisibility(View.VISIBLE);
    swipeRefresh.setRefreshing(false);
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

  private void setupRecyclerView() {
    usersAdapter.setOnItemClickListener(onItemClickListener);
    viewUsers.setLayoutManager(new UsersLayoutManager(context()));
    viewUsers.setAdapter(usersAdapter);

    swipeRefresh.setColorSchemeResources(R.color.primary);
    swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
      @Override
      public void onRefresh() {
        loadUserList();
      }
    });
  }

  /**
   * Loads all users.
   */
  private void loadUserList() {
    userListPresenter.initialize();
  }

  @OnClick(R.id.bt_retry)
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
