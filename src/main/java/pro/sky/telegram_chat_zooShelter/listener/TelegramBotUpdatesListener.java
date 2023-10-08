package pro.sky.telegram_chat_zooShelter.listener;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.model.User;
import com.pengrad.telegrambot.model.request.InlineKeyboardMarkup;
import com.pengrad.telegrambot.request.SendMessage;
import com.pengrad.telegrambot.response.SendResponse;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pro.sky.telegram_chat_zooShelter.model.Customer;
import pro.sky.telegram_chat_zooShelter.services.KeyBoardService;

import java.util.List;

import static pro.sky.telegram_chat_zooShelter.Constants.*;

@Service
public class TelegramBotUpdatesListener implements UpdatesListener {

    private final Logger logger = LoggerFactory.getLogger(TelegramBotUpdatesListener.class);

    @Autowired
    private TelegramBot telegramBot;
    private User  telegramCustomer;
    String nameCustomer;

    @PostConstruct
    public void init() {
        telegramBot.setUpdatesListener(this);
    }

    @Override
    public int process(List<Update> updates) {
        updates.forEach(update -> {
            logger.info("Processing update: {}", update);
            if (update.message() != null) {
                telegramCustomer = update.message().from();
                if (update.message().text().equals("/start")) {
                    Long chatId = update.message().chat().id();
                    nameCustomer = update.message().from().firstName();
                    responseOnCommandStart(chatId);
                    Customer customer = new Customer(
                            telegramCustomer.id(),
                            chatId,
                            telegramCustomer.lastName(),
                            telegramCustomer.firstName(),
                            telegramCustomer.username(),
                            null,
                            null,
                            null
                            );
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
                    case ("INFOSHELTER" + "кошек"):
                        responseOnCommandInfoCat(chatId);
                        break;
                    case ("INFOSHELTER" + "собак"):
                        responseOnCommandInfoDog(chatId);
                        break;
                    case("CONTSHELTER" + "кошек"):
                        responseOnCommandContactCatShelter(chatId);
                        break;
                    case("CONTSHELTER" + "собак"):
                        responseOnCommandContactDogShelter(chatId);
                        break;
                    case("CONTSECURITY" + "кошек"):
                        responseOnCommandContactSecurityCatShelter(chatId);
                        break;
                    case("CONTSECURITY" + "собак"):
                        responseOnCommandContactSecurityDogShelter(chatId);
                        break;
                    case("GETVOLUNTEER" + "кошек"):
                        responseOnCommandContactVolunteerCatShelter(chatId);
                        break;
                    case("GETVOLUNTEER" + "собак"):
                        responseOnCommandContactVolunteerDogShelter(chatId);
                        break;
                }
            }
        });
        return UpdatesListener.CONFIRMED_UPDATES_ALL;
    }

    /***
     * В ответ на команду "/start" метод отправляет в чат приветственное сообщение
     * с клавиатурой выбора приюта или вызов волонтера.
     * {@link #prepareKeyboardStart()}
     * @param chatId
     */
    private void responseOnCommandStart(long chatId) {

        SendMessage sendMess = new SendMessage(chatId, "Привет, " + telegramCustomer.firstName() + "!\n"
        + "Приют животных Астаны приветствует тебя\n" + "Выбери отдел приюта\n");
        sendMess.replyMarkup(prepareKeyboardStart());
        SendResponse response = telegramBot.execute(sendMess);
    }
    /***
     * В ответ на выбор приюта кошек метод отправляет в чат приветственное сообщение
     * от приюта с клавиатурой выбора меню для приюта кошек
     * или вызов волонтера.
     * {@link #prepareKeyboardCatShelter()}
     * @param chatId
     */
    private void responseOnCommandCatShelter(long chatId) {

        SendMessage sendMess = new SendMessage(chatId, greetingTextCat);
        sendMess.replyMarkup(prepareKeyboardCatShelter());
        SendResponse response = telegramBot.execute(sendMess);
    }
    /***
     * В ответ на выбор приюта собак метод отправляет в чат приветственное сообщение
     * от имени приюта с клавиатурой выбора меню для приюта собак
     * или вызов волонтера.
     * {@link #prepareKeyboardDogShelter()}
     * @param chatId
     */
    private void responseOnCommandDogShelter(long chatId) {

        SendMessage sendMess = new SendMessage(chatId, greetingTextDog);
        sendMess.replyMarkup(prepareKeyboardDogShelter());
        SendResponse response = telegramBot.execute(sendMess);
    }
    /***
     * В ответ на выбор информации о приюте кошек метод отправляет в чат
     * приветственное сообщение с информацией
     * о приюте с клавиатурой выбора меню для приюта кошек или вызов волонтера.
     * {@link #prepareKeyboardInfoCatShelter()}
     * @param chatId
     */
    private void responseOnCommandInfoCatShelter(long chatId) {

        SendMessage sendMess = new SendMessage(chatId, helloShelter);
        sendMess.replyMarkup(prepareKeyboardInfoCatShelter());
        SendResponse response = telegramBot.execute(sendMess);
    }

    /***
     * В ответ на выбор информации о приюте собак метод отправляет в чат
     * приветственное сообщение с информацией
     * о приюте с клавиатурой выбора меню для приюта собак или вызов волонтера.
     * {@link #prepareKeyboardInfoDogShelter()}
     * @param chatId
     */
    private void responseOnCommandInfoDogShelter(long chatId) {

        SendMessage sendMess = new SendMessage(chatId, helloShelter);
        sendMess.replyMarkup(prepareKeyboardInfoDogShelter());
        SendResponse response = telegramBot.execute(sendMess);
    }
    private void responseOnCommandInfoCat(long chatId) {

        SendMessage sendMess = new SendMessage(chatId, aboutCatShelter);
        SendResponse response = telegramBot.execute(sendMess);
    }
    private void responseOnCommandInfoDog(long chatId) {

        SendMessage sendMess = new SendMessage(chatId, aboutDogShelter);
        SendResponse response = telegramBot.execute(sendMess);
    }
    private void responseOnCommandContactSecurityCatShelter(long chatId) {

        SendMessage sendMess = new SendMessage(chatId, contactSecurityCatShelter);
        SendResponse response = telegramBot.execute(sendMess);
    }
    private void responseOnCommandContactSecurityDogShelter(long chatId) {

        SendMessage sendMess = new SendMessage(chatId, contactSecurityDogShelter);
        SendResponse response = telegramBot.execute(sendMess);
    }
    /***
     * В ответ на выбор контактов приюта кошек метод отправляет в чат
     * контакты приюта.
     * @param chatId
     */
    private void responseOnCommandContactCatShelter(long chatId){

        SendMessage sendMess = new SendMessage(chatId, contactCatShelter);
        SendResponse response = telegramBot.execute(sendMess);
    }
    /***
     * В ответ на выбор контактов приюта собак метод отправляет в чат
     * контакты приюта.
     * @param chatId
     */
    private void responseOnCommandContactDogShelter(long chatId){

        SendMessage sendMess = new SendMessage(chatId, contactDogShelter);
        SendResponse response = telegramBot.execute(sendMess);
    }

    private void responseOnCommandContactVolunteerCatShelter(long chatId){
        SendMessage sendMess = new SendMessage(chatId, "Информация передана волонтеру.\n" +
                "Ожидайте. Волонтер с вами свяжется для уточнения информации");
        SendResponse response = telegramBot.execute(sendMess);
    }
    private void responseOnCommandContactVolunteerDogShelter(long chatId){
        SendMessage sendMess = new SendMessage(chatId, "Информация передана волонтеру.\n" +
                "Ожидайте. Волонтер с вами свяжется для уточнения информации");
        SendResponse response = telegramBot.execute(sendMess);
    }


     /** метод создает инлайн клавиатуру после отправки команды "/start"
     * @return клавиатура с подсообщением
      */

    private InlineKeyboardMarkup prepareKeyboardStart() {
        return KeyBoardService.prepareKeyboardStart("кошек", "собак");
    }
     /** метод создает инлайн клавиатуру после выбора приюта кошек
     * @return клавиатура с подсообщением
     */
    private InlineKeyboardMarkup prepareKeyboardCatShelter() {
        return KeyBoardService.prepareKeyboardShelter("кошек");
    }
     /** метод создает инлайн клавиатуру после выбора приюта собак
     * @return клавиатура с подсообщением
     */
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