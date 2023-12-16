package com.weather.WeatherPlus.TgInterfaceParts;


import com.weather.WeatherPlus.Creaters.CreaterMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;

public class CreaterWindBottom {
    public SendMessage createTemp(Long chatId){
        CreaterMessage createrMessge = new CreaterMessage();
        SendMessage message = createrMessge.createMessage(chatId, "Select speed wind");

        MakeInlineKeyboardMarkup maker = new MakeInlineKeyboardMarkup();
        InlineKeyboardMarkup markup = maker.makeInlineKeyboardMarkup("m/s", "MS_BUTTON", "km/h", "KM_H_BUTTON");
        message.setReplyMarkup(markup);
        return message;
    }
}
