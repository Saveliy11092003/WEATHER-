package com.weather.WeatherPlus.Command;

import com.weather.WeatherPlus.model.User;
import com.weather.WeatherPlus.model.UserRepository;
import org.telegram.telegrambots.meta.api.objects.Chat;

public class ChangerUserData {
    public void changeUserData(Long chatId, Chat chat, String city, UserRepository userRepository){
        User user = new User();
        user.setChatId(chatId);
        user.setUserName(chat.getUserName());
        user.setCity(city);
        userRepository.save(user);
    }
}
