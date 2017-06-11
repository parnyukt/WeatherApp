package com.tania.weatherapp.view;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.tania.weatherapp.api.dto.ForecastDay;
import com.tania.weatherapp.databinding.ViewForecastItemBinding;

public class ForecastItemViewHolder extends RecyclerView.ViewHolder {

    private final ViewForecastItemBinding mBinding;

    public ForecastItemViewHolder(View itemView) {
        super(itemView);

        mBinding = ViewForecastItemBinding.bind(itemView);
    }

    public void bind(ForecastDay model) {
        mBinding.setForecast(model);
    }
}
