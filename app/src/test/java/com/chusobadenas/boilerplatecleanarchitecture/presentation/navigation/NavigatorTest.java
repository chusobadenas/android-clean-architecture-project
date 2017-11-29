package com.chusobadenas.boilerplatecleanarchitecture.presentation.navigation;

import android.content.ActivityNotFoundException;

import com.chusobadenas.boilerplatecleanarchitecture.AndroidApplicationTest;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.annotation.Config;

import static org.junit.Assert.fail;

@RunWith(RobolectricTestRunner.class)
@Config(application = AndroidApplicationTest.class, sdk = 23)
public class NavigatorTest {

  private Navigator navigator;

  @Before
  public void setUp() {
    navigator = new Navigator();
  }

  @Test
  public void testNavigateToUserListSuccess() {
    try {
      navigator.navigateToUserList(RuntimeEnvironment.application);
    } catch (ActivityNotFoundException exception) {
      fail("UserListActivity not found");
    }
  }

  @Test
  public void testNavigateToUserDetailsSuccess() {
    try {
      navigator.navigateToUserDetails(RuntimeEnvironment.application, 1);
    } catch (ActivityNotFoundException exception) {
      fail("UserDetailsActivity not found");
    }
  }
}
