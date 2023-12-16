package com.weather.WeatherPlus.Command;

import com.weather.WeatherPlus.model.User;
import com.weather.WeatherPlus.model.UserRepository;
import org.telegram.telegrambots.meta.api.objects.Message;

public class RegisterUser {
    public void registerUser(Message msg, UserRepository userRepository) {
        if(userRepository.findById(msg.getChatId()).isEmpty()) {
            var chatId = msg.getChatId();
            var chat = msg.getChat();

            User user = new User();
            user.setChatId(chatId);
            user.setUserName(chat.getUserName());

            userRepository.save(user);
        }
    }
}
