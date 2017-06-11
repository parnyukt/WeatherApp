package com.tania.weatherapp.viewmodel;

import com.tania.weatherapp.api.dto.Forecast;
import com.tania.weatherapp.api.dto.ForecastDay;

import java.util.List;

public class DetailsForecastViewModel extends BaseViewModel {
    public static final int DAYS_COUNT = 5;

    public String city;
    public List<ForecastDay> forecastList;

    public static DetailsForecastViewModel from(Forecast forecastModel, String city) {
        DetailsForecastViewModel vm = new DetailsForecastViewModel();

        vm.city = city;
        vm.forecastList = forecastModel.getForecastList(DAYS_COUNT);
        return vm;
    }
}
