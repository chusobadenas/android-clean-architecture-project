package com.chusobadenas.boilerplatecleanarchitecture.data.repository.remote;

import com.chusobadenas.boilerplatecleanarchitecture.data.entity.UserEntity;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * APIService for retrieving data from the network using Retrofit 2
 */
public interface APIService {

    String API_BASE_URL = "http://www.android10.org/";
    String API_VERSION = "/myapi";
    String EXTENSION = ".json";
    String USER_ID = "userId";

    /**
     * Retrieves an {@link rx.Observable} which will emit a List of {@link UserEntity}.
     */
    @GET(API_VERSION + "/users" + EXTENSION)
    Observable<List<UserEntity>> userEntityList();

    /**
     * Retrieves an {@link rx.Observable} which will emit a {@link UserEntity}.
     *
     * @param userId The user id used to get user data.
     */
    @GET(API_VERSION + "/user_{" + USER_ID + "}" + EXTENSION)
    Observable<UserEntity> userEntityById(@Path(USER_ID) int userId);

    /********
     * Helper class that sets up a new services
     *******/
    class Creator {

        private static OkHttpClient createHttpClient() {
            return new OkHttpClient.Builder()
                    .readTimeout(10000, TimeUnit.MILLISECONDS)
                    .connectTimeout(15000, TimeUnit.MILLISECONDS)
                    .build();
        }

        public static APIService newAPIService() {
            Gson gson = new GsonBuilder()
                    .setDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'")
                    .create();
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(API_BASE_URL)
                    .client(Creator.createHttpClient())
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .build();
            return retrofit.create(APIService.class);
        }
    }
}
