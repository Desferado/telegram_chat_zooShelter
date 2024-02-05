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
import pro.sky.telegram_chat_zooShelter.constants.Icon;
import pro.sky.telegram_chat_zooShelter.model.Customer;
import pro.sky.telegram_chat_zooShelter.services.CustomerService;
import pro.sky.telegram_chat_zooShelter.services.KeyBoardService;

import java.util.List;

import static pro.sky.telegram_chat_zooShelter.constants.Constants.*;

@Service
public class TelegramBotUpdatesListener implements UpdatesListener {

    private final Logger logger = LoggerFactory.getLogger(TelegramBotUpdatesListener.class);

    @Autowired
    private TelegramBot telegramBot;
    private User telegramCustomer;
    private final CustomerService customer;


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
                if (update.message().text() != null && update.message().text().equals("/start")) {
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
                } else if (update.message().text() != null && update.message().text().equals("/help")) {
                    Long chatId = update.message().chat().id();
                    nameCustomer = update.message().from().firstName();
                    responseOnCommandHelp(chatId);
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
                    case("SENDREPORT"):
                        responseOnCommandReportShelter(chatId);
                        break;
                    case("GETPET" + "кошек"):
                        responseOnCommandGetCat(chatId);
                        break;
                    case("GETPET" + "собак"):
                        responseOnCommandGetDog(chatId);
                        break;
                    case("RULES" + "кошек"):
                        responseOnCommandRulesCatShelter(chatId);
                        break;
                    case("RULES" + "собак"):
                        responseOnCommandRulesDogShelter(chatId);
                        break;
                    case("RULESGETTING" + "кошек"):
                        responseOnCommandRulesCat(chatId);
                        break;
                    case("RULESGETTING" + "собак"):
                        responseOnCommandRulesDog(chatId);
                        break;
                    case("LISTDOCUMENTS" + "кошек"):
                        responseOnCommandCatDocuments(chatId);
                        break;
                    case("LISTDOCUMENTS" + "собак"):
                        responseOnCommandDogDocuments(chatId);
                        break;
                    case("RECOMMENDATIONS" + "кошек"):
                        responseOnCommandCatRecommendation(chatId);
                        break;
                    case("RECOMMENDATIONS" + "собак"):
                        responseOnCommandDogRecommendation(chatId);
                        break;
                    case("REJECTION" + "кошек"):
                        responseOnCommandCatRejection(chatId);
                        break;
                    case("REJECTION" + "собак"):
                        responseOnCommandDogRejection(chatId);
                        break;
                    case("CONNECT" + "кошек"):
                        responseOnCommandGetContact(chatId);
                        break;
                    case("CONNECT" + "собак"):
                        responseOnCommandGetContact(chatId);
                        break;
                    case("TRANSPORT" + "кошек"):
                        responseOnCommandCatTransport(chatId);
                        break;
                    case("TRANSPORT" + "собак"):
                        responseOnCommandDogTransport(chatId);
                        break;
                    case("YANG" + "кошек"):
                        responseOnCommandCatYang(chatId);
                        break;
                    case("YANG" + "собак"):
                        responseOnCommandDogYang(chatId);
                        break;
                    case("OLD" + "кошек"):
                        responseOnCommandCatOld(chatId);
                        break;
                    case("OLD" + "собак"):
                        responseOnCommandDogOld(chatId);
                        break;
                    case("DISABILITIES" + "кошек"):
                        responseOnCommandCatDisabilities(chatId);
                        break;
                    case("DISABILITIES" + "собак"):
                        responseOnCommandDogDisabilities(chatId);
                        break;
                    case("CYNOLOGIST"):
                        responseOnCommandCynologist(chatId);
                        break;
                    case("BESTCYNOLOGIST"):
                        responseOnCommandBestCynologist(chatId);
                        break;
                }
            }
        });
        return UpdatesListener.CONFIRMED_UPDATES_ALL;
    }

    /***
     * В ответ на команду "/start" метод отправляет в чат приветственное сообщение
     * с клавиатурой выбора приюта или вызов волонтера.
     */
    private void responseOnCommandStart(long chatId) {

        SendMessage sendMess = new SendMessage(chatId, "Привет, " + nameCustomer + "!"+ Icon.WAVE_Icon.get()+"\n"
        + "Приют животных Астаны приветствует тебя\n" + "Выбери отдел приюта\n");
        sendMess.replyMarkup(prepareKeyboardStart());
        SendResponse response = telegramBot.execute(sendMess);
    }
    private void responseOnCommandHelp(long chatId) {
        SendMessage sendMess = new SendMessage(chatId, "Этот бот помогает клиентам взаимодействовать " +
                "с приютом.\n" +
                "Для запуска бота отправьте в чат сообщение '/start'." +
                "И следуйте указаниям его указаниям.");
        SendResponse response = telegramBot.execute(sendMess);
    }
    /***
     * В ответ на выбор приюта кошек метод отправляет в чат приветственное сообщение
     * от приюта с клавиатурой выбора меню для приюта кошек
     * или вызов волонтера.
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
     */
    private void responseOnCommandInfoDogShelter(long chatId) {
        SendMessage sendMess = new SendMessage(chatId, helloShelter);
        sendMess.replyMarkup(KeyBoardService.prepareKeyboardInfoShelter("собак"));
        SendResponse response = telegramBot.execute(sendMess);
    }
    /***
     * В ответ на выбор отправки отчета, метод отправляет в чат
     * приветственное сообщение с информацией
     * о правильном зполнении отчета и с клавиатурой выбора меню для приюта кошек или вызов волонтера.
     */
    private void responseOnCommandReportShelter(long chatId) {
        responseOnCommand(chatId, reportShelter);
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
     */
    private void responseOnCommandContactCatShelter(long chatId){
        responseOnCommand(chatId, contactCatShelter);
    }

    /***
     * В ответ на выбор контактов приюта собак метод отправляет в чат
     * контакты приюта.
     */
    private void responseOnCommandContactDogShelter(long chatId){
        responseOnCommand(chatId, contactDogShelter);
    }

    private void responseOnCommandContactVolunteerCatShelter(long chatId, User telegramCustomer){
        responseOnCommand(chatId, callVolunteer);
        responseOnCommand(1284536796, "Клиент" + nameCustomer + " нуждается\n" +
                " в консультации. @" + telegramCustomer.username());
    }
    private void responseOnCommandContactVolunteerDogShelter(long chatId, User telegramCustomer){
        responseOnCommand(chatId, callVolunteer);
        responseOnCommand(1284536796, "Клиент " + nameCustomer + " нуждается\n" +
                " в консультации. @" + telegramCustomer.username());
    }
    private void responseOnCommandGetContact(long chatId){
        responseOnCommand(chatId, getContact);
    }
    private void responseOnCommandCatRecommendation(Long chatId){
        SendMessage sendMess = new SendMessage(chatId, recommendations);
        sendMess.replyMarkup(KeyBoardService.prepareKeyboardCatRecommendation("кошек"));
        SendResponse response = telegramBot.execute(sendMess);
    }
    private void responseOnCommandDogRecommendation(Long chatId){
        SendMessage sendMess = new SendMessage(chatId, recommendations);
        sendMess.replyMarkup(KeyBoardService.prepareKeyboardDogRecommendation("собак"));
        SendResponse response = telegramBot.execute(sendMess);
    }

    private void responseOnCommandGetCat(Long chatId){
        SendMessage sendMess = new SendMessage(chatId,greeting);
        sendMess.replyMarkup(KeyBoardService.prepareKeyboardGetPet("кошек"));
        SendResponse response = telegramBot.execute(sendMess);
    }
    private void responseOnCommandGetDog(Long chatId){
        SendMessage sendMess = new SendMessage(chatId,greeting);
        sendMess.replyMarkup(KeyBoardService.prepareKeyboardGetPet("собак"));
        SendResponse response = telegramBot.execute(sendMess);
    }

    private void responseOnCommandRulesCatShelter(long chatId){
        responseOnCommand(chatId, rulesShelter);
    }
    private void responseOnCommandRulesDogShelter(long chatId){
        responseOnCommand(chatId, rulesShelter);
    }
    private void responseOnCommandRulesCat(long chatId){
        responseOnCommand(chatId, rules);
    }
    private void responseOnCommandRulesDog(long chatId){
        responseOnCommand(chatId, rules);
    }
    private void responseOnCommandCatDocuments(long chatId){
        responseOnCommand(chatId, listDocuments);
    }
    private void responseOnCommandDogDocuments(long chatId){
        responseOnCommand(chatId, listDocuments);
    }
    private void responseOnCommandCatRejection(long chatId){
        responseOnCommand(chatId, rejection);
    }
    private void responseOnCommandDogRejection(long chatId){
        responseOnCommand(chatId, rejection);
    }
    private void responseOnCommandCatTransport(long chatId){
        responseOnCommand(chatId, transportRecom);
    }
    private void responseOnCommandDogTransport(long chatId){
        responseOnCommand(chatId, transportRecom);
    }
    private void responseOnCommandCatYang(long chatId){
        responseOnCommand(chatId, yangRecom);
    }
    private void responseOnCommandDogYang(long chatId){
        responseOnCommand(chatId, yangRecom);
    }
    private void responseOnCommandCatOld(long chatId){
        responseOnCommand(chatId, oldRecom);
    }
    private void responseOnCommandDogOld(long chatId){
        responseOnCommand(chatId, oldRecom);
    }
    private void responseOnCommandCatDisabilities(long chatId){
        responseOnCommand(chatId, disabilitiesRecom);
    }
    private void responseOnCommandDogDisabilities(long chatId){
        responseOnCommand(chatId, disabilitiesRecom);
    }
    private void responseOnCommandCynologist(long chatId){
        responseOnCommand(chatId, cynologistRecom);
    }
    private void responseOnCommandBestCynologist(long chatId){
        responseOnCommand(chatId, bestCynologist);
    }

//    private SendMessage startBot(long chatId, String userName){
//        SendMessage message = new SendMessage(chatId,"Привет, " + userName
//                + "Приют животных Астаны приветствует тебя\n"
//                + "Выбери отдел приюта\n");
//        return message;
//    }

    private void responseOnCommand (long chatId, String text){
        SendMessage sendMess = new SendMessage(chatId, text);
        SendResponse response = telegramBot.execute(sendMess);
    }


     /** метод создает инлайн клавиатуру после отправки команды "/start"
     * @return клавиатура с подсообщением
      */

    private InlineKeyboardMarkup prepareKeyboardStart() {
        return KeyBoardService.prepareKeyboardStart("кошек" + Icon.CAT_Icon.get(), "собак"+Icon.DOG_Icon.get());
    }

//    private InlineKeyboardMarkup prepareKeyboardCatShelter() {
//        return KeyBoardService.prepareKeyboardShelter("кошек");
//    }
//     /** метод создает инлайн клавиатуру после выбора приюта собак
//     * @return клавиатура с подсообщением
//     */
//    private InlineKeyboardMarkup prepareKeyboardDogShelter() {
//        return KeyBoardService.prepareKeyboardShelter("собак");
//    }
//    private InlineKeyboardMarkup prepareKeyboardInfoCatShelter() {
//        return KeyBoardService.prepareKeyboardInfoShelter("кошек");
//    }
//    private InlineKeyboardMarkup prepareKeyboardInfoDogShelter() {
//        return KeyBoardService.prepareKeyboardInfoShelter("собак");
//    }
}