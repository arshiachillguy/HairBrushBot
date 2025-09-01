package org.example.commands;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class commands extends TelegramLongPollingBot {

    @Override
    public String getBotUsername() {
        return "MYHairBrush_Bot";
    }

    @Override
    public String getBotToken() {
        return System.getenv("BOT_TOKEN") != null ? System.getenv("BOT_TOKEN") : "7521874173:AAEA2RTyF_DTXscXptk1IY0B9R9_OrJaNdk";
    }

    @Override
    public void onUpdateReceived(Update update) {
        System.out.println("دریافت آپدیت جدید: " + update);

        if (update.hasMessage() && update.getMessage().hasText()) {
            String messageText = update.getMessage().getText();
            Long chatId = update.getMessage().getChatId();

            System.out.println("پیام دریافتی: " + messageText + " | Chat ID: " + chatId);

            if (messageText.startsWith("/")) {
                System.out.println("پردازش دستور: " + messageText);
                handleCommand(messageText, chatId);
            } else {
                System.out.println("پردازش پیام معمولی: " + messageText);
                handleMessage(messageText, chatId);
            }
        } else {
            System.out.println("پیام غیرمعتبر یا بدون متن دریافت شد.");
        }
    }

    private void sendMessage(Long chatId, String text) {
        System.out.println("تلاش برای ارسال پیام: " + text + " به Chat ID: " + chatId);

        SendMessage message = new SendMessage();
        message.setChatId(String.valueOf(chatId));
        message.setText(text);

        try {
            execute(message);
            System.out.println("پیام با موفقیت ارسال شد: " + text);
        } catch (TelegramApiException e) {
            System.err.println("خطا در ارسال پیام: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // برای دستورات
    private void handleCommand(String command, Long chatId) {
        System.out.println("اجرای دستور: " + command);

        switch (command.toLowerCase()) {
            case "/start":
                sendMessage(chatId, "خوش اومدی به بات ما!");
                break;
            case "/help":
                sendMessage(chatId, "دستورات موجود:\n/start - شروع بات\n/help - نمایش راهنما\n/products - نمایش محصولات\n/buy - خرید محصول");
                break;
            case "/products":
                sendMessage(chatId, "لیست محصولات:\n1. محصول A\n2. محصول B\n3. محصول C");
                break;
            case "/buy":
                sendMessage(chatId, "لطفاً محصول مورد نظر خود را انتخاب کنید. برای مشاهده محصولات از /products استفاده کنید.");
                break;
            default:
                sendMessage(chatId, "دستور ناشناخته! لطفاً از /help استفاده کنید.");
                break;
        }
    }

    // برای پیام‌های معمولی
    private void handleMessage(String message, Long chatId) {
        System.out.println("پردازش پیام معمولی: " + message);

        String lowerCaseMessage = message.toLowerCase();
        if (lowerCaseMessage.contains("buy") || lowerCaseMessage.contains("خرید")) {
            sendMessage(chatId, "برای خرید، لطفاً از دستور /buy استفاده کنید.");
        } else if (lowerCaseMessage.contains("products") || lowerCaseMessage.contains("محصولات")) {
            sendMessage(chatId, "برای مشاهده محصولات، از دستور /products استفاده کنید.");
        } else if (lowerCaseMessage.contains("help") || lowerCaseMessage.contains("راهنما")) {
            sendMessage(chatId, "برای راهنما، از دستور /help استفاده کنید.");
        } else {
            sendMessage(chatId, "پیام شما شناخته نشد! لطفاً از /help برای دستورات موجود استفاده کنید.");
        }
    }
}