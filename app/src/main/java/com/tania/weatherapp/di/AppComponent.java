package com.tania.weatherapp.di;

import com.tania.weatherapp.view.MainActivity;
import com.tania.weatherapp.viewmodel.BaseViewModel;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {ApiModule.class, AppModule.class})
public interface AppComponent {
    void inject(MainActivity activity);

    void inject(BaseViewModel baseViewModel);
}
