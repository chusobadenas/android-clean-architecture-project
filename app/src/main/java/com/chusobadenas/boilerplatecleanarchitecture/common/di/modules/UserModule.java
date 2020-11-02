package com.chusobadenas.boilerplatecleanarchitecture.common.di.modules;

import com.chusobadenas.boilerplatecleanarchitecture.common.executor.PostExecutionThread;
import com.chusobadenas.boilerplatecleanarchitecture.common.executor.ThreadExecutor;
import com.chusobadenas.boilerplatecleanarchitecture.data.repository.UserDataRepository;
import com.chusobadenas.boilerplatecleanarchitecture.data.repository.remote.APIService;
import com.chusobadenas.boilerplatecleanarchitecture.domain.interactor.GetUserDetails;
import com.chusobadenas.boilerplatecleanarchitecture.domain.interactor.GetUserList;
import com.chusobadenas.boilerplatecleanarchitecture.domain.interactor.UseCase;
import com.chusobadenas.boilerplatecleanarchitecture.domain.repository.UserRepository;
import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.components.ActivityComponent;
import dagger.hilt.android.components.FragmentComponent;

import javax.inject.Named;

@InstallIn({ ActivityComponent.class, FragmentComponent.class })
@Module
public class UserModule {

  @Provides
  public APIService provideApiService() {
    return APIService.Creator.newAPIService();
  }

  @Provides
  public UserRepository provideUserRepository(UserDataRepository userDataRepository) {
    return userDataRepository;
  }

  @Provides
  @Named("userList")
  public UseCase provideGetUserListUseCase(
      UserDataRepository userDataRepository,
      ThreadExecutor threadExecutor,
      PostExecutionThread postExecutionThread
  ) {
    return new GetUserList(userDataRepository, threadExecutor, postExecutionThread);
  }

  @Provides
  @Named("userDetails")
  public UseCase provideGetUserDetailsUseCase(
      UserDataRepository userDataRepository,
      ThreadExecutor threadExecutor,
      PostExecutionThread postExecutionThread
  ) {
    return new GetUserDetails(userDataRepository, threadExecutor, postExecutionThread);
  }
}
