package com.weather.WeatherPlus.checkers;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.IOException;

import static com.weather.WeatherPlus.Constants.Constants.*;


@Slf4j
@Component
public class CityNameChecker {

    public boolean isCity(String cityName) throws RuntimeException{
        log.info("cityName " + cityName);
        boolean aswer = true;

        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("https://graphhopper.com/api/1/geocode?q=" + cityName + "&key=" + API_KEY1 )
                .get()
                .build();

        JsonArray hits;

        Response response = null;
        try {
            response = client.newCall(request).execute();
            JsonObject jobj1 = new Gson().fromJson(response.body().string(), JsonObject.class);
            hits = jobj1.getAsJsonArray(HITS);
        } catch (IOException e) {
            aswer = false;
            throw new RuntimeException(e);
        }



        JsonElement element = hits.get(0);
        JsonObject object = (JsonObject)element;


        return aswer;

    }




}
