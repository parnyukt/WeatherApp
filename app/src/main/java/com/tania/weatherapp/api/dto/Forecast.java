package com.tania.weatherapp.api.dto;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class Forecast implements Serializable { //todo: Parselable??
    RootForecast forecast;

    public ForecastDay getTodayForecast() {
        return forecast.simpleForecast.forecastDayList.get(0);
    }

    public List<ForecastDay> getForecastList(int daysCount) {
        return forecast.simpleForecast.forecastDayList.subList(0, daysCount);
    }

    public class RootForecast implements Serializable {
        @SerializedName("simpleforecast")
        SimpleForecast simpleForecast;
    }

    public class SimpleForecast implements Serializable {
        @SerializedName("forecastday")
        List<ForecastDay> forecastDayList;
    }
}
