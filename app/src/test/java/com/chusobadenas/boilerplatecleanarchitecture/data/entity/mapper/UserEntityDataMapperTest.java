package com.chusobadenas.boilerplatecleanarchitecture.data.entity.mapper;

import com.chusobadenas.boilerplatecleanarchitecture.data.entity.UserEntity;
import com.chusobadenas.boilerplatecleanarchitecture.domain.User;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertSame;

public class UserEntityDataMapperTest {

  private static final String COVER_URL = "coverUrl";
  private static final String DESCRIPTION = "description";
  private static final String EMAIL = "email";
  private static final String FULL_NAME = "fullName";

  private static final int FOLLOWERS = 10;
  private static final int USER_ID = 1;

  private UserEntityDataMapper userEntityDataMapper;

  @Before
  public void setUp() {
    userEntityDataMapper = new UserEntityDataMapper();
  }

  @Test
  public void testTransformToUser() {
    UserEntity userEntity = new UserEntity();
    userEntity.setCoverUrl(COVER_URL);
    userEntity.setDescription(DESCRIPTION);
    userEntity.setEmail(EMAIL);
    userEntity.setFollowers(FOLLOWERS);
    userEntity.setFullname(FULL_NAME);
    userEntity.setUserId(USER_ID);

    User user = userEntityDataMapper.transform(userEntity);

    assertEquals(user.getCoverUrl(), COVER_URL);
    assertEquals(user.getDescription(), DESCRIPTION);
    assertEquals(user.getEmail(), EMAIL);
    assertSame(user.getFollowers(), FOLLOWERS);
    assertEquals(user.getFullName(), FULL_NAME);
    assertSame(user.getUserId(), USER_ID);
  }

  @Test
  public void testTransformToUserCollection() {
    List<UserEntity> userEntities = new ArrayList<>();
    UserEntity userEntity = new UserEntity();
    userEntity.setCoverUrl(COVER_URL);
    userEntity.setDescription(DESCRIPTION);
    userEntity.setEmail(EMAIL);
    userEntity.setFollowers(FOLLOWERS);
    userEntity.setFullname(FULL_NAME);
    userEntity.setUserId(USER_ID);
    userEntities.add(userEntity);

    List<User> users = userEntityDataMapper.transform(userEntities);

    assertFalse(users.isEmpty());
    assertSame(users.size(), 1);

    User user = users.get(0);
    assertEquals(user.getCoverUrl(), COVER_URL);
    assertEquals(user.getDescription(), DESCRIPTION);
    assertEquals(user.getEmail(), EMAIL);
    assertSame(user.getFollowers(), FOLLOWERS);
    assertEquals(user.getFullName(), FULL_NAME);
    assertSame(user.getUserId(), USER_ID);
  }
}
