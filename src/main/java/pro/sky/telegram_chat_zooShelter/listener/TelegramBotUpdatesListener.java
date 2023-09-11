package pro.sky.telegram_chat_zooShelter.listener;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import com.pengrad.telegrambot.response.SendResponse;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TelegramBotUpdatesListener implements UpdatesListener {

    private Logger logger = LoggerFactory.getLogger(TelegramBotUpdatesListener.class);

    @Autowired
    private TelegramBot telegramBot;

    @PostConstruct
    public void init() {
        telegramBot.setUpdatesListener(this);
    }

    @Override
    public int process(List<Update> updates) {
        updates.forEach(update -> {
            logger.info("Processing update: {}", update);
            // Process your updates here
            if (update.message() != null) {
                if (update.message().text().equals("/start")) {
                    Long chatId = update.message().chat().id();
                    responseOnCommandStart(chatId);
                }
            }

            else if (update.callbackQuery() != null) {
                if (update.callbackQuery().data().equals("ONE")) {
                    Long chatId = update.callbackQuery().message().chat().id();
                    responseOnCommandInfoShelter(chatId);
                }
            }
        });
        return UpdatesListener.CONFIRMED_UPDATES_ALL;
    }
    private void responseOnCommandStart(long chatId) {

        SendMessage sendMess = new SendMessage(chatId, "Привет друг!" +
                "\nЯ бот-помощник и помогу познакомится " +
                "\nс приютом и питомцами.");
        sendMess.replyMarkup(preparekeyboardStart());
        SendResponse response = telegramBot.execute(sendMess);
    }
    private void responseOnCommandInfoShelter(long chatId) {

        SendMessage sendMess = new SendMessage(chatId, "Выбери то,что тебя интересует");
        sendMess.replyMarkup(preparekeyboardInfoShelter());
        SendResponse response = telegramBot.execute(sendMess);
    }
}
