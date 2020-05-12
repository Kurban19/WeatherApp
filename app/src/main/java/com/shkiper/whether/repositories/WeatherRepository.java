package com.shkiper.whether.repositories;

import android.content.Context;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.shkiper.whether.models.Weather;

import java.util.ArrayList;
import java.util.List;

public class WeatherRepository {


    private static WeatherRepository instances;
    List<Weather> list;


    private WeatherRepository(Context context){
        list = new ArrayList<>();
    }

    public static WeatherRepository getInstance(Context context){
        if(instances == null){
            instances = new WeatherRepository(context);
        }
        return instances;
    }

    public void setList(List<Weather> list){
        this.list = list;
    }

    public void add(Weather weather){
        list.add(weather);
    }

    public Weather get(int i){
        return list.get(i);
    }
}
