package com.shkiper.whether.fragments;

import android.os.AsyncTask;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.shkiper.whether.R;
import com.shkiper.whether.models.Weather;
import com.shkiper.whether.network.WeatherFetch;

import java.util.List;


public class WeatherFragment extends Fragment {

    private static final String ARG_PARAM = "param";

    private String mParam;

    public WeatherFragment() {
        // Required empty public constructor
    }

    public static WeatherFragment newInstance() {
        WeatherFragment fragment = new WeatherFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_weather, container, false);
    }

    private class FetchWeatherTask extends AsyncTask<Void,Void, List<Weather>>{

        @Override
        protected List<Weather> doInBackground(Void... voids) {
            return new WeatherFetch().fetchWeather();
        }
    }

}
