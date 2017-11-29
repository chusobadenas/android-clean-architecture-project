package com.chusobadenas.boilerplatecleanarchitecture.data.repository.remote;

import com.chusobadenas.boilerplatecleanarchitecture.BuildConfig;
import com.chusobadenas.boilerplatecleanarchitecture.data.entity.UserEntity;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * APIService for retrieving data from the network using Retrofit 2
 */
public interface APIService {

  int CONNECT_TIMEOUT = 15000;
  int READ_TIMEOUT = 20000;
  int WRITE_TIMEOUT = 20000;

  String API_BASE_URL = "https://raw.githubusercontent.com/";
  String USER_ID = "userId";

  /**
   * Retrieves an {@link Observable} which will emit a List of {@link UserEntity}.
   */
  @GET("/android10/Sample-Data/master/Android-CleanArchitecture/users.json")
  Observable<List<UserEntity>> userEntityList();

  /**
   * Retrieves an {@link Observable} which will emit a {@link UserEntity}.
   *
   * @param userId The user id used to get user data.
   */
  @GET("/android10/Sample-Data/master/Android-CleanArchitecture/user_{" + USER_ID + "}.json")
  Observable<UserEntity> userEntityById(@Path(USER_ID) int userId);

  /********
   * Helper class that sets up a new services
   *******/
  class Creator {

    private static OkHttpClient createHttpClient() {
      OkHttpClient.Builder clientBuilder = new OkHttpClient.Builder();

      // Enable logging
      if (BuildConfig.DEBUG) {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BASIC);
        clientBuilder.addInterceptor(interceptor);
      }

      return clientBuilder
          .connectTimeout(CONNECT_TIMEOUT, TimeUnit.MILLISECONDS)
          .readTimeout(READ_TIMEOUT, TimeUnit.MILLISECONDS)
          .writeTimeout(WRITE_TIMEOUT, TimeUnit.MILLISECONDS)
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
          .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
          .build();
      return retrofit.create(APIService.class);
    }
  }
}
