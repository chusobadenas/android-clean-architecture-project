/**
 * Copyright (C) 2014 android10.org. All rights reserved.
 *
 * @author Fernando Cejas (the android10 coder)
 */
package com.chusobadenas.boilerplatecleanarchitecture.presentation.userdetails;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chusobadenas.boilerplatecleanarchitecture.R;
import com.chusobadenas.boilerplatecleanarchitecture.common.di.components.UserComponent;
import com.chusobadenas.boilerplatecleanarchitecture.presentation.base.BaseFragment;
import com.chusobadenas.boilerplatecleanarchitecture.presentation.model.UserModel;
import com.squareup.picasso.Picasso;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Fragment that shows details of a certain user.
 */
public class UserDetailsFragment extends BaseFragment implements UserDetailsMvpView {

    @Inject
    UserDetailsPresenter mUserDetailsPresenter;

    @BindView(R.id.iv_cover)
    ImageView mImageViewCover;
    @BindView(R.id.tv_fullname)
    TextView mTextViewFullName;
    @BindView(R.id.tv_email)
    TextView mTextViewEmail;
    @BindView(R.id.tv_followers)
    TextView mTextViewFollowers;
    @BindView(R.id.tv_description)
    TextView mTextViewDescription;
    @BindView(R.id.rl_progress)
    RelativeLayout mViewProgress;
    @BindView(R.id.rl_retry)
    RelativeLayout mViewRetry;
    @BindView(R.id.user_detail_view)
    LinearLayout mViewUserDetail;

    private Unbinder mUnbinder;

    /**
     * Creates a new instance of a UserDetailsFragment.
     */
    public static UserDetailsFragment newInstance() {
        return new UserDetailsFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        getComponent(UserComponent.class).inject(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View fragmentView = inflater.inflate(R.layout.fragment_user_details, container, false);
        mUnbinder = ButterKnife.bind(this, fragmentView);
        mUserDetailsPresenter.attachView(this);
        return fragmentView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mUnbinder.unbind();
    }

    @Override
    public void onStart() {
        super.onStart();
        loadUserDetails();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mUserDetailsPresenter.detachView();
    }

    @Override
    public void renderUser(UserModel user) {
        if (user != null) {
            Picasso.with(getActivity()).load(user.getCoverUrl()).into(mImageViewCover);
            mTextViewFullName.setText(user.getFullName());
            mTextViewEmail.setText(user.getEmail());
            mTextViewFollowers.setText(String.valueOf(user.getFollowers()));
            mTextViewDescription.setText(user.getDescription());
        }
    }

    @Override
    public void showLoading() {
        mViewUserDetail.setVisibility(View.GONE);
        mViewProgress.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        mViewUserDetail.setVisibility(View.VISIBLE);
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
    public void showError(String message) {
        showToastMessage(message);
    }

    @Override
    public Context context() {
        return getActivity().getApplicationContext();
    }

    /**
     * Loads all users.
     */
    private void loadUserDetails() {
        if (mUserDetailsPresenter != null) {
            mUserDetailsPresenter.initialize();
        }
    }

    @OnClick(R.id.bt_retry)
    void onButtonRetryClick() {
        loadUserDetails();
    }
}
