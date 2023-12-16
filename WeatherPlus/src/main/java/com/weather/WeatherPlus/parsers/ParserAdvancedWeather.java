package com.weather.WeatherPlus.parsers;

import com.weather.WeatherPlus.Coordinates.Coordinates;
import com.weather.WeatherPlus.getters.GetterCityFromBD;
import com.weather.WeatherPlus.getters.GetterCoordinates;
import com.weather.WeatherPlus.model.UserRepository;
import com.weather.WeatherPlus.network.HttpClient;
import com.weather.WeatherPlus.units.PRESSURE;
import com.weather.WeatherPlus.units.StoreUnits;
import com.weather.WeatherPlus.units.TEMPERATURE;
import com.weather.WeatherPlus.units.WIND_SPEED;
import org.json.JSONObject;
import java.io.IOException;

import static com.weather.WeatherPlus.Constants.Constants.*;
import static java.lang.Math.round;

public class ParserAdvancedWeather {

    public String parse(StoreUnits storeUnits, UserRepository userRepository, Long chatId) throws IOException {
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
        double feels_like = jsonObject.getJSONObject("main").getDouble("feels_like");
        double temp_min = jsonObject.getJSONObject("main").getDouble("temp_min");
        double temp_max = jsonObject.getJSONObject("main").getDouble("temp_max");
        double humidity = jsonObject.getJSONObject("main").getDouble("humidity");
        double windSpeed = jsonObject.getJSONObject("wind").getDouble("speed");
        double pressure = jsonObject.getJSONObject("main").getDouble("pressure");
        String precipitation = jsonObject.getJSONArray("weather").getJSONObject(0).getString("main");

        double temp;
        double temp_fl;
        double t_min;
        double t_max;
        double press;
        double wind;


        if(storeUnits.getTemperature().equals(TEMPERATURE.K)) {
            temp = round(temperature + 273);
            temp_fl = round(feels_like + 273);
            t_min = round(temp_min + 273);
            t_max = round(temp_max + 273);
        } else {
            temp = temperature;
            temp_fl = feels_like;
            t_min = temp_min;
            t_max = temp_max;
        }

        if(storeUnits.getWindSpeed().equals(WIND_SPEED.KMH)) {
            wind = round(windSpeed * 3.6);
        } else {
            wind = windSpeed;
        }

        if(storeUnits.getPressure().equals(PRESSURE.MMHG)) {
            press = round(pressure * 0.7500616827);
        } else {
            press = pressure;
        }


        return  "WEATHER IN " + coordinates.getCityName() +
                ",\n Temperature: " + temp + " " + storeUnits.getTemperature() +
                ",\n Feels like: " + temp_fl + " " + storeUnits.getTemperature() +
                ",\n Wind speed: " + wind + " " + storeUnits.getWindSpeed() +
                ",\n Pressure: " + press + " " + storeUnits.getPressure() +
                ",\n Humidity: " + humidity + "% " +
                ",\n Min temperature: " + t_min + " " + storeUnits.getTemperature() +
                ",\n Max temperature: " + t_max + " " + storeUnits.getTemperature() +
                ",\n Precipitation: " + precipitation;
    }
}
