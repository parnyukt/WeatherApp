package com.tania.weatherapp.api.dto;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Forecast {
    RootForecast forecast;

    public class RootForecast {
        @SerializedName("simpleforecast")
        SimpleForecast simpleForecast;
    }

    public class SimpleForecast {
        @SerializedName("forecastday")
        List<ForecastDay> forecastDay;
    }
}
