package pro.sky.telegram_chat_zooShelter.component;

import org.springframework.stereotype.Component;
import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.request.ParseMode;
import com.pengrad.telegrambot.request.SendMessage;
import com.pengrad.telegrambot.response.SendResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.lang.Nullable;


@Component

public class SendNotification {
    private static final Logger LOG = LoggerFactory.getLogger(SendNotification.class);
    private  final TelegramBot telegramBot;


    public SendNotification(TelegramBot telegramBot) {
        this.telegramBot = telegramBot;
    }

    public void sendMessage(Long chatId,
                            String text,
                            @Nullable ParseMode parseMode) {
        SendMessage sendMessage = new SendMessage(chatId, text);
        if (parseMode != null) {
            sendMessage.parseMode(parseMode);
        }
        SendResponse sendResponse = telegramBot.execute(sendMessage);
        if (!sendResponse.isOk()) {
            LOG.error(sendResponse.toString());
        }
    }
    public void sendMessage(Long chatId,
                            String text) {
        sendMessage(chatId, text, null);
    }
}
