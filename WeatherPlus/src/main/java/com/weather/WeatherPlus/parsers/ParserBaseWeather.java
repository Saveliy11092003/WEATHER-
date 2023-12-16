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
import static com.weather.WeatherPlus.Constants.Constants.*;

import java.io.IOException;

import static java.lang.Math.round;


public class ParserBaseWeather {

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
        double windSpeed = jsonObject.getJSONObject("wind").getDouble("speed");
        double pressure = jsonObject.getJSONObject("main").getDouble("pressure");


        double temp;
        double press;
        double wind;


        if(storeUnits.getTemperature().equals(TEMPERATURE.K)) {
            temp = round(temperature + 273);
        } else {
            temp = temperature;
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
                ",\n Wind speed: " + wind + " " + storeUnits.getWindSpeed() +
                ",\n Pressure: " + press + " " + storeUnits.getPressure();
    }

}
