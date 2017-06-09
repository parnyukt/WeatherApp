package com.tania.weatherapp.di;

import com.tania.weatherapp.MainActivity;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {ApiModule.class, AppModule.class})
public interface AppComponent {
    void inject(MainActivity activity);
}
