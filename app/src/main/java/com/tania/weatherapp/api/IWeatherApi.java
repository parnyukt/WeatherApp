package com.tania.weatherapp.api;

import com.tania.weatherapp.api.dto.Forecast;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface IWeatherApi {
    @GET("forecast10day/q/{query}.json")
    Call<Forecast> forecast(@Path("query") String query);
}




