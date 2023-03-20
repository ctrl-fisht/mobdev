package com.example.test;


import com.example.test.data.WeatherData;

import io.reactivex.Observable;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface Api {

    static String URL = "https://api.openweathermap.org/";

    @GET("data/2.5/weather")
    Observable<WeatherData> getWeatherDataByCity(@Query("q") String city,
                                             @Query("appid") String appId,
                                             @Query("units") String units,
                                             @Query("lang") String lang);



    class Instance {

        private static Retrofit getRetrofit() {

            OkHttpClient.Builder okHttpClientBuilder = new OkHttpClient.Builder();

            Retrofit.Builder retrofitBuilder = new Retrofit.Builder();
            retrofitBuilder.baseUrl(URL);
            retrofitBuilder.addConverterFactory(GsonConverterFactory.create());
            retrofitBuilder.addCallAdapterFactory(RxJava2CallAdapterFactory.create());

            retrofitBuilder.client(okHttpClientBuilder.build());

            return retrofitBuilder.build();

        }

        public static Api getApi() {
            return getRetrofit().create(Api.class);
        }
    }
}
