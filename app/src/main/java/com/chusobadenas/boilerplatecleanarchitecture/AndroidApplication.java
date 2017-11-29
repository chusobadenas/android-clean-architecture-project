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
package com.chusobadenas.boilerplatecleanarchitecture;

import android.app.Application;

import com.chusobadenas.boilerplatecleanarchitecture.common.di.HasComponent;
import com.chusobadenas.boilerplatecleanarchitecture.common.di.components.ApplicationComponent;
import com.chusobadenas.boilerplatecleanarchitecture.common.di.components.DaggerApplicationComponent;
import com.chusobadenas.boilerplatecleanarchitecture.common.di.modules.ApplicationModule;

/**
 * Android Main Application
 */
public class AndroidApplication extends Application implements HasComponent<ApplicationComponent> {

  private ApplicationComponent applicationComponent;

  @Override
  public void onCreate() {
    super.onCreate();
    initializeInjector();
  }

  @Override
  public ApplicationComponent getComponent() {
    return applicationComponent;
  }

  protected void initializeInjector() {
    applicationComponent = DaggerApplicationComponent.builder()
        .applicationModule(new ApplicationModule(this))
        .build();
  }
}
