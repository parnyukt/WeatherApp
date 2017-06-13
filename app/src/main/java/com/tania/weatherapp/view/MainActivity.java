package com.tania.weatherapp.view;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.databinding.DataBindingUtil;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
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

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private static final int PERMISSIONS_REQUEST_FINE_LOCATION = 0;

    @Inject
    IWeatherApi weatherApi;
    String[] cities = {"Current location", "Kiev", "Kharkiv", "Australia/Sydney"};
    LocationManager locationManager;
    private ActivityMainBinding binding;
    private Spinner citySpinner;
    private Forecast currentForecast;
    private String currentCity;
    LocationListener locationListener = new LocationListener() {
        public void onLocationChanged(Location location) {
            currentCity = getCityByLocation(location);
            getForecast(currentCity);
        }

        public void onStatusChanged(String provider, int status, Bundle extras) {
        }

        public void onProviderEnabled(String provider) {
        }

        public void onProviderDisabled(String provider) {
            startActivity(new Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS));
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        setCitySpinnerData();

        WeatherApp.getAppComponent().inject(this);

        locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
    }

    public void requestLocation() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    PERMISSIONS_REQUEST_FINE_LOCATION);
            return;
        }
        locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, locationListener);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case PERMISSIONS_REQUEST_FINE_LOCATION: {
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                } else {
                    citySpinner.setSelection(1);
                }
                return;
            }
        }
    }

    public String getCityByLocation(Location location) {
        Geocoder gcd = new Geocoder(this, Locale.getDefault());
        List<Address> addresses = null;
        try {
            addresses = gcd.getFromLocation(location.getLatitude(), location.getLongitude(), 1);
            if (addresses.size() > 0)
                return addresses.get(0).getLocality();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void setCitySpinnerData() {
        citySpinner = binding.spinner;

        ArrayAdapter adapter = new ArrayAdapter(
                getApplicationContext(), android.R.layout.simple_list_item_1, cities);
        citySpinner.setAdapter(adapter);

        citySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) {
                    // get city by location
                    requestLocation();
                } else {
                    currentCity = citySpinner.getItemAtPosition(position).toString();
                    getForecast(currentCity);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                citySpinner.setSelection(0);
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
