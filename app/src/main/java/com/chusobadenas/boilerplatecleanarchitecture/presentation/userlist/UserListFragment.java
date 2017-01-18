/**
 * Copyright (C) 2014 android10.org. All rights reserved.
 *
 * @author Fernando Cejas (the android10 coder)
 */
package com.chusobadenas.boilerplatecleanarchitecture.presentation.userlist;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.chusobadenas.boilerplatecleanarchitecture.R;
import com.chusobadenas.boilerplatecleanarchitecture.common.di.components.UserComponent;
import com.chusobadenas.boilerplatecleanarchitecture.presentation.base.BaseFragment;
import com.chusobadenas.boilerplatecleanarchitecture.presentation.model.UserModel;

import java.util.Collection;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Fragment that shows a list of Users.
 */
public class UserListFragment extends BaseFragment implements UserListMvpView {

    /**
     * Interface for listening user list events.
     */
    public interface UserListListener {

        void onUserClicked(final UserModel userModel);
    }

    @Inject
    UserListPresenter mUserListPresenter;
    @Inject
    UsersAdapter mUsersAdapter;

    @BindView(R.id.rv_users)
    RecyclerView mViewUsers;
    @BindView(R.id.rl_progress)
    RelativeLayout mViewProgress;
    @BindView(R.id.rl_retry)
    RelativeLayout mViewRetry;
    @BindView(R.id.swipe_container)
    SwipeRefreshLayout mSwipeRefresh;

    private UserListListener mUserListListener;
    private Unbinder mUnbinder;

    /**
     * Creates a new instance of a UserListFragment.
     */
    public static UserListFragment newInstance() {
        return new UserListFragment();
    }

    @Override
    public void onAttachToContext(Context context) {
        super.onAttachToContext(context);
        if (context instanceof UserListListener) {
            mUserListListener = (UserListListener) context;
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        getComponent(UserComponent.class).inject(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View fragmentView = inflater.inflate(R.layout.fragment_user_list, container, false);
        mUnbinder = ButterKnife.bind(this, fragmentView);
        mUserListPresenter.attachView(this);
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
        mViewUsers.setAdapter(null);
        mUnbinder.unbind();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mUserListPresenter.detachView();
    }

    @Override
    public void showLoading() {
        mSwipeRefresh.setVisibility(View.GONE);
        mSwipeRefresh.setRefreshing(true);
        mViewProgress.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        mSwipeRefresh.setVisibility(View.VISIBLE);
        mSwipeRefresh.setRefreshing(false);
        mViewProgress.setVisibility(View.GONE);
    }

    @Override
    public void showRetry() {
        mViewRetry.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideRetry() {
        mViewRetry.setVisibility(View.GONE);
    }

    @Override
    public void renderUserList(Collection<UserModel> userModelCollection) {
        if (userModelCollection != null) {
            mUsersAdapter.setUsersCollection(userModelCollection);
        }
    }

    @Override
    public void viewUser(UserModel userModel) {
        if (mUserListListener != null) {
            mUserListListener.onUserClicked(userModel);
        }
    }

    @Override
    public void showError(String message) {
        showToastMessage(message);
    }

    @Override
    public Context context() {
        return getActivity().getApplicationContext();
    }

    private void setupRecyclerView() {
        mUsersAdapter.setOnItemClickListener(onItemClickListener);
        mViewUsers.setLayoutManager(new UsersLayoutManager(context()));
        mViewUsers.setAdapter(mUsersAdapter);

        mSwipeRefresh.setColorSchemeResources(R.color.primary);
        mSwipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
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
        mUserListPresenter.initialize();
    }

    @OnClick(R.id.bt_retry)
    void onButtonRetryClick() {
        loadUserList();
    }

    private final UsersAdapter.OnItemClickListener onItemClickListener = new UsersAdapter.OnItemClickListener() {
        @Override
        public void onUserItemClicked(UserModel userModel) {
            if (mUserListPresenter != null && userModel != null) {
                mUserListPresenter.onUserClicked(userModel);
            }
        }
    };
}
