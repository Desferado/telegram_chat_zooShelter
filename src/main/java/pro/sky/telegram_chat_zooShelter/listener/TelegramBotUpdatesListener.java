package pro.sky.telegram_chat_zooShelter.listener;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.model.Update;
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

import static pro.sky.telegram_chat_zooShelter.Constants.*;

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
                        responseOnCommandCatShelter(chatId);
                        break;
                    case ("DOG"):
                        responseOnCommandDogShelter(chatId);
                        break;
                    case ("INFO" + "кошек"):
                        responseOnCommandInfoCatShelter(chatId);
                        break;
                    case ("INFO" + "собак"):
                        responseOnCommandInfoDogShelter(chatId);
                        break;
                    case("CONTSHELTER" + "кошек"):
                        responseOnCommandContactCatShelter(chatId);
                        break;
                    case("CONTSHELTER" + "собак"):
                        responseOnCommandContactDogShelter(chatId);
                        break;
                }
            }
        });
        return UpdatesListener.CONFIRMED_UPDATES_ALL;
    }
    private void responseOnCommandStart(long chatId) {

        SendMessage sendMess = new SendMessage(chatId, helloText);
        sendMess.replyMarkup(prepareKeyboardStart());
        SendResponse response = telegramBot.execute(sendMess);
    }

    private void responseOnCommandCatShelter(long chatId) {

        SendMessage sendMess = new SendMessage(chatId, greetingTextCat);
        sendMess.replyMarkup(prepareKeyboardCatShelter());
        SendResponse response = telegramBot.execute(sendMess);
    }
    private void responseOnCommandDogShelter(long chatId) {

        SendMessage sendMess = new SendMessage(chatId, greetingTextDog);
        sendMess.replyMarkup(prepareKeyboardDogShelter());
        SendResponse response = telegramBot.execute(sendMess);
    }
    private void responseOnCommandInfoCatShelter(long chatId) {

        SendMessage sendMess = new SendMessage(chatId, aboutCatShelter);
        sendMess.replyMarkup(prepareKeyboardInfoCatShelter());
        SendResponse response = telegramBot.execute(sendMess);
    }
    private void responseOnCommandInfoDogShelter(long chatId) {

        SendMessage sendMess = new SendMessage(chatId, aboutDogShelter);
        sendMess.replyMarkup(prepareKeyboardInfoDogShelter());
        SendResponse response = telegramBot.execute(sendMess);
    }
    private void responseOnCommandContactCatShelter(long chatId){

        SendMessage sendMess = new SendMessage(chatId, contactCatShelter);
        SendResponse response = telegramBot.execute(sendMess);
    }
    private void responseOnCommandContactDogShelter(long chatId){

        SendMessage sendMess = new SendMessage(chatId, contactDogShelter);
        SendResponse response = telegramBot.execute(sendMess);
    }

     /* метод создает инлайн клавиатуру после отправки команды Start
    @return клавиатура подсообщением
     */

    private InlineKeyboardMarkup prepareKeyboardStart() {
        return KeyBoardService.prepareKeyboardStart("кошек", "собак");
    }
    /* метод создает инлайн клавиатуру после отправки команды для получения информации о приюте
      @return клавиатура под сообщением
     */
    private InlineKeyboardMarkup prepareKeyboardCatShelter() {
        return KeyBoardService.prepareKeyboardShelter("кошек");
    }
    private InlineKeyboardMarkup prepareKeyboardDogShelter() {
        return KeyBoardService.prepareKeyboardShelter("собак");
    }
    private InlineKeyboardMarkup prepareKeyboardInfoCatShelter() {
        return KeyBoardService.prepareKeyboardInfoShelter("кошек");
    }
    private InlineKeyboardMarkup prepareKeyboardInfoDogShelter() {
        return KeyBoardService.prepareKeyboardInfoShelter("собак");
    }
}