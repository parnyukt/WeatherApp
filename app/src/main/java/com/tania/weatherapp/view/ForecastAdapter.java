package com.tania.weatherapp.view;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tania.weatherapp.R;
import com.tania.weatherapp.api.dto.ForecastDay;

import java.util.ArrayList;
import java.util.List;

public class ForecastAdapter extends RecyclerView.Adapter<ForecastItemViewHolder> {

    private final ArrayList<ForecastDay> forecastDays = new ArrayList<>();

    public ForecastAdapter(List<ForecastDay> data) {
        forecastDays.addAll(data);
    }

    @Override
    public ForecastItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.view_forecast_item, parent, false);

        return new ForecastItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ForecastItemViewHolder holder, int position) {
        holder.bind(forecastDays.get(position));
    }

    @Override
    public int getItemCount() {
        return forecastDays.size();
    }

}
