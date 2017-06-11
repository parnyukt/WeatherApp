package com.tania.weatherapp.viewmodel;

import android.content.Context;
import android.databinding.BaseObservable;
import android.os.Bundle;

import com.tania.weatherapp.WeatherApp;
import com.tania.weatherapp.di.AppComponent;

public class BaseViewModel extends BaseObservable {

    public BaseViewModel() {
        inject(WeatherApp.getAppComponent());
    }

    protected void inject(AppComponent component) {
        component.inject(this);
    }

    protected Context getApplicationContext() {
        return WeatherApp.get();
    }

    protected String getString(int stringId) {
        return WeatherApp.get().getString(stringId);
    }

    public void onSaveInstanceState(Bundle outState) {
    }

    public void onRestoreInstanceState(Bundle savedInstanceState) {
    }
}