package com.chusobadenas.boilerplatecleanarchitecture.common.di.modules;

import com.chusobadenas.boilerplatecleanarchitecture.common.executor.PostExecutionThread;
import com.chusobadenas.boilerplatecleanarchitecture.common.executor.ThreadExecutor;
import com.chusobadenas.boilerplatecleanarchitecture.data.repository.UserDataRepository;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.assertNotNull;

public class UserModuleTest {

  private UserModule userModule;

  @Mock
  private UserDataRepository userDataRepository;
  @Mock
  private ThreadExecutor threadExecutor;
  @Mock
  private PostExecutionThread postExecutionThread;

  @Before
  public void setUp() {
    MockitoAnnotations.initMocks(this);
    userModule = new UserModule();
  }

  @Test
  public void testProvideGetUserList() {
    assertNotNull(userModule.provideGetUserListUseCase(userDataRepository, threadExecutor,
        postExecutionThread));
  }

  @Test
  public void testProvideGetUserDetailsUseCase() {
    assertNotNull(userModule.provideGetUserDetailsUseCase(userDataRepository, threadExecutor,
        postExecutionThread));
  }
}
