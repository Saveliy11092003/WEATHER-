package com.weather.WeatherPlus.Creaters;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;

public class CreaterMessage {
    public SendMessage createMessage(long chatId, String textToSend){
        SendMessage message = new SendMessage();
        message.setChatId(String.valueOf(chatId));
        message.setText(textToSend);
        return message;
    }

    public EditMessageText createMessage(Long chatId, String textToSend, long messageId){
        EditMessageText message = new EditMessageText();
        message.setChatId(String.valueOf(chatId));
        message.setText(textToSend);
        message.setMessageId((int) messageId);
        return message;
    }


}
