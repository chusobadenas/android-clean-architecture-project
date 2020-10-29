package com.chusobadenas.boilerplatecleanarchitecture.domain.interactor;

import com.chusobadenas.boilerplatecleanarchitecture.common.executor.PostExecutionThread;
import com.chusobadenas.boilerplatecleanarchitecture.common.executor.ThreadExecutor;
import com.chusobadenas.boilerplatecleanarchitecture.domain.repository.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.verifyNoMoreInteractions;

public class GetUserListTest {

  private GetUserList getUserList;

  @Mock
  private UserRepository userRepository;
  @Mock
  private ThreadExecutor threadExecutor;
  @Mock
  private PostExecutionThread postExecutionThread;

  @Before
  public void setUp() {
    MockitoAnnotations.openMocks(this);
    getUserList = new GetUserList(userRepository, threadExecutor, postExecutionThread);
  }

  @Test
  public void testGetUsers() {
    getUserList.buildUseCaseObservable();
    verify(userRepository).users();
    verifyNoMoreInteractions(userRepository);
    verifyNoInteractions(threadExecutor);
    verifyNoInteractions(postExecutionThread);
  }
}
