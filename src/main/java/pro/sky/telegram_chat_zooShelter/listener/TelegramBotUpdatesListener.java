package pro.sky.telegram_chat_zooShelter.listener;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.model.User;
import com.pengrad.telegrambot.request.SendMessage;
import com.pengrad.telegrambot.response.SendResponse;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pro.sky.telegram_chat_zooShelter.model.Customer;
import pro.sky.telegram_chat_zooShelter.services.CustomerService;
import pro.sky.telegram_chat_zooShelter.services.KeyBoardService;

import java.util.List;

import static pro.sky.telegram_chat_zooShelter.Constants.*;

@Service
public class TelegramBotUpdatesListener implements UpdatesListener {

    private final Logger logger = LoggerFactory.getLogger(TelegramBotUpdatesListener.class);

    @Autowired
    private TelegramBot telegramBot;
    private User  telegramCustomer;
    private CustomerService customer;


    public TelegramBotUpdatesListener(TelegramBot telegramBot, CustomerService customer) {
        this.telegramBot = telegramBot;
        this.customer = customer;
    }
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
                    if(customer.findCustomerByChatId(chatId)== null) {
                        customer.createCustomer(new Customer(
                                telegramCustomer.id(),
                                chatId,
                                telegramCustomer.lastName(),
                                telegramCustomer.firstName(),
                                telegramCustomer.username(),
                                null,
                                null,
                                null
                        ));
                    }
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
                    case("CALL_VOLUNTEER" + "кошек"):
                        responseOnCommandContactVolunteerCatShelter(chatId,telegramCustomer);
                        break;
                    case("CALL_VOLUNTEER" + "собак"):
                        responseOnCommandContactVolunteerDogShelter(chatId,telegramCustomer);
                        break;

                }
            }
        });
        return UpdatesListener.CONFIRMED_UPDATES_ALL;
    }

    /***
     * В ответ на команду "/start" метод отправляет в чат приветственное сообщение
     * с клавиатурой выбора приюта или вызов волонтера.
     * @param chatId
     */
    private void responseOnCommandStart(long chatId) {
        SendMessage sendMess = new SendMessage(chatId,"Привет, " + nameCustomer
                + "Приют животных Астаны приветствует тебя\n"
                + "Выбери отдел приюта\n");
        sendMess.replyMarkup(KeyBoardService.prepareKeyboardStart("кошек", "собак"));
        SendResponse response = telegramBot.execute(sendMess);
    }
    /***
     * В ответ на выбор приюта кошек метод отправляет в чат приветственное сообщение
     * от приюта с клавиатурой выбора меню для приюта кошек
     * или вызов волонтера.
     * @param chatId
     */
    private void responseOnCommandCatShelter(long chatId) {

        SendMessage sendMess = new SendMessage(chatId, greetingTextCat);
        sendMess.replyMarkup(KeyBoardService.prepareKeyboardShelter("кошек"));
        SendResponse response = telegramBot.execute(sendMess);
    }
    /***
     * В ответ на выбор приюта собак метод отправляет в чат приветственное сообщение
     * от имени приюта с клавиатурой выбора меню для приюта собак
     * или вызов волонтера.
     * @param chatId
     */
    private void responseOnCommandDogShelter(long chatId) {

        SendMessage sendMess = new SendMessage(chatId, greetingTextDog);
        sendMess.replyMarkup(KeyBoardService.prepareKeyboardShelter("собак"));
        SendResponse response = telegramBot.execute(sendMess);
    }
    /***
     * В ответ на выбор информации о приюте кошек метод отправляет в чат
     * приветственное сообщение с информацией
     * о приюте с клавиатурой выбора меню для приюта кошек или вызов волонтера.
     * @param chatId
     */
    private void responseOnCommandInfoCatShelter(long chatId) {

        SendMessage sendMess = new SendMessage(chatId, helloShelter);
        sendMess.replyMarkup(KeyBoardService.prepareKeyboardInfoShelter("кошек"));
        SendResponse response = telegramBot.execute(sendMess);
    }

    /***
     * В ответ на выбор информации о приюте собак метод отправляет в чат
     * приветственное сообщение с информацией
     * о приюте с клавиатурой выбора меню для приюта собак или вызов волонтера.
     * @param chatId
     */
    private void responseOnCommandInfoDogShelter(long chatId) {
        SendMessage sendMess = new SendMessage(chatId, helloShelter);
        sendMess.replyMarkup(KeyBoardService.prepareKeyboardInfoShelter("собак"));
        SendResponse response = telegramBot.execute(sendMess);
    }
    private void responseOnCommandInfoCat(long chatId) {
        responseOnCommand(chatId, aboutCatShelter);
    }
    private void responseOnCommandInfoDog(long chatId) {
        responseOnCommand(chatId, aboutDogShelter);
    }
    private void responseOnCommandContactSecurityCatShelter(long chatId) {
        responseOnCommand(chatId, contactSecurityCatShelter);
    }
    private void responseOnCommandContactSecurityDogShelter(long chatId) {
        responseOnCommand(chatId, contactSecurityDogShelter);
    }
    /***
     * В ответ на выбор контактов приюта кошек метод отправляет в чат
     * контакты приюта.
     * @param chatId
     */
    private void responseOnCommandContactCatShelter(long chatId){
        responseOnCommand(chatId, contactCatShelter);
    }
    /***
     * В ответ на выбор контактов приюта собак метод отправляет в чат
     * контакты приюта.
     * @param chatId
     */
    private void responseOnCommandContactDogShelter(long chatId){
        responseOnCommand(chatId, contactDogShelter);
    }

    private void responseOnCommandContactVolunteerCatShelter(long chatId, User telegramCustomer){
        responseOnCommand(chatId, callVolunter);
        responseOnCommand(1284536796, "Клиент" + nameCustomer + " нуждается\n" +
                " в консультации. @" + telegramCustomer.username());
    }
    private void responseOnCommandContactVolunteerDogShelter(long chatId, User telegramCustomer){
        responseOnCommand(chatId, callVolunter);
        responseOnCommand(1284536796, "Клиент " + nameCustomer + " нуждается\n" +
                " в консультации. @" + telegramCustomer.username());
    }

    private SendMessage startBot(long chatId, String userName){
        SendMessage message = new SendMessage(chatId,"Привет, " + userName
                + "Приют животных Астаны приветствует тебя\n"
                + "Выбери отдел приюта\n");
        return message;
    }
    private void responseOnCommand (long chatId, String text){
        SendMessage sendMess = new SendMessage(chatId, text);
        SendResponse response = telegramBot.execute(sendMess);
    }
}