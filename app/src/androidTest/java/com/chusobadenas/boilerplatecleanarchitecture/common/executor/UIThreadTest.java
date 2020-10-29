package com.chusobadenas.boilerplatecleanarchitecture.common.executor;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertNotNull;

@RunWith(AndroidJUnit4.class)
public class UIThreadTest {

  @Test
  public void testGetSchedulerNotNull() {
    UIThread uiThread = new UIThread();
    assertNotNull(uiThread.getScheduler());
  }
}
