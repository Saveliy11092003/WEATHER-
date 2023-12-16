package com.weather.WeatherPlus.TgInterfaceParts;

import com.weather.WeatherPlus.Creaters.CreaterMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;

public class CreaterPresBottom {
    public SendMessage createPres(Long chatId){
        CreaterMessage createrMessge = new CreaterMessage();
        SendMessage message = createrMessge.createMessage(chatId, "Select speed wind");

        MakeInlineKeyboardMarkup maker = new MakeInlineKeyboardMarkup();
        InlineKeyboardMarkup markup = maker.makeInlineKeyboardMarkup("mmHg", "MM_HG_BUTTON", "hPa", "H_PA_BUTTON");

        message.setReplyMarkup(markup);

        return message;
    }
}
