package com.chusobadenas.boilerplatecleanarchitecture.data.repository;

import com.chusobadenas.boilerplatecleanarchitecture.data.entity.UserEntity;
import com.chusobadenas.boilerplatecleanarchitecture.data.entity.mapper.UserEntityDataMapper;
import com.chusobadenas.boilerplatecleanarchitecture.data.repository.remote.APIService;
import com.chusobadenas.boilerplatecleanarchitecture.domain.User;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.observers.TestObserver;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertSame;
import static org.mockito.Mockito.when;

public class UserDataRepositoryTest {

  private UserDataRepository userDataRepository;

  @Mock
  private APIService apiService;

  @Before
  public void setUp() {
    MockitoAnnotations.initMocks(this);
    userDataRepository = new UserDataRepository(apiService, new UserEntityDataMapper());
  }

  @Test
  public void testGetUsers() {
    List<UserEntity> userEntities = new ArrayList<>();
    UserEntity userEntity = new UserEntity();
    userEntity.setUserId(1);
    userEntities.add(userEntity);
    Observable<List<UserEntity>> observableUserEntities = Observable.just(userEntities);

    when(apiService.userEntityList()).thenReturn(observableUserEntities);

    Observable<List<User>> observable = userDataRepository.users();
    TestObserver<List<User>> testObserver = observable.test();
    testObserver.assertNoErrors();
    List<User> userList = testObserver.values().get(0);

    assertFalse(userList.isEmpty());
    assertSame(userList.size(), 1);
    assertSame(userList.get(0).getUserId(), 1);
  }

  @Test
  public void testGetUserById() {
    UserEntity userEntity = new UserEntity();
    userEntity.setUserId(1);
    Observable<UserEntity> observableUserEntity = Observable.just(userEntity);

    when(apiService.userEntityById(1)).thenReturn(observableUserEntity);

    Observable<User> observable = userDataRepository.user(1);
    TestObserver<User> testObserver = observable.test();
    testObserver.assertNoErrors();
    User user = testObserver.values().get(0);

    assertSame(user.getUserId(), 1);
  }
}
