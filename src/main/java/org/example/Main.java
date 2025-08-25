package org.example;

import org.example.conection.coection;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;
import static org.example.database.database.DatabaseConnection.connect;

public class Main {
    public static void main(String[] args) throws TelegramApiException {
        connect();

        try {
            TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);
            botsApi.registerBot(new coection.MyTelegramBot());
            System.out.println(" ربات با موفقیت راه‌اندازی شد!");
        } catch (Exception e) {
            System.err.println("❌ خطا در اتصال به Telegram API: " + e.getMessage());
            e.printStackTrace();
        }

    }
}