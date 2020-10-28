package com.chusobadenas.boilerplatecleanarchitecture.common.di.modules;

import com.chusobadenas.boilerplatecleanarchitecture.common.di.PerActivity;
import com.chusobadenas.boilerplatecleanarchitecture.common.executor.PostExecutionThread;
import com.chusobadenas.boilerplatecleanarchitecture.common.executor.ThreadExecutor;
import com.chusobadenas.boilerplatecleanarchitecture.data.repository.UserDataRepository;
import com.chusobadenas.boilerplatecleanarchitecture.domain.interactor.GetUserDetails;
import com.chusobadenas.boilerplatecleanarchitecture.domain.interactor.GetUserList;
import com.chusobadenas.boilerplatecleanarchitecture.domain.interactor.UseCase;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;

/**
 * Dagger module that provides user related collaborators.
 */
@Module
public class UserModule {

  @Provides
  @PerActivity
  @Named("userList")
  UseCase provideGetUserListUseCase(UserDataRepository userDataRepository, ThreadExecutor
      threadExecutor, PostExecutionThread postExecutionThread) {
    return new GetUserList(userDataRepository, threadExecutor, postExecutionThread);
  }

  @Provides
  @PerActivity
  @Named("userDetails")
  UseCase provideGetUserDetailsUseCase(UserDataRepository userDataRepository, ThreadExecutor
      threadExecutor, PostExecutionThread postExecutionThread) {
    return new GetUserDetails(userDataRepository, threadExecutor, postExecutionThread);
  }
}
