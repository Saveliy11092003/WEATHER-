package com.weather.WeatherPlus.TgInterfaceParts;


import com.weather.WeatherPlus.Creaters.CreaterMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;

public class CreaterTempBottom {
    public SendMessage createTemp(Long chatId){
        CreaterMessage createrMessge = new CreaterMessage();
        SendMessage message = createrMessge.createMessage(chatId, "Select util of temperature:");

        MakeInlineKeyboardMarkup maker = new MakeInlineKeyboardMarkup();
        InlineKeyboardMarkup markup = maker.makeInlineKeyboardMarkup("C", "BUTTON_C", "K", "BUTTON_K");

        message.setReplyMarkup(markup);
        return message;
    }
}
