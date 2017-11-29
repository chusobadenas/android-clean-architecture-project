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
package com.chusobadenas.boilerplatecleanarchitecture.data.repository;

import com.chusobadenas.boilerplatecleanarchitecture.data.entity.UserEntity;
import com.chusobadenas.boilerplatecleanarchitecture.data.entity.mapper.UserEntityDataMapper;
import com.chusobadenas.boilerplatecleanarchitecture.data.repository.remote.APIService;
import com.chusobadenas.boilerplatecleanarchitecture.domain.User;
import com.chusobadenas.boilerplatecleanarchitecture.domain.repository.UserRepository;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Observable;
import io.reactivex.functions.Function;

/**
 * {@link UserRepository} for retrieving user data.
 */
@Singleton
public class UserDataRepository implements UserRepository {

  private final APIService apiService;
  private final UserEntityDataMapper userEntityDataMapper;

  /**
   * Constructs a {@link UserRepository}.
   *
   * @param apiService           API service
   * @param userEntityDataMapper User mapper
   */
  @Inject
  public UserDataRepository(APIService apiService, UserEntityDataMapper userEntityDataMapper) {
    this.apiService = apiService;
    this.userEntityDataMapper = userEntityDataMapper;
  }

  @Override
  public Observable<List<User>> users() {
    return apiService.userEntityList().map(new Function<List<UserEntity>, List<User>>() {
      @Override
      public List<User> apply(List<UserEntity> userEntities) {
        return userEntityDataMapper.transform(userEntities);
      }
    });
  }

  @Override
  public Observable<User> user(int userId) {
    return apiService.userEntityById(userId).map(new Function<UserEntity, User>() {
      @Override
      public User apply(UserEntity userEntity) {
        return userEntityDataMapper.transform(userEntity);
      }
    });
  }
}
