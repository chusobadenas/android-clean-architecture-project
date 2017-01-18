package com.chusobadenas.boilerplatecleanarchitecture.common.exception;

import android.content.Context;

import com.chusobadenas.boilerplatecleanarchitecture.R;
import com.chusobadenas.boilerplatecleanarchitecture.presentation.model.UserNotFoundException;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.net.UnknownHostException;

import okhttp3.ResponseBody;
import retrofit2.Response;
import retrofit2.adapter.rxjava.HttpException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.when;

public class ErrorMessageFactoryTest {

    @Mock
    private Context mMockContext;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        when(mMockContext.getString(R.string.exception_message_no_connection)).thenReturn("No connection");
        when(mMockContext.getString(R.string.exception_message_user_not_found)).thenReturn("User not found");
    }

    @Test
    public void testCreationNetworkException() {
        NetworkConnectionException exception = new NetworkConnectionException();
        assertEquals(ErrorMessageFactory.create(mMockContext, exception), "No connection");
    }

    @Test
    public void testCreationUnknownHostException() {
        UnknownHostException exception = new UnknownHostException();
        assertEquals(ErrorMessageFactory.create(mMockContext, exception), "No connection");
    }

    @Test
    public void testCreationHttpException() {
        ResponseBody body = Mockito.mock(ResponseBody.class);
        Response response = Response.error(400, body);
        HttpException exception = new HttpException(response);

        assertNull(ErrorMessageFactory.create(mMockContext, exception));
    }

    @Test
    public void testCreationUserNotFoundException() {
        UserNotFoundException exception = new UserNotFoundException();
        assertEquals(ErrorMessageFactory.create(mMockContext, exception), "User not found");
    }
}
