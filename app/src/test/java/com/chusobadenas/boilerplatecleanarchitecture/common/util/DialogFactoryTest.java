package com.chusobadenas.boilerplatecleanarchitecture.common.util;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.widget.ArrayAdapter;
import android.widget.Button;

import com.chusobadenas.boilerplatecleanarchitecture.R;
import com.chusobadenas.boilerplatecleanarchitecture.common.util.DialogFactory.DialogType;

import org.easymock.EasyMock;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.powermock.api.easymock.PowerMock;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

@RunWith(PowerMockRunner.class)
@PrepareForTest({ContextCompat.class, DialogFactory.class})
public class DialogFactoryTest {

  private static final String TITLE = "Say hi";
  private static final String MESSAGE = "Hello, world!";

  @Mock
  Button button;
  @Mock
  Context context;
  @Mock
  AlertDialog mockDialog;
  @Mock
  DialogInterface.OnClickListener listener;

  @Before
  public void setUp() {
    MockitoAnnotations.initMocks(this);
    PowerMockito.mockStatic(ContextCompat.class);
    PowerMockito.when(ContextCompat.getColor(context, R.color.accent)).thenReturn(0);
  }

  @Test
  public void testInstance() throws SecurityException, NoSuchMethodException,
      IllegalArgumentException, InstantiationException, IllegalAccessException,
      InvocationTargetException {
    Constructor<DialogFactory> constructor = DialogFactory.class.getDeclaredConstructor();
    constructor.setAccessible(true);

    assertNotNull(constructor.newInstance());
  }

  @Test
  public void testCreateListDialogSuccess() {
    AlertDialog.Builder builder = PowerMock.createMock(AlertDialog.Builder.class);
    ArrayAdapter arrayAdapter = PowerMock.createMock(ArrayAdapter.class);
    List<String> items = Arrays.asList("option1", "option2", "option3");

    try {
      PowerMock.expectNew(AlertDialog.Builder.class, context).andReturn(builder);
      PowerMock.expectNew(ArrayAdapter.class, new Class[]{Context.class, int.class, List.class},
          context, R.layout.list_item_single_choice, items).andReturn(arrayAdapter);

      EasyMock.expect(builder.setTitle(null)).andReturn(builder);
      EasyMock.expect(builder.setAdapter(arrayAdapter, listener)).andReturn(builder);
      EasyMock.expect(builder.create()).andReturn(mockDialog);

      PowerMock.replayAll();
      AlertDialog dialog = DialogFactory.createListDialog(context, null, items, listener);
      PowerMock.verifyAll();

      assertEquals(dialog, mockDialog);

    } catch (Exception exception) {
      fail(exception.getMessage());
    }
  }

  @Test
  public void testShowSimpleDialogSuccess() {
    AlertDialog.Builder builder = PowerMock.createMock(AlertDialog.Builder.class);

    try {
      PowerMock.expectNew(AlertDialog.Builder.class, context).andReturn(builder);

      EasyMock.expect(builder.setCancelable(false)).andReturn(builder);
      EasyMock.expect(builder.setTitle(TITLE)).andReturn(builder);
      EasyMock.expect(builder.setMessage(MESSAGE)).andReturn(builder);
      EasyMock.expect(builder.setNeutralButton(android.R.string.ok, null)).andReturn(builder);
      EasyMock.expect(builder.create()).andReturn(mockDialog);

      Mockito.when(mockDialog.getButton(DialogInterface.BUTTON_NEUTRAL)).thenReturn(button);

      EasyMock.expectLastCall();
      mockDialog.show();
      EasyMock.expectLastCall();
      button.setTextColor(0);

      PowerMock.replay(builder, AlertDialog.Builder.class);
      AlertDialog dialog = DialogFactory.showDialog(context, DialogType.SIMPLE, TITLE,
          MESSAGE, android.R.string.ok, null);
      PowerMock.verify(builder, AlertDialog.Builder.class);

      assertEquals(dialog, mockDialog);

    } catch (Exception exception) {
      fail(exception.getMessage());
    }
  }

  @Test
  public void testShowConfirmDialogSuccess() {
    AlertDialog.Builder builder = PowerMock.createMock(AlertDialog.Builder.class);

    try {
      PowerMock.expectNew(AlertDialog.Builder.class, context).andReturn(builder);

      EasyMock.expect(builder.setCancelable(false)).andReturn(builder);
      EasyMock.expect(builder.setTitle(TITLE)).andReturn(builder);
      EasyMock.expect(builder.setMessage(MESSAGE)).andReturn(builder);
      EasyMock.expect(builder.setNegativeButton(android.R.string.cancel, null)).andReturn(builder);
      EasyMock.expect(builder.setPositiveButton(android.R.string.ok, listener)).andReturn(builder);
      EasyMock.expect(builder.create()).andReturn(mockDialog);

      Mockito.when(mockDialog.getButton(DialogInterface.BUTTON_POSITIVE)).thenReturn(button);

      EasyMock.expectLastCall();
      mockDialog.show();
      EasyMock.expectLastCall();
      button.setTextColor(0);

      PowerMock.replay(builder, AlertDialog.Builder.class);
      AlertDialog dialog = DialogFactory.showDialog(context, DialogType.CONFIRM, TITLE, MESSAGE,
          android.R.string.ok, listener);
      PowerMock.verify(builder, AlertDialog.Builder.class);

      assertEquals(dialog, mockDialog);

    } catch (Exception exception) {
      fail(exception.getMessage());
    }
  }

  @Test
  public void testShowProgressDialogSuccess() {
    AlertDialog.Builder builder = PowerMock.createMock(AlertDialog.Builder.class);

    try {
      PowerMock.expectNew(AlertDialog.Builder.class, context).andReturn(builder);

      EasyMock.expect(builder.setCancelable(false)).andReturn(builder);
      EasyMock.expect(builder.setView(R.layout.loading_progress_view)).andReturn(builder);
      EasyMock.expect(builder.create()).andReturn(mockDialog);

      EasyMock.expectLastCall();
      mockDialog.show();

      PowerMock.replay(builder, AlertDialog.Builder.class);
      AlertDialog dialog = DialogFactory.showDialog(context, DialogType.PROGRESS, TITLE, MESSAGE,
          android.R.string.ok, listener);
      PowerMock.verify(builder, AlertDialog.Builder.class);

      assertEquals(dialog, mockDialog);

    } catch (Exception exception) {
      fail(exception.getMessage());
    }
  }
}
