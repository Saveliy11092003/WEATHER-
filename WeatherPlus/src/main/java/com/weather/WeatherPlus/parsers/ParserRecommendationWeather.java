package com.weather.WeatherPlus.parsers;


import com.weather.WeatherPlus.Coordinates.Coordinates;
import com.weather.WeatherPlus.getters.GetterCityFromBD;
import com.weather.WeatherPlus.getters.GetterCoordinates;
import com.weather.WeatherPlus.model.UserRepository;
import com.weather.WeatherPlus.network.HttpClient;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.json.JSONObject;
import static com.weather.WeatherPlus.Constants.Constants.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class ParserRecommendationWeather {

    public RecommendationInfoDto parseRecommendationInfo(UserRepository userRepository, Long chatId) throws IOException {

        String city = null;

        GetterCityFromBD getterCity = new GetterCityFromBD();
        city = getterCity.getCity(userRepository,chatId);

        Coordinates coordinates = new Coordinates(0.0,0.0, "Novosibirsk");

        GetterCoordinates getterCoordinates = new GetterCoordinates();
        getterCoordinates.getCoordinates(coordinates, city);

        System.out.println("lat " + coordinates.getLat());
        System.out.println("lng " + coordinates.getLng());
        System.out.println("name " + coordinates.getCityName());

        String output = HttpClient.getUrlContent("https://api.openweathermap.org/data/2.5/weather?lat=" + coordinates.getLat() + "&lon=" + coordinates.getLng() + "&appid=" + API_KEY2 + "&units=metric");

        JSONObject jsonObject = new JSONObject(output);
        double temperature = jsonObject.getJSONObject("main").getDouble("temp");
        String precipitation = jsonObject.getJSONArray("weather").getJSONObject(0).getString("main");

        RecommendationInfoDto dto = new RecommendationInfoDto(temperature, precipitation);
        return dto;
    }

    @Getter
    @AllArgsConstructor
    public static class RecommendationInfoDto {
        private double temperature;
        private String precipitation;
    }

    public static String getUrlContent(String urlAdress) throws IOException {
        StringBuffer content = new StringBuffer();
        URL url = new URL(urlAdress);
        URLConnection urlConnection = url.openConnection();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader((urlConnection.getInputStream())));
        String line;
        while((line = bufferedReader.readLine()) != null){
            content.append(line + "\n");
        }
        bufferedReader.close();
        return content.toString();
    }

}

