package com.shkiper.whether.network;

import android.net.Uri;

import com.shkiper.whether.common.Common;
import com.shkiper.whether.models.Weather;
import com.shkiper.whether.repositories.WeatherRepository;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class WeatherFetch {

    public List<Weather> fetchWeather() {
        String url = buildUrl();
        return downloadWeatherItem(url);
    }

    private static final Uri ENDPOINT = Uri
            .parse("https://api.openweathermap.org/data/2.5/weather?")
            .buildUpon()
            .appendQueryParameter("zip", "368009, ru")
            .appendQueryParameter("appid", Common.APP_ID)
            .appendQueryParameter("lang", "ru")
            .build();

    public byte[] getUrlBytes(String urlSpec) throws IOException {
        URL url = new URL(urlSpec);
        HttpURLConnection connection = (HttpURLConnection)url.openConnection();
        try {
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            InputStream in = connection.getInputStream();
            if (connection.getResponseCode() != HttpURLConnection.HTTP_OK) {
                throw new IOException(connection.getResponseMessage() +
                        ": with " +
                        urlSpec);
            }
            int bytesRead = 0;
            byte[] buffer = new byte[1024];
            while ((bytesRead = in.read(buffer)) > 0) {
                out.write(buffer, 0, bytesRead);
            }
            out.close();
            return out.toByteArray();
        } finally {
            connection.disconnect();
        }
    }

    private String getUrlString(String urlSpec) throws IOException {
        return new String(getUrlBytes(urlSpec));
    }

    private String buildUrl() {
        Uri.Builder uriBuilder = ENDPOINT.buildUpon();
        return uriBuilder.build().toString();
    }

    private List<Weather> downloadWeatherItem(String url) {
        List<Weather> items = new ArrayList<>();
        url = url.replace("%2C%20", ",");
        System.out.println(url);
        try {
            String jsonString = getUrlString(url);
            JSONObject jsonBody = new JSONObject(jsonString);
            parseItem(items, jsonBody);
        } catch (IOException | JSONException ioe) {
            ioe.printStackTrace();
        }
        return items;
    }

    private void parseItem(List<Weather> items, JSONObject jsonBody) throws IOException, JSONException {
        JSONObject main = jsonBody.getJSONObject("main");
        String temp = main.getString("temp");
        JSONObject weather = jsonBody.getJSONArray("weather").getJSONObject(0);
        String id = weather.getString("id");
        String description = weather.getString("main");
        String city = jsonBody.getString("name");

        Weather item = new Weather();
        item.setId(id);
        item.setCity(city);
        item.setCurrentTemp(temp);
        item.setDescription(description);
        items.add(item);
    }
}
