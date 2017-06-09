package com.tania.weatherapp.di;

import android.content.Context;

import com.facebook.stetho.okhttp3.StethoInterceptor;
import com.tania.weatherapp.R;
import com.tania.weatherapp.api.IWeatherApi;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class ApiModule {

    private static final String SERVICE_BASE_URL = "http://api.wunderground.com/api/";

    @Provides
    @Singleton
    public OkHttpClient.Builder okHttpClient() {

        //todo: Stetho only for Debug
        return new OkHttpClient.Builder().addNetworkInterceptor(new StethoInterceptor());
    }

    @Provides
    @Singleton
    public Retrofit retrofit(OkHttpClient.Builder okHttpClientBuilder, Context context) {

        String baseUrl = SERVICE_BASE_URL + context.getString(R.string.wunderground_api) + "/";

        return new Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(okHttpClientBuilder.build())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    @Provides
    @Singleton
    public IWeatherApi restApi(Retrofit retrofit) {
        return retrofit.create(IWeatherApi.class);
    }
}
