package com.chusobadenas.boilerplatecleanarchitecture;

import com.chusobadenas.boilerplatecleanarchitecture.common.di.components.DaggerApplicationComponent;
import dagger.android.AndroidInjector;
import dagger.android.DaggerApplication;

/**
 * Android Main Application
 */
public class AndroidApplication extends DaggerApplication {

  @Override
  protected AndroidInjector<? extends DaggerApplication> applicationInjector() {
    return DaggerApplicationComponent
        .builder()
        .application(this)
        .build();
  }
}
