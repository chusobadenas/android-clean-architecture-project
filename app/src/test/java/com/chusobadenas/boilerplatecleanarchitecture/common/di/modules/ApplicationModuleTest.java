package com.chusobadenas.boilerplatecleanarchitecture.common.di.modules;

import com.chusobadenas.boilerplatecleanarchitecture.common.executor.JobExecutor;
import com.chusobadenas.boilerplatecleanarchitecture.common.executor.UIThread;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.assertEquals;

public class ApplicationModuleTest {

  private ApplicationModule applicationModule;

  @Mock
  private UIThread uiThread;
  @Mock
  private JobExecutor jobExecutor;

  @Before
  public void setUp() {
    MockitoAnnotations.openMocks(this);
    applicationModule = new ApplicationModule();
  }

  @Test
  public void testProvidePostExecutionThreadSuccess() {
    assertEquals(applicationModule.providePostExecutionThread(uiThread), uiThread);
  }

  @Test
  public void testProvideThreadExecutorSuccess() {
    assertEquals(applicationModule.provideThreadExecutor(jobExecutor), jobExecutor);
  }
}
