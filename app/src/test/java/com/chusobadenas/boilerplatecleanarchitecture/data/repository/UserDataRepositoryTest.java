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

import rx.Observable;
import rx.functions.Action1;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertSame;
import static org.mockito.Mockito.when;

public class UserDataRepositoryTest {

    private UserDataRepository mUserDataRepository;

    @Mock
    private APIService mMockApiService;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        mUserDataRepository = new UserDataRepository(mMockApiService, new UserEntityDataMapper());
    }

    @Test
    public void testGetUsers() {
        List<UserEntity> userEntities = new ArrayList<>();
        UserEntity userEntity = new UserEntity();
        userEntity.setUserId(1);
        userEntities.add(userEntity);
        Observable<List<UserEntity>> observableUserEntities = Observable.just(userEntities);

        when(mMockApiService.userEntityList()).thenReturn(observableUserEntities);

        Observable<List<User>> observable = mUserDataRepository.users();
        observable.subscribe(new Action1<List<User>>() {
            @Override
            public void call(List<User> users) {
                assertFalse(users.isEmpty());
                assertSame(users.size(), 1);
                assertSame(users.get(0).getUserId(), 1);
            }
        });
    }

    @Test
    public void testGetUserById() {
        UserEntity userEntity = new UserEntity();
        userEntity.setUserId(1);
        Observable<UserEntity> observableUserEntity = Observable.just(userEntity);

        when(mMockApiService.userEntityById(1)).thenReturn(observableUserEntity);

        Observable<User> observable = mUserDataRepository.user(1);
        observable.subscribe(new Action1<User>() {
            @Override
            public void call(User user) {
                assertSame(user.getUserId(), 1);
            }
        });
    }
}
