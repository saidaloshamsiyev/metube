package org.example.metube;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

@SpringBootApplication
public class MetubeBotApplication {

    public static void main(String[] args) {
        SpringApplication.run(MetubeBotApplication.class, args);
        try {
            TelegramBotsApi telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);
            telegramBotsApi.registerBot(new YouTubeDownloaderBot());
            System.out.println("Bot Start");
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }

}
