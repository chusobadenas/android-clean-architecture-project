package com.chusobadenas.boilerplatecleanarchitecture.common.di.modules;

import android.content.Context;
import com.chusobadenas.boilerplatecleanarchitecture.AndroidApplication;
import com.chusobadenas.boilerplatecleanarchitecture.common.di.ApplicationContext;
import com.chusobadenas.boilerplatecleanarchitecture.common.executor.JobExecutor;
import com.chusobadenas.boilerplatecleanarchitecture.common.executor.PostExecutionThread;
import com.chusobadenas.boilerplatecleanarchitecture.common.executor.ThreadExecutor;
import com.chusobadenas.boilerplatecleanarchitecture.common.executor.UIThread;
import com.chusobadenas.boilerplatecleanarchitecture.data.repository.UserDataRepository;
import com.chusobadenas.boilerplatecleanarchitecture.data.repository.remote.APIService;
import com.chusobadenas.boilerplatecleanarchitecture.domain.repository.UserRepository;
import dagger.Module;
import dagger.Provides;

import javax.inject.Singleton;

/**
 * Dagger module that provides objects which will live during the application lifecycle.
 */
@Module
public class ApplicationModule {

  @ApplicationContext
  @Provides
  @Singleton
  Context provideContext(AndroidApplication application) {
    return application.getApplicationContext();
  }

  @Provides
  @Singleton
  APIService provideApiService() {
    return APIService.Creator.newAPIService();
  }

  @Provides
  @Singleton
  PostExecutionThread providePostExecutionThread(UIThread uiThread) {
    return uiThread;
  }

  @Provides
  @Singleton
  ThreadExecutor provideThreadExecutor(JobExecutor jobExecutor) {
    return jobExecutor;
  }

  @Provides
  @Singleton
  UserRepository provideUserRepository(UserDataRepository userDataRepository) {
    return userDataRepository;
  }
}
