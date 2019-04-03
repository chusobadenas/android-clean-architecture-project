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
package com.chusobadenas.boilerplatecleanarchitecture.common.di.components;

import android.content.Context;
import com.chusobadenas.boilerplatecleanarchitecture.AndroidApplication;
import com.chusobadenas.boilerplatecleanarchitecture.common.di.ApplicationContext;
import com.chusobadenas.boilerplatecleanarchitecture.common.di.modules.ApplicationModule;
import com.chusobadenas.boilerplatecleanarchitecture.common.executor.PostExecutionThread;
import com.chusobadenas.boilerplatecleanarchitecture.common.executor.ThreadExecutor;
import com.chusobadenas.boilerplatecleanarchitecture.data.repository.UserDataRepository;
import com.chusobadenas.boilerplatecleanarchitecture.data.repository.remote.APIService;
import com.chusobadenas.boilerplatecleanarchitecture.presentation.base.BaseActivity;
import com.chusobadenas.boilerplatecleanarchitecture.presentation.navigation.Navigator;
import dagger.BindsInstance;
import dagger.Component;

import javax.inject.Singleton;

/**
 * A component whose lifetime is the life of the application.
 */
@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {

  @Component.Builder
  interface Builder {

    ApplicationComponent build();

    @BindsInstance
    Builder application(AndroidApplication application);
  }

  void inject(BaseActivity baseActivity);

  APIService apiService();

  @ApplicationContext
  Context context();

  Navigator navigator();

  PostExecutionThread postExecutionThread();

  ThreadExecutor threadExecutor();

  UserDataRepository userDataRepository();
}
