package com.chusobadenas.boilerplatecleanarchitecture.common.di.modules;

import com.chusobadenas.boilerplatecleanarchitecture.common.di.PerActivity;
import com.chusobadenas.boilerplatecleanarchitecture.presentation.base.BaseFragment;
import com.chusobadenas.boilerplatecleanarchitecture.presentation.userdetails.UserDetailsFragment;
import com.chusobadenas.boilerplatecleanarchitecture.presentation.userlist.UserListFragment;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class FragmentBindingsModule {

  @PerActivity
  @ContributesAndroidInjector
  abstract BaseFragment contributeBaseFragmentInjector();

  @PerActivity
  @ContributesAndroidInjector(modules = UserModule.class)
  abstract UserListFragment contributeUserListFragmentInjector();

  @PerActivity
  @ContributesAndroidInjector(modules = UserModule.class)
  abstract UserDetailsFragment contributeUserDetailsFragmentInjector();
}
