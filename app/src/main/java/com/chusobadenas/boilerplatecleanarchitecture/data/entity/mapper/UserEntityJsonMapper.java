package com.chusobadenas.boilerplatecleanarchitecture.data.entity.mapper;

import com.chusobadenas.boilerplatecleanarchitecture.data.entity.UserEntity;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

import javax.inject.Inject;

/**
 * Class used to transform from Strings representing json to valid objects.
 */
public class UserEntityJsonMapper {

  private final Gson gson;

  @Inject
  public UserEntityJsonMapper() {
    this.gson = new Gson();
  }

  /**
   * Transform from valid json string to {@link UserEntity}.
   *
   * @param userJsonResponse A json representing a user profile.
   * @return {@link UserEntity}.
   */
  public UserEntity transformUserEntity(String userJsonResponse) {
    Type userEntityType = new TypeToken<UserEntity>() {
    }.getType();
    return this.gson.fromJson(userJsonResponse, userEntityType);
  }

  /**
   * Transform from valid json string to List of {@link UserEntity}.
   *
   * @param userListJsonResponse A json representing a collection of users.
   * @return List of {@link UserEntity}.
   */
  public List<UserEntity> transformUserEntityCollection(String userListJsonResponse) {
    Type listOfUserEntityType = new TypeToken<List<UserEntity>>() {}.getType();
    return this.gson.fromJson(userListJsonResponse, listOfUserEntityType);
  }
}
