package com.chusobadenas.boilerplatecleanarchitecture.data.entity.mapper;

import com.chusobadenas.boilerplatecleanarchitecture.data.entity.UserEntity;
import com.chusobadenas.boilerplatecleanarchitecture.domain.User;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Mapper class used to transform {@link UserEntity} (in the data layer) to {@link User} in the
 * domain layer.
 */
@Singleton
public class UserEntityDataMapper {

  @Inject
  public UserEntityDataMapper() {
  }

  /**
   * Transform a {@link UserEntity} into an {@link User}.
   *
   * @param userEntity Object to be transformed.
   * @return {@link User} if valid {@link UserEntity} otherwise null.
   */
  public User transform(UserEntity userEntity) {
    User user = null;

    if (userEntity != null) {
      user = new User(userEntity.getUserId());
      user.setCoverUrl(userEntity.getCoverUrl());
      user.setFullName(userEntity.getFullname());
      user.setDescription(userEntity.getDescription());
      user.setFollowers(userEntity.getFollowers());
      user.setEmail(userEntity.getEmail());
    }

    return user;
  }

  /**
   * Transform a List of {@link UserEntity} into a Collection of {@link User}.
   *
   * @param userEntityCollection Object Collection to be transformed.
   * @return {@link User} if valid {@link UserEntity} otherwise null.
   */
  public List<User> transform(Collection<UserEntity> userEntityCollection) {
    List<User> userList = new ArrayList<>();

    for (UserEntity userEntity : userEntityCollection) {
      User user = transform(userEntity);
      if (user != null) {
        userList.add(user);
      }
    }

    return userList;
  }
}
