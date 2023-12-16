package com.weather.WeatherPlus.getters;

import com.weather.WeatherPlus.model.User;
import com.weather.WeatherPlus.model.UserRepository;

import java.util.Optional;

public class GetterCityFromBD {
    public String getCity(UserRepository userRepository, Long chatId) {
        String city = "Novosibirsk";
        Optional<User> user = userRepository.findById(chatId);
        if(user.isPresent()) {
            User foundUser = user.get();
            city = foundUser.getCity();
        }
        return city;
    }
}
