package com.chusobadenas.boilerplatecleanarchitecture.common.di.components;

import android.content.Context;
import com.chusobadenas.boilerplatecleanarchitecture.AndroidApplication;
import com.chusobadenas.boilerplatecleanarchitecture.common.di.ApplicationContext;
import com.chusobadenas.boilerplatecleanarchitecture.common.di.modules.ActivityBindingsModule;
import com.chusobadenas.boilerplatecleanarchitecture.common.di.modules.ApplicationModule;
import com.chusobadenas.boilerplatecleanarchitecture.common.di.modules.FragmentBindingsModule;
import com.chusobadenas.boilerplatecleanarchitecture.common.executor.PostExecutionThread;
import com.chusobadenas.boilerplatecleanarchitecture.common.executor.ThreadExecutor;
import com.chusobadenas.boilerplatecleanarchitecture.data.repository.UserDataRepository;
import com.chusobadenas.boilerplatecleanarchitecture.data.repository.remote.APIService;
import com.chusobadenas.boilerplatecleanarchitecture.presentation.navigation.Navigator;
import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjectionModule;
import dagger.android.AndroidInjector;

import javax.inject.Singleton;

/**
 * A component whose lifetime is the life of the application.
 */
@Singleton
@Component(modules = {
    AndroidInjectionModule.class,
    ActivityBindingsModule.class,
    FragmentBindingsModule.class,
    ApplicationModule.class
})
public interface ApplicationComponent extends AndroidInjector<AndroidApplication> {

  @Component.Builder
  interface Builder {

    ApplicationComponent build();

    @BindsInstance
    Builder application(AndroidApplication application);
  }

  APIService apiService();

  @ApplicationContext
  Context context();

  Navigator navigator();

  PostExecutionThread postExecutionThread();

  ThreadExecutor threadExecutor();

  UserDataRepository userDataRepository();
}
