package com.weather.WeatherPlus.TgInterfaceParts;

import org.telegram.telegrambots.meta.api.objects.commands.BotCommand;

import java.util.ArrayList;
import java.util.List;

public class MakerMenu {
    public List<BotCommand> makeMenu(){
        List<BotCommand> listOfCommands = new ArrayList<>();

        listOfCommands.add(new BotCommand("/start", "get a welcome message"));
        listOfCommands.add(new BotCommand("/weather_base", "get a base weather"));
        listOfCommands.add(new BotCommand("/weather_advanced", "get a advanced weather"));
        listOfCommands.add(new BotCommand("/recommendation_of_clothes", "recomendation of clothers"));
        listOfCommands.add(new BotCommand("/change_utils", "change utils"));
        listOfCommands.add(new BotCommand("/help", "info how to use this bot"));

        return listOfCommands;
    }
}
