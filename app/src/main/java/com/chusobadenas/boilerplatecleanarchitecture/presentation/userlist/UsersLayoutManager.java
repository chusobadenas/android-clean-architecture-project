package com.chusobadenas.boilerplatecleanarchitecture.presentation.userlist;

import android.content.Context;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Layout manager to position items inside a {@link RecyclerView}.
 */
class UsersLayoutManager extends LinearLayoutManager {

  UsersLayoutManager(Context context) {
    super(context);
  }
}
