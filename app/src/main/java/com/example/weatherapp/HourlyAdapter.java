package com.example.weatherapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.weatherapp.HourlyWeather;

import java.util.List;

public class HourlyAdapter extends RecyclerView.Adapter<HourlyAdapter.HourlyViewHolder> {

    private List<HourlyWeather> hourlyWeatherList;

    public void setData(List<HourlyWeather> hourlyWeatherList) {
        this.hourlyWeatherList = hourlyWeatherList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public HourlyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_hourly_weather, parent, false);
        return new HourlyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HourlyViewHolder holder, int position) {
        HourlyWeather hourlyWeather = hourlyWeatherList.get(position);
        holder.bind(hourlyWeather);
    }

    @Override
    public int getItemCount() {
        return hourlyWeatherList != null ? hourlyWeatherList.size() : 0;
    }

    static class HourlyViewHolder extends RecyclerView.ViewHolder {
        private TextView timeTextView;
        private TextView temperatureTextView;
        private TextView conditionTextView;

        public HourlyViewHolder(@NonNull View itemView) {
            super(itemView);
            timeTextView = itemView.findViewById(R.id.timeTextView);
            temperatureTextView = itemView.findViewById(R.id.temperatureTextView);
            conditionTextView = itemView.findViewById(R.id.conditionTextView);
        }

        public void bind(HourlyWeather hourlyWeather) {
            timeTextView.setText("Time: "+hourlyWeather.getTime());
            temperatureTextView.setText("Temperature: "+String.valueOf(hourlyWeather.getTemperature())+"°C");
            conditionTextView.setText("Condition: "+hourlyWeather.getCondition());
        }
    }
}
