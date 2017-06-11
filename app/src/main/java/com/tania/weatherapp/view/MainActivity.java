package com.tania.weatherapp.view;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.tania.weatherapp.R;
import com.tania.weatherapp.WeatherApp;
import com.tania.weatherapp.api.IWeatherApi;
import com.tania.weatherapp.api.dto.Forecast;
import com.tania.weatherapp.databinding.ActivityMainBinding;
import com.tania.weatherapp.viewmodel.ForecastItemViewModel;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    @Inject
    IWeatherApi weatherApi;
    String[] cities = {"Kharkiv", "Kiev", "CA/San_Francisco", "Australia/Sydney"}; //todo fix
    private ActivityMainBinding binding;
    private Spinner citySpinner;
    private Forecast currentForecast;
    private String currentCity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        setCitySpinnerData();

        WeatherApp.getAppComponent().inject(this);
    }

    public void setCitySpinnerData() {
        citySpinner = binding.spinner;

        ArrayAdapter adapter = new ArrayAdapter(
                getApplicationContext(), android.R.layout.simple_list_item_1, cities);
        citySpinner.setAdapter(adapter);

        citySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                currentCity = citySpinner.getItemAtPosition(position).toString();
                getForecast(currentCity);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    public void getForecast(String city) {
        weatherApi.forecast(city).enqueue(new Callback<Forecast>() {
            @Override
            public void onResponse(Call<Forecast> call, Response<Forecast> response) {
                if (response.body() == null) return;

                currentForecast = response.body();
                binding.todayForecast.setVm(ForecastItemViewModel.from(currentForecast.getTodayForecast()));
            }

            @Override
            public void onFailure(Call<Forecast> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Server connection error. Try again later.", Toast.LENGTH_SHORT).show();
                Log.e(getClass().getSimpleName(), t.getMessage());
            }
        });
    }

    public void showDetails(View view) {
        DetailsActivity.start(this, currentForecast, currentCity);
    }
}
