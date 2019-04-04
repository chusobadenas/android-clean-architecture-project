package com.chusobadenas.boilerplatecleanarchitecture.presentation.base;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import com.chusobadenas.boilerplatecleanarchitecture.common.di.modules.ActivityModule;
import com.chusobadenas.boilerplatecleanarchitecture.presentation.navigation.Navigator;
import dagger.android.support.DaggerAppCompatActivity;

import javax.inject.Inject;

/**
 * Base {@link AppCompatActivity} class for every Activity in this application.
 */
public abstract class BaseActivity extends DaggerAppCompatActivity {

  @Inject
  protected Navigator navigator;

  /**
   * @return the {@link Navigator}
   */
  public Navigator getNavigator() {
    return navigator;
  }

  /**
   * Adds a {@link Fragment} to this activity's layout.
   *
   * @param containerViewId The container view to where add the fragment.
   * @param fragment The fragment to be added.
   */
  public void addFragment(int containerViewId, Fragment fragment) {
    getSupportFragmentManager().beginTransaction()
        .add(containerViewId, fragment)
        .commit();
  }

  /**
   * Replaces a {@link Fragment} to this activity's layout.
   *
   * @param containerViewId The container view to where replace the fragment.
   * @param fragment The fragment to be replaced.
   * @param addToBackStack true to add this fragment to the back stack, false otherwise
   */
  public void replaceFragment(int containerViewId, Fragment fragment, boolean addToBackStack) {
    FragmentTransaction transaction = getSupportFragmentManager().beginTransaction()
        .replace(containerViewId, fragment);

    if (addToBackStack) {
      transaction = transaction.addToBackStack(null);
    }

    transaction.commit();
  }

  /**
   * Gets the current fragment of the container
   *
   * @param containerId the container id
   *
   * @return the current fragment
   */
  public Fragment getCurrentFragment(int containerId) {
    return getSupportFragmentManager().findFragmentById(containerId);
  }

  /**
   * Get an Activity module for dependency injection.
   *
   * @return {@link ActivityModule}
   */
  protected ActivityModule getActivityModule() {
    return new ActivityModule(this);
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    boolean result = super.onOptionsItemSelected(item);

    if (android.R.id.home == item.getItemId()) {
      onBackPressed();
      result = true;
    }

    return result;
  }

  @Override
  public void onBackPressed() {
    android.support.v4.app.FragmentManager fragmentManager = getSupportFragmentManager();

    if (fragmentManager.getBackStackEntryCount() > 0) {
      fragmentManager.popBackStack();
    } else {
      super.onBackPressed();
    }
  }
}
