package com.chusobadenas.boilerplatecleanarchitecture.common.exception;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class NetworkConnectionExceptionTest {

    private static final String ERROR_MSG = "This is an error";

    @Test
    public void testDefaultCreation() {
        NetworkConnectionException exception = new NetworkConnectionException();
        assertNotNull(exception);
    }

    @Test
    public void testMessageCreation() {
        NetworkConnectionException exception = new NetworkConnectionException(ERROR_MSG);
        assertNotNull(exception);
        assertEquals(exception.getMessage(), ERROR_MSG);
    }

    @Test
    public void testThrowableCreation() {
        Throwable throwable = new Throwable(ERROR_MSG);
        NetworkConnectionException exception = new NetworkConnectionException(throwable);
        assertNotNull(exception);
        assertEquals(exception.getCause(), throwable);
    }

    @Test
    public void testThrowableAndMessageCreation() {
        Throwable throwable = new Throwable();
        NetworkConnectionException exception = new NetworkConnectionException(ERROR_MSG, throwable);
        assertNotNull(exception);
        assertEquals(exception.getMessage(), ERROR_MSG);
        assertEquals(exception.getCause(), throwable);
    }
}
