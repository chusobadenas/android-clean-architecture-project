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
package com.chusobadenas.boilerplatecleanarchitecture.data.repository;

import com.chusobadenas.boilerplatecleanarchitecture.data.entity.UserEntity;
import com.chusobadenas.boilerplatecleanarchitecture.data.entity.mapper.UserEntityDataMapper;
import com.chusobadenas.boilerplatecleanarchitecture.data.repository.remote.APIService;
import com.chusobadenas.boilerplatecleanarchitecture.domain.User;
import com.chusobadenas.boilerplatecleanarchitecture.domain.repository.UserRepository;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import rx.Observable;
import rx.functions.Func1;

/**
 * {@link UserRepository} for retrieving user data.
 */
@Singleton
public class UserDataRepository implements UserRepository {

    private final APIService mApiService;
    private final UserEntityDataMapper mUserEntityDataMapper;

    /**
     * Constructs a {@link UserRepository}.
     *
     * @param apiService           API service
     * @param userEntityDataMapper User mapper
     */
    @Inject
    public UserDataRepository(APIService apiService, UserEntityDataMapper userEntityDataMapper) {
        this.mApiService = apiService;
        this.mUserEntityDataMapper = userEntityDataMapper;
    }

    @Override
    public Observable<List<User>> users() {
        return mApiService.userEntityList().map(new Func1<List<UserEntity>, List<User>>() {
            @Override
            public List<User> call(List<UserEntity> userEntities) {
                return mUserEntityDataMapper.transform(userEntities);
            }
        });
    }

    @Override
    public Observable<User> user(int userId) {
        return mApiService.userEntityById(userId).map(new Func1<UserEntity, User>() {
            @Override
            public User call(UserEntity userEntity) {
                return mUserEntityDataMapper.transform(userEntity);
            }
        });
    }
}
