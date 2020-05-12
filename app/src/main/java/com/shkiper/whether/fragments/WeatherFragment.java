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

        weather = WeatherRepository.getInstance(getActivity()).get(0);

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
        temperatura.setText(weather.getCurrentTemp() + "°C");
        city = (TextView) v.findViewById(R.id.txt_city);
        city.setText(weather.getCity());
        description = (TextView) v.findViewById(R.id.txt_description);
        description.setText(weather.getDescription());

        return inflater.inflate(R.layout.fragment_weather, container, false);
    }

//    public class FetchWeatherTask extends AsyncTask<Void,Void, List<Weather>>{
//
//        @Override
//        protected List<Weather> doInBackground(Void... voids) {
//            return new WeatherFetch().fetchWeather();
//        }
//
//        @Override
//        protected void onPostExecute(List<Weather> weathers) {
//            super.onPostExecute(weathers);
//            Items = weathers;
//            weather = Items.get(0);
//            Toast.makeText(getActivity(), weather.getCurrentTemp(), Toast.LENGTH_LONG).show();
//            Toast.makeText(getActivity(), weather.getCity(), Toast.LENGTH_LONG).show();
//
//        }
//    }

}
