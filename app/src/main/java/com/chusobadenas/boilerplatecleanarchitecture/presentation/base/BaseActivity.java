package com.chusobadenas.boilerplatecleanarchitecture.presentation.base;

import android.view.MenuItem;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import com.chusobadenas.boilerplatecleanarchitecture.presentation.navigation.Navigator;
import dagger.hilt.android.AndroidEntryPoint;

import javax.inject.Inject;

/**
 * Base {@link AppCompatActivity} class for every Activity in this application.
 */
@AndroidEntryPoint
public abstract class BaseActivity extends AppCompatActivity {

  @Inject
  protected Navigator navigator;

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
    FragmentManager fragmentManager = getSupportFragmentManager();

    if (fragmentManager.getBackStackEntryCount() > 0) {
      fragmentManager.popBackStack();
    } else {
      super.onBackPressed();
    }
  }
}
