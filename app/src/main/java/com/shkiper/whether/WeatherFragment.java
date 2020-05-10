package com.shkiper.whether;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class WeatherFragment extends Fragment {

    private static final String ARG_PARAM = "param";
    private final String CITY = "dhaka,bd";


    private String mParam;

    public WeatherFragment() {
        // Required empty public constructor
    }

    public static WeatherFragment newInstance() {
        WeatherFragment fragment = new WeatherFragment();
//        Bundle args = new Bundle();
//        args.putString(ARG_PARAM, param1);
//        fragment.setArguments(args);
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



}
