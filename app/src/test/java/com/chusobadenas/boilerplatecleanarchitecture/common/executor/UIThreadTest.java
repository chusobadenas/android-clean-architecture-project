package com.chusobadenas.boilerplatecleanarchitecture.common.executor;

import com.chusobadenas.boilerplatecleanarchitecture.AndroidApplicationTest;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import static org.junit.Assert.assertNotNull;

@RunWith(RobolectricTestRunner.class)
@Config(application = AndroidApplicationTest.class, sdk = 27)
public class UIThreadTest {

  @Test
  public void testGetSchedulerNotNull() {
    UIThread uiThread = new UIThread();
    assertNotNull(uiThread.getScheduler());
  }
}
