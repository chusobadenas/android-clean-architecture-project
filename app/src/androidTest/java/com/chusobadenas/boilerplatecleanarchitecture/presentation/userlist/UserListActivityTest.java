package com.chusobadenas.boilerplatecleanarchitecture.presentation.userlist;

import androidx.lifecycle.Lifecycle;
import androidx.test.core.app.ActivityScenario;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import com.chusobadenas.boilerplatecleanarchitecture.R;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

@RunWith(AndroidJUnit4.class)
public class UserListActivityTest {

  @Test
  public void testNavigateToUserDetailsSuccess() {
    // Create activity
    ActivityScenario<UserListActivity> scenario = ActivityScenario.launch(UserListActivity.class);
    scenario.moveToState(Lifecycle.State.RESUMED);

    try {
      // Navigate to user details
      Thread.sleep(1000);
      onView(withId(R.id.rv_users)).perform(actionOnItemAtPosition(0, click()));

      // Verify fragment is opened
      Thread.sleep(500);
      onView(withId(R.id.iv_cover)).check(matches((isDisplayed())));
    } catch (InterruptedException exception) {
      Assert.fail();
    }
  }
}
