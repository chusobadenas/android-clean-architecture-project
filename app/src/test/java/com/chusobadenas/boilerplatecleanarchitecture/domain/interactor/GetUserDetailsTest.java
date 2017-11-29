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

public class GetUserDetailsTest {

  private GetUserDetails getUserDetails;

  @Mock
  private UserRepository userRepository;
  @Mock
  private ThreadExecutor threadExecutor;
  @Mock
  private PostExecutionThread postExecutionThread;

  @Before
  public void setUp() {
    MockitoAnnotations.initMocks(this);
    getUserDetails = new GetUserDetails(userRepository, threadExecutor, postExecutionThread);
  }

  @Test
  public void testGetUserById() {
    getUserDetails.buildUseCaseObservable(1);
    verify(userRepository).user(1);
    verifyNoMoreInteractions(userRepository);
    verifyZeroInteractions(threadExecutor);
    verifyZeroInteractions(postExecutionThread);
  }
}
