package org.example.metube;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;


@Component
public class YouTubeDownloaderBot extends MyBot {

    @Autowired
    private  S3Uploader s3Uploader ;


    public void onUpdate(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            String messageText = update.getMessage().getText();
            Long chatId = update.getMessage().getChatId();

            if (messageText.equals("/start")) {
                sendMessage(chatId, "Salom! Yuklamoqchi bo'lgan videoni URL-ni kiriting.");
                return;
            }

            try {
                String uploadedUrl = s3Uploader.uploadVideoToS3(messageText);
                sendMessage(chatId, "Video yuklandi: " + uploadedUrl);
            } catch (Exception e) {
                sendMessage(chatId, "Xato: " + e.getMessage());
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
