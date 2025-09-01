package org.example.log;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.logging.Logger;

public class telegramlog extends TelegramLongPollingBot {

    private static final Logger logger = Logger.getLogger(telegramlog.class.getName());

    @Override
    public void onUpdateReceived(Update update) {
        logger.info("Received message :" + update.toString());

        if (update.hasMessage() && update.getMessage().hasText() ){
            String messageTEXT = update.getMessage().getText();
            Long chatId = update.getMessage().getChatId();

            logger.info("message from "+ chatId + ":" + messageTEXT);

            processMessage(chatId , messageTEXT);

        }
    }

    private void processMessage(Long chatId, String text) {
        try {
            SendMessage message = new SendMessage();
            message.setChatId(String.valueOf(chatId));

            if (text.startsWith("/")) {
                message.setText("response to the command : " + text);
            } else {
                message.setText("you saied :" + text);
            }

            execute(message);
            logger.info("message sent to " + chatId);
        }catch (TelegramApiException e){
            logger.severe("Error sending message :" + e.getMessage());
        }
    }


    @Override
    public String getBotUsername() {
        return "MYHairBrush_Bot";
    }
}
