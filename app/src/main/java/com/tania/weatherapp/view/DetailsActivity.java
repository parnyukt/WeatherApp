package com.tania.weatherapp.view;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.tania.weatherapp.R;
import com.tania.weatherapp.api.dto.Forecast;
import com.tania.weatherapp.databinding.ActivityDetailsBinding;
import com.tania.weatherapp.viewmodel.DetailsForecastViewModel;

public class DetailsActivity extends AppCompatActivity {

    public static final String ARG_FORECAST = "forecast";
    public static final String ARG_CITY = "city";
    DetailsForecastViewModel viewModel;
    private ActivityDetailsBinding binding;
    private ForecastAdapter adapter;
    private RecyclerView recyclerView;
    private LinearLayoutManager layoutManager;

    public static void start(Context context, Forecast forecast, String city) {
        if (context != null && forecast != null) {
            Intent intent = new Intent(context, DetailsActivity.class);
            intent.putExtra(ARG_FORECAST, forecast);
            intent.putExtra(ARG_CITY, city);
            context.startActivity(intent);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_details);

        Forecast forecast = (Forecast) getIntent().getSerializableExtra(ARG_FORECAST);
        String city = getIntent().getStringExtra(ARG_CITY);
        viewModel = DetailsForecastViewModel.from(forecast, city);
        binding.setVm(viewModel);

        adapter = new ForecastAdapter(viewModel.forecastList);
        recyclerView = (RecyclerView) findViewById(R.id.forecastsList);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }
}
