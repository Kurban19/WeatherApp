package com.shkiper.whether;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.AsyncTask;
import android.os.Bundle;

import com.shkiper.whether.fragments.WeatherFragment;
import com.shkiper.whether.models.Weather;
import com.shkiper.whether.network.WeatherFetch;
import com.shkiper.whether.repositories.WeatherRepository;

import java.util.List;

public class WeatherActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);


        new FetchWeatherTask().execute();

    }


    public class FetchWeatherTask extends AsyncTask<Void, Void, List<Weather>>{

        @Override
        protected List<Weather> doInBackground(Void... voids) {
            return new WeatherFetch().fetchWeather();
        }

        @Override
        protected void onPostExecute(List<Weather> weathers) {
            super.onPostExecute(weathers);
            WeatherRepository.getInstance().setList(weathers);


            FragmentManager fm = getSupportFragmentManager();
            Fragment fragment = fm.findFragmentById(R.id.action_container);

            if (fragment == null) {
                fragment = WeatherFragment.newInstance();
                fm.beginTransaction().add(R.id.action_container, fragment).commit();
            }
        }
    }
}
