package com.shkiper.whether.fragments;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.shkiper.whether.R;
import com.shkiper.whether.models.Weather;
import com.shkiper.whether.network.WeatherFetch;
import com.shkiper.whether.repositories.WeatherRepository;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;


public class WeatherFragment extends Fragment {

    private static final String ARG_PARAM = "param";
    private List<Weather> Items = new ArrayList<>();
    private TextView temperatura;
    private TextView city;
    private TextView description;

    private Weather weather;


    public static WeatherFragment newInstance() {
        WeatherFragment fragment = new WeatherFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        weather = WeatherRepository.getInstance().get(0);


        Toast.makeText(getActivity(), weather.getCity(), Toast.LENGTH_LONG).show();

        //updateItems();
    }

//    private void updateItems(){
//        new FetchWeatherTask().execute();
//    }

    @SuppressLint("SetTextI18n")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_weather, container, false);

        temperatura = (TextView) v.findViewById(R.id.curr_temp);
        city = (TextView) v.findViewById(R.id.txt_city);
        description = (TextView) v.findViewById(R.id.txt_description);


        int index = weather.getCurrentTemp().indexOf(".");
        String result = weather.getCurrentTemp().substring(0,index);
        city.setText(weather.getCity());
        description.setText(weather.getDescription());
        temperatura.setText(result + "°C");

        return v;
    }

}
