package com.shkiper.whether

import android.os.AsyncTask
import android.os.Bundle
import android.os.PersistableBundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.shkiper.whether.fragments.WeatherFragment
import com.shkiper.whether.models.Weather
import com.shkiper.whether.network.WeatherFetch
import com.shkiper.whether.repositories.WeatherRepository
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        setContentView(R.layout.activity_main)

        //FetchWeatherTask().execute()

    }

    inner class FetchWeatherTask : AsyncTask<Void?, Void?, List<Weather>>() {

        override fun onPreExecute() {
            super.onPreExecute()
            loader.visibility = View.VISIBLE
        }

        override fun doInBackground(vararg params: Void?): List<Weather> {
            return WeatherFetch().fetchWeather()
        }

        override fun onPostExecute(result: List<Weather>?) {
            super.onPostExecute(result)
            WeatherRepository.getInstance(this@MainActivity).setList(result)
            loader.visibility = View.GONE

            val fm = supportFragmentManager
            var fragment =
                fm.findFragmentById(R.id.action_container)

            if (fragment == null) {
                fragment = WeatherFragment.newInstance()
                fm.beginTransaction().add(R.id.action_container, fragment!!).commit()
            }
        }
    }
}