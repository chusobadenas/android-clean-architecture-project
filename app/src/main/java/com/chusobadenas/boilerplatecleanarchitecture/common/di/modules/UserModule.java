/**
 * Copyright (C) 2015 Fernando Cejas Open Source Project
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
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