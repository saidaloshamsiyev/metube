package org.example.metube;


import lombok.SneakyThrows;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


@Component
public class MyBot extends TelegramLongPollingBot {

    @Autowired
    public  YouTubeDownloaderBot bot ;
    public MyBot() {
        super("7716409596:AAFzFhIbMs_8JFpRNMXuHQL5OFg6ZQBN0Hs");
    }

    ExecutorService executorService = Executors.newFixedThreadPool(50);
/*s*/

    @SneakyThrows
    @Override
    public void onUpdateReceived(Update update) {
        executorService.execute(()->{
            bot.onUpdate(update);
        });
    }

    @Override
    public String getBotUsername() {
        return "MetubeMediaBot";
    }
}
