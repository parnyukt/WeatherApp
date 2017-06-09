package com.tania.weatherapp.api.dto;

import com.google.gson.annotations.SerializedName;

public class ForecastDay {
    Temperature high;
    Temperature low;
    String conditions;
    String icon;
    String title;
    String fcttext;
    String fcttext_metric;
    ForecastDate date;

    public class ForecastDate {
        String epoch;
        @SerializedName("monthname")
        String monthName;
        @SerializedName("monthname_short")
        String monthNameShort;
        @SerializedName("weekday_short")
        String weekdayShort;
        String weekday;
    }

    public class Temperature {
        String fahrenheit;
        String celsius;
    }
}


