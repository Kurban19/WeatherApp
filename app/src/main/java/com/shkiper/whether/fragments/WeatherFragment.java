package com.shkiper.whether.fragments;

import android.annotation.SuppressLint;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import com.shkiper.whether.R;
import com.shkiper.whether.SearchActivity;
import com.shkiper.whether.models.Weather;
import com.shkiper.whether.repositories.WeatherRepository;


public class WeatherFragment extends Fragment {

    private Weather weather;


    public static WeatherFragment newInstance() {
        WeatherFragment fragment = new WeatherFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        weather = WeatherRepository.getInstance().get(0);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_weather, container, false);


        TextView temperatura = (TextView) v.findViewById(R.id.curr_temp);
        TextView city = (TextView) v.findViewById(R.id.txt_city);
        TextView description = (TextView) v.findViewById(R.id.txt_description);
        Button add_btn = (Button) v.findViewById(R.id.btn_add);

        add_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(SearchActivity.newIntent(getActivity()));
            }
        });


        int index = weather.getCurrentTemp().indexOf(".");
        String result = weather.getCurrentTemp().substring(0, index);// format temp from 13.4 to 13

        city.setText(weather.getCity());
        description.setText(weather.getDescription());
        temperatura.setText(result + "°C");


        return v;
    }
}
