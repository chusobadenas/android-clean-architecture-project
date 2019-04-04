package com.chusobadenas.boilerplatecleanarchitecture.common.di.modules;

import com.chusobadenas.boilerplatecleanarchitecture.common.di.PerActivity;
import com.chusobadenas.boilerplatecleanarchitecture.presentation.base.BaseActivity;
import com.chusobadenas.boilerplatecleanarchitecture.presentation.userdetails.UserDetailsActivity;
import com.chusobadenas.boilerplatecleanarchitecture.presentation.userlist.MainActivity;
import com.chusobadenas.boilerplatecleanarchitecture.presentation.userlist.UserListActivity;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityBindingsModule {

  @PerActivity
  @ContributesAndroidInjector
  abstract BaseActivity contributeBaseActivityInjector();

  @PerActivity
  @ContributesAndroidInjector
  abstract MainActivity contributeMainActivityInjector();

  @PerActivity
  @ContributesAndroidInjector(modules = UserModule.class)
  abstract UserListActivity contributeUserListActivityInjector();

  @PerActivity
  @ContributesAndroidInjector(modules = UserModule.class)
  abstract UserDetailsActivity contributeUserDetailsActivityInjector();
}
