package pro.sky.telegram_chat_zooShelter.listener;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import com.pengrad.telegrambot.response.SendResponse;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import pro.sky.telegram_chat_zooShelter.component.SendNotificationTask;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class TelegramBotUpdatesListener implements UpdatesListener {

    private Logger logger = LoggerFactory.getLogger(TelegramBotUpdatesListener.class);

    private TelegramBot telegramBot;
    private  final SendNotificationTask sendNotificationTask;

    public TelegramBotUpdatesListener(TelegramBot telegramBot, SendNotificationTask sendNotificationTask) {
        this.telegramBot = telegramBot;
        this.sendNotificationTask = sendNotificationTask;
    }

    @PostConstruct
    public void init() {
        telegramBot.setUpdatesListener(this);
    }

    @Override
    public int process(List<Update> updates) {
        updates.forEach(update -> {
            logger.info("Processing update: {}", update);
            String text = update.message().text();
            Long chatId = update.message().chat().id();
            // Process your updates here
            if ("/start".equals(text)) {
                sendNotificationTask.sendMessage(chatId, "Привет!!! Выберите, пожалуйста, приют: Кошки или Собаки.");
            } else if ("Кошки".equals(text)){
                sendNotificationTask.sendMessage(chatId, "Вы выбрали приют кошек");
            } else if ("Собаки".equals(text)) {
                sendNotificationTask.sendMessage(chatId, "Вы выбрали приют собак");
            }

        });
        return UpdatesListener.CONFIRMED_UPDATES_ALL;
    }
}
