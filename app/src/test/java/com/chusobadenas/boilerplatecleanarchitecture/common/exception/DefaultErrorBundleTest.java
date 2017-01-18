package com.chusobadenas.boilerplatecleanarchitecture.common.exception;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class DefaultErrorBundleTest {

    @Test
    public void testCreationDefaultMessage() {
        DefaultErrorBundle defaultErrorBundle = new DefaultErrorBundle(null);

        assertNull(defaultErrorBundle.getException());
        assertEquals(defaultErrorBundle.getErrorMessage(), "Unknown error");
    }

    @Test
    public void testCreationCustomMessage() {
        Exception exception = new Exception("This is an error");
        DefaultErrorBundle defaultErrorBundle = new DefaultErrorBundle(exception);

        assertEquals(defaultErrorBundle.getException(), exception);
        assertEquals(defaultErrorBundle.getErrorMessage(), "This is an error");
    }
}
