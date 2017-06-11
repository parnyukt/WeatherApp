package com.tania.weatherapp.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.tania.weatherapp.R;
import com.tania.weatherapp.api.dto.Forecast;
import com.tania.weatherapp.api.dto.ForecastDay;

import java.util.List;

public class DetailsActivity extends AppCompatActivity {

    public static final String ARG_FORECAST = "forecast";
    public static final int DAYS_COUNT = 5;

    private ForecastAdapter adapter;
    private RecyclerView recyclerView;
    private LinearLayoutManager layoutManager;

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

        Forecast forecast = (Forecast) getIntent().getSerializableExtra(ARG_FORECAST);
        List<ForecastDay> data = forecast.getForecastList(DAYS_COUNT);

        adapter = new ForecastAdapter(data);
        recyclerView = (RecyclerView) findViewById(R.id.forecastsList);
        recyclerView.addItemDecoration(new RecyclerView.ItemDecoration() {
        });
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }
}
