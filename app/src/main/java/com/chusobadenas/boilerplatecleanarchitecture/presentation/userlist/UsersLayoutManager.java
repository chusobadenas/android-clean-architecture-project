/**
 * Copyright (C) 2014 android10.org. All rights reserved.
 *
 * @author Fernando Cejas (the android10 coder)
 */
package com.chusobadenas.boilerplatecleanarchitecture.presentation.userlist;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

/**
 * Layout manager to position items inside a {@link RecyclerView}.
 */
class UsersLayoutManager extends LinearLayoutManager {

  UsersLayoutManager(Context context) {
    super(context);
  }
}