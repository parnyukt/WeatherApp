package com.tania.weatherapp.viewmodel;

import com.tania.weatherapp.api.dto.ForecastDay;

public class ForecastItemViewModel extends BaseViewModel {
    public String weekDay;
    public String iconUrl;
    public String conditions;
    public String highTemp;
    public String lowTemp;

    public static ForecastItemViewModel from(ForecastDay model) {
        ForecastItemViewModel vm = new ForecastItemViewModel();

        vm.weekDay = model.date.weekday;
        vm.iconUrl = model.icon_url;
        vm.conditions = model.conditions;
        vm.highTemp = model.high.celsius;
        vm.lowTemp = model.low.celsius;
        return vm;
    }
}
