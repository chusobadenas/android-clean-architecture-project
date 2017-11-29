package com.chusobadenas.boilerplatecleanarchitecture.common.util;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.chusobadenas.boilerplatecleanarchitecture.R;
import com.chusobadenas.boilerplatecleanarchitecture.common.di.modules.GlideApp;

/**
 * UI utilities class
 */
public final class UIUtils {

  /**
   * Private constructor
   */
  private UIUtils() {
  }

  /**
   * Used to load images in a view with {@link Glide}.
   *
   * @param context the context.
   * @param view    the image view.
   * @param url     the url of the image.
   */
  public static void loadImageUrl(Context context, ImageView view, String url) {
    // Configure image
    GlideApp.with(context)
        .load(url)
        .centerCrop()
        .placeholder(R.color.bg_light_grey)
        .error(R.color.bg_light_grey)
        .transition(DrawableTransitionOptions.withCrossFade())
        .into(view);
  }
}
