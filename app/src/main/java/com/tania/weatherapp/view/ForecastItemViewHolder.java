package com.tania.weatherapp.view;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.tania.weatherapp.databinding.ViewForecastItemBinding;
import com.tania.weatherapp.viewmodel.ForecastItemViewModel;

public class ForecastItemViewHolder extends RecyclerView.ViewHolder {

    private final ViewForecastItemBinding mBinding;

    public ForecastItemViewHolder(View itemView) {
        super(itemView);

        mBinding = ViewForecastItemBinding.bind(itemView);
    }

    public void bind(ForecastItemViewModel collectionViewModel) {
        mBinding.setVm(collectionViewModel);
    }
}
