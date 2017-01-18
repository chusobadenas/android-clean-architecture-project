/**
 * Copyright (C) 2015 Fernando Cejas Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
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

    private ApplicationComponent mApplicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        initializeInjector();
    }

    @Override
    public ApplicationComponent getComponent() {
        return mApplicationComponent;
    }

    protected void initializeInjector() {
        mApplicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .build();
    }
}
