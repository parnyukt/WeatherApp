package com.tania.weatherapp.api.dto;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class ForecastDay implements Serializable {
    public Temperature high;
    public Temperature low;
    public String conditions;
    public String icon;
    public String title;
    public String fcttext;
    public String fcttext_metric;
    public ForecastDate date;

    public class ForecastDate implements Serializable {
        public String epoch;
        @SerializedName("monthname")
        public String monthName;
        @SerializedName("monthname_short")
        public String monthNameShort;
        @SerializedName("weekday_short")
        public String weekdayShort;
        public String weekday;
    }

    public class Temperature implements Serializable {
        public String fahrenheit;
        public String celsius;
    }
}


