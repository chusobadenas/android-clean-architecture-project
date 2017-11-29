package com.chusobadenas.boilerplatecleanarchitecture.common.di.modules;

import android.app.Activity;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class ActivityModuleTest {

  private ActivityModule activityModule;

  @Mock
  private Activity mockActivity;

  @Before
  public void setUp() {
    MockitoAnnotations.initMocks(this);
    activityModule = new ActivityModule(mockActivity);
  }

  @Test
  public void testGetActivitySuccess() {
    Activity activity = activityModule.activity();
    assertNotNull(activity);
    assertEquals(activity, mockActivity);
  }
}
