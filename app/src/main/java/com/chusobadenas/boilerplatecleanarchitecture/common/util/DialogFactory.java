package com.chusobadenas.boilerplatecleanarchitecture.common.util;

import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.widget.ArrayAdapter;
import android.widget.Button;

import com.chusobadenas.boilerplatecleanarchitecture.R;

import java.util.List;

/**
 * Class for creating dialogs
 */
public final class DialogFactory {

  public enum DialogType {
    CONFIRM, PROGRESS, SIMPLE
  }

  /**
   * Private constructor
   */
  private DialogFactory() {
  }

  /**
   * Creates a confirm dialog
   *
   * @param context      the context
   * @param title        the title to display
   * @param message      the message to display
   * @param buttonTextId resource id of the button text
   * @param action       the action to perform
   * @return the dialog
   */
  private static AlertDialog createConfirmDialog(Context context, String title, String message,
                                                 int buttonTextId, OnClickListener action) {
    // Create dialog
    AlertDialog.Builder builder = new AlertDialog.Builder(context)
        .setCancelable(false)
        .setTitle(title)
        .setMessage(message)
        .setNegativeButton(android.R.string.cancel, null)
        .setPositiveButton(buttonTextId, action);

    // Return the dialog
    return builder.create();
  }

  /**
   * Creates a progress dialog
   *
   * @param context the context
   * @return the dialog
   */
  private static AlertDialog createProgressDialog(Context context) {
    // Create dialog
    AlertDialog.Builder builder = new AlertDialog.Builder(context)
        .setCancelable(false)
        .setView(R.layout.loading_progress_view);

    return builder.create();
  }

  /**
   * Creates a simple dialog
   *
   * @param context the context
   * @param title   the title
   * @param message the message
   * @param action  the action to perform
   * @return the dialog
   */
  private static AlertDialog createSimpleDialog(Context context, String title, String message,
                                                OnClickListener action) {
    // Create dialog
    AlertDialog.Builder builder = new AlertDialog.Builder(context)
        .setCancelable(false)
        .setTitle(title)
        .setMessage(message)
        .setNeutralButton(android.R.string.ok, action);

    // Return the dialog
    return builder.create();
  }

  /**
   * Creates a list dialog
   *
   * @param context  the context
   * @param title    the title to display
   * @param items    the list of items to show in the dialog
   * @param listener the action listener
   * @return the dialog
   */
  public static AlertDialog createListDialog(Context context, String title, List<String> items,
                                             OnClickListener listener) {
    // Create adapter
    ArrayAdapter<String> adapter = new ArrayAdapter<>(context, R.layout.list_item_single_choice, items);

    // Create dialog
    AlertDialog.Builder builder = new AlertDialog.Builder(context)
        .setTitle(title)
        .setAdapter(adapter, listener);

    // Return the dialog
    return builder.create();
  }

  private static void setButtonColor(Context context, AlertDialog dialog, int buttonType) {
    // Get color accent
    int color = ContextCompat.getColor(context, R.color.accent);
    // Set button color
    Button button = dialog.getButton(buttonType);
    if (button != null) {
      button.setTextColor(color);
    }
  }

  /**
   * Creates and shows a dialog
   *
   * @param context      the context
   * @param type         the dialog type
   * @param title        the title to display
   * @param message      the message to display
   * @param buttonTextId resource id of the button text
   * @param action       the action to perform
   * @return the dialog
   */
  public static AlertDialog showDialog(Context context, DialogType type, String title,
                                       String message, int buttonTextId, OnClickListener action) {
    // Create dialog
    AlertDialog dialog = null;
    switch (type) {
      case CONFIRM:
        dialog = DialogFactory.createConfirmDialog(context, title, message, buttonTextId, action);
        break;
      case PROGRESS:
        dialog = DialogFactory.createProgressDialog(context);
        break;
      case SIMPLE:
        dialog = DialogFactory.createSimpleDialog(context, title, message, action);
        break;
      default:
        break;
    }

    if (dialog != null) {
      // Show dialog
      dialog.show();
      // Change buttons color
      setButtonColor(context, dialog, DialogInterface.BUTTON_NEUTRAL);
      setButtonColor(context, dialog, DialogInterface.BUTTON_POSITIVE);
    }

    return dialog;
  }
}
