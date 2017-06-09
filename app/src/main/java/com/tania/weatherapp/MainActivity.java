package com.tania.weatherapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.tania.weatherapp.api.IWeatherApi;
import com.tania.weatherapp.api.dto.Forecast;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    @Inject
    IWeatherApi weatherApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        WeatherApp.getAppComponent().inject(this);

        getForecast();
    }

    public void getForecast() {
        weatherApi.forecast("zmw:00000.8.33345").enqueue(new Callback<Forecast>() {
            @Override
            public void onResponse(Call<Forecast> call, Response<Forecast> response) {
                if (response.body() == null) return;

                Forecast forecast = response.body();
                Log.i("forecast", forecast.toString());
            }

            @Override
            public void onFailure(Call<Forecast> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Server connection error. Try again later.", Toast.LENGTH_SHORT).show();
                Log.e(getClass().getSimpleName(), t.getMessage());
            }
        });
    }
}
