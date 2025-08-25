package org.example.conection;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;

public class connection  {

    public static class MyTelegramBot extends TelegramLongPollingBot {

        @Override
        public String getBotUsername() { return "@MYHairBrush_Bot"; }

        @Override
        public String getBotToken() { return "7521874173:AAEA2RTyF_DTXscXptk1IY0B9R9_OrJaNdk"; }

        @Override
        public void onUpdateReceived(Update update) {
            if (update.hasMessage() && update.getMessage().hasText()) {
                String messageText = update.getMessage().getText();
                long chatId = update.getMessage().getChatId();

                SendMessage response = new SendMessage();
                response.setChatId(chatId);
                response.setText(" " +
                        " دریافت شد: " + messageText);

                try {
                    execute(response);
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        }

    }

}
