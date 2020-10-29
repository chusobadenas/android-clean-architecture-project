package com.chusobadenas.boilerplatecleanarchitecture.common.exception;

import android.content.Context;
import com.chusobadenas.boilerplatecleanarchitecture.R;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.when;

public class DefaultErrorBundleTest {

  @Mock
  private Context context;

  @Before
  public void setUp() {
    MockitoAnnotations.openMocks(this);
    when(context.getString(R.string.error_message_generic)).thenReturn("An error has occurred");
  }

  @Test
  public void testCreationNullMessage() {
    DefaultErrorBundle defaultErrorBundle = new DefaultErrorBundle(context, null, null);

    assertNull(defaultErrorBundle.getException());
    assertEquals(defaultErrorBundle.getErrorMessage(), "An error has occurred");
  }

  @Test
  public void testCreationCustomMessage() {
    Exception exception = new Exception("Error");
    DefaultErrorBundle defaultErrorBundle = new DefaultErrorBundle(context, exception, R.string
        .error_message_generic);

    assertEquals(defaultErrorBundle.getException(), exception);
    assertEquals(defaultErrorBundle.getErrorMessage(), "An error has occurred");
  }
}
