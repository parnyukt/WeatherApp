package com.tania.weatherapp.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.tania.weatherapp.R;
import com.tania.weatherapp.api.dto.Forecast;

public class DetailsActivity extends AppCompatActivity {

    public static final String ARG_FORECAST = "forecast";

    public static void start(Context context, Forecast forecast) {
        if (context != null && forecast != null) {
            Intent intent = new Intent(context, DetailsActivity.class);
            intent.putExtra(ARG_FORECAST, forecast);
            context.startActivity(intent);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
    }
}
