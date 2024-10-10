package org.example.metube;


import lombok.SneakyThrows;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class MyBot extends TelegramLongPollingBot {

    public static YouTubeDownloaderBot bot =new YouTubeDownloaderBot();
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
