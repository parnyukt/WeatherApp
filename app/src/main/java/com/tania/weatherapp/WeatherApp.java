package com.tania.weatherapp;

import android.app.Application;

import com.facebook.stetho.Stetho;
import com.squareup.leakcanary.LeakCanary;
import com.tania.weatherapp.di.ApiModule;
import com.tania.weatherapp.di.AppComponent;
import com.tania.weatherapp.di.AppModule;
import com.tania.weatherapp.di.DaggerAppComponent;

import javax.inject.Inject;

import okhttp3.OkHttpClient;


public class WeatherApp extends Application {
    private static WeatherApp sInstance;
    private static AppComponent appComponent;

    @Inject
    OkHttpClient okHttpClient;

    public WeatherApp() {
        super();
        sInstance = this;
    }

    public static WeatherApp get() {
        return sInstance;
    }

    public static AppComponent getAppComponent() {
        if (appComponent == null) {
            appComponent = DaggerAppComponent.builder()
                    .apiModule(new ApiModule())
                    .appModule(new AppModule())
                    .build();
        }
        return appComponent;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        appComponent = getAppComponent();

        if (LeakCanary.isInAnalyzerProcess(this)) {
            // This process is dedicated to LeakCanary for heap analysis.
            // You should not init your app in this process.
            return;
        }
        LeakCanary.install(this);

        Stetho.initializeWithDefaults(this);

    }

}
