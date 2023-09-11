package pro.sky.telegram_chat_zooShelter.listener;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.model.request.InlineKeyboardButton;
import com.pengrad.telegrambot.model.request.InlineKeyboardMarkup;
import com.pengrad.telegrambot.request.SendMessage;
import com.pengrad.telegrambot.response.SendResponse;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pro.sky.telegram_chat_zooShelter.services.KeyBoardService;

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
            if (update.message() != null) {
                if (update.message().text().equals("/start")) {
                    Long chatId = update.message().chat().id();
                    responseOnCommandStart(chatId);
                }
            } else if (update.callbackQuery() != null) {
                Long chatId = update.callbackQuery().message().chat().id();
                switch (update.callbackQuery().data()) {
                    case ("CAT"):
                    responseOnCommandInfoCATShelter(chatId);
                    break;
                    case ("DOG"):
                    responseOnCommandInfoDOGShelter(chatId);
                    break;
                }
            }
        });
        return UpdatesListener.CONFIRMED_UPDATES_ALL;
    }
    private void responseOnCommandStart(long chatId) {

        SendMessage sendMess = new SendMessage(chatId, "Привет друг!" +
                "\nЯ бот-помощник и помогу познакомится " +
                "\nс приютом и питомцами." +
                "\nСначала выбери приют.");
        sendMess.replyMarkup(preparekeyboardStart());
        SendResponse response = telegramBot.execute(sendMess);
    }
    private void responseOnCommandInfoCATShelter(long chatId) {

        SendMessage sendMess = new SendMessage(chatId, "Выбери то,что тебя интересует");
        sendMess.replyMarkup(preparekeyboardInfoCATShelter());
        SendResponse response = telegramBot.execute(sendMess);
    }
    private void responseOnCommandInfoDOGShelter(long chatId) {

        SendMessage sendMess = new SendMessage(chatId, "Выбери то,что тебя интересует");
        sendMess.replyMarkup(preparekeyboardInfoDOGShelter());
        SendResponse response = telegramBot.execute(sendMess);
    }
    /* метод создает инлайн клавиатуру после отправки команды Start
    @return клавиатура подсообщением
     */

    private InlineKeyboardMarkup preparekeyboardStart() {
        return KeyBoardService.preparekeyboardStart("кошек", "собак");
    }
    /* метод создает инлайн клавиатуру после отправки команды для получения информации о приюте
      @return клавиатура под сообщением
     */
    private InlineKeyboardMarkup preparekeyboardInfoCATShelter() {
        return KeyBoardService.preparekeyboardInfoShelter("кошек");
    }
    private InlineKeyboardMarkup preparekeyboardInfoDOGShelter() {
        return KeyBoardService.preparekeyboardInfoShelter("собак");
    }
}