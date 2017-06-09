package com.tania.weatherapp.di;

import android.content.Context;

import com.tania.weatherapp.WeatherApp;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {

    @Provides
    @Singleton
    public Context context() {
        return WeatherApp.get();
    }

}
