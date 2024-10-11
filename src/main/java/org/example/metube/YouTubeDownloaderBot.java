package org.example.metube;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;


@Component
public class YouTubeDownloaderBot extends TelegramLongPollingBot {

    public YouTubeDownloaderBot() {
        super("7716409596:AAFzFhIbMs_8JFpRNMXuHQL5OFg6ZQBN0Hs");
    }


    @Override
    public String getBotUsername() {
        return "MetubeMediaBot";
    }



    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            String messageText = update.getMessage().getText();
            Long chatId = update.getMessage().getChatId();

            if (messageText.equals("/start")) {
                sendMessage(chatId, "Salom! Yuklamoqchi bo'lgan videoni URL-ni kiriting.");
                return;
            }


        }

    }

    private void sendMessage(Long chatId, String text) {
        try {
            execute(new SendMessage(chatId.toString(), text));
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}
