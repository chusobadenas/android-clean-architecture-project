package com.chusobadenas.boilerplatecleanarchitecture.domain.interactor;

import com.chusobadenas.boilerplatecleanarchitecture.common.executor.PostExecutionThread;
import com.chusobadenas.boilerplatecleanarchitecture.common.executor.ThreadExecutor;
import com.chusobadenas.boilerplatecleanarchitecture.domain.repository.UserRepository;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.verifyZeroInteractions;

public class GetUserListTest {

    private GetUserList mGetUserList;

    @Mock
    private UserRepository mMockUserRepository;
    @Mock
    private ThreadExecutor mMockThreadExecutor;
    @Mock
    private PostExecutionThread mMockPostExecutionThread;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        mGetUserList = new GetUserList(mMockUserRepository, mMockThreadExecutor, mMockPostExecutionThread);
    }

    @Test
    public void testGetUsers() {
        mGetUserList.buildUseCaseObservable();
        verify(mMockUserRepository).users();
        verifyNoMoreInteractions(mMockUserRepository);
        verifyZeroInteractions(mMockThreadExecutor);
        verifyZeroInteractions(mMockPostExecutionThread);
    }
}
