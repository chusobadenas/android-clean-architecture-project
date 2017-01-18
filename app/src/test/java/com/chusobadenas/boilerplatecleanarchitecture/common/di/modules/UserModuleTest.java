package com.chusobadenas.boilerplatecleanarchitecture.common.di.modules;

import com.chusobadenas.boilerplatecleanarchitecture.common.executor.PostExecutionThread;
import com.chusobadenas.boilerplatecleanarchitecture.common.executor.ThreadExecutor;
import com.chusobadenas.boilerplatecleanarchitecture.domain.interactor.GetUserList;
import com.chusobadenas.boilerplatecleanarchitecture.domain.repository.UserRepository;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class UserModuleTest {

    private UserModule mUserModule;

    @Mock
    private UserRepository mMockUserRepository;
    @Mock
    private ThreadExecutor mMockThreadExecutor;
    @Mock
    private PostExecutionThread mMockPostExecutionThread;
    @Mock
    private GetUserList mMockGetUserList;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        mUserModule = new UserModule(1);
    }

    @Test
    public void testProvideGetUserList() {
        assertEquals(mUserModule.provideGetUserListUseCase(mMockGetUserList), mMockGetUserList);
    }

    @Test
    public void testProvideGetUserDetailsUseCase() {
        assertNotNull(mUserModule.provideGetUserDetailsUseCase(mMockUserRepository, mMockThreadExecutor,
                mMockPostExecutionThread));
    }
}
