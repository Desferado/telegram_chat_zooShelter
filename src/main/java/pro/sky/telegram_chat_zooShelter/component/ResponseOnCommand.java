package pro.sky.telegram_chat_zooShelter.component;

import com.pengrad.telegrambot.model.User;
import com.pengrad.telegrambot.request.SendMessage;
import com.pengrad.telegrambot.response.SendResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pro.sky.telegram_chat_zooShelter.constants.Icon;
import pro.sky.telegram_chat_zooShelter.services.KeyBoardService;
import com.pengrad.telegrambot.TelegramBot;
import static pro.sky.telegram_chat_zooShelter.constants.Constants.*;

@Component

public class ResponseOnCommand {
    @Autowired
    private TelegramBot telegramBot;
    private final SendMessages sendMessages;

    public ResponseOnCommand(SendMessages sendMessages) {
        this.sendMessages = sendMessages;
    }
    public void responseOnCommandText(long chatId, String text){
        sendMessages.sendMessage(chatId, text);
    }
    /***
     * В ответ на команду "/start" метод отправляет в чат приветственное сообщение
     * с клавиатурой выбора приюта или вызов волонтера.
     */
    public void responseOnCommandStart(long chatId, String nameCustomer) {

        SendMessage sendMess = new SendMessage(chatId, "Привет, " + nameCustomer + "!"+ Icon.WAVE_Icon.get()+"\n"
                + "Приют животных Астаны приветствует тебя\n" + "Выбери отдел приюта\n");
        sendMess.replyMarkup(KeyBoardService.prepareKeyboardStart());
        SendResponse response = telegramBot.execute(sendMess);
    }
    /***
     * В ответ на выбор приюта кошек и собак метод отправляет в чат приветственное сообщение
     * от приюта с клавиатурой выбора меню для приюта
     * или вызов волонтера.
     */
    public void responseOnCommandShelter(long chatId, String message, String shelter) {
        SendMessage sendMess = new SendMessage(chatId, message);
        sendMess.replyMarkup(KeyBoardService.prepareKeyboardShelter(shelter));
        SendResponse response = telegramBot.execute(sendMess);
    }
    /***
     * В ответ на выбор информации о приюте метод отправляет в чат
     * приветственное сообщение с информацией
     * о приюте с клавиатурой выбора меню для приюта или вызов волонтера.
     */
    public void responseOnCommandInfoShelter(long chatId, String message, String shelter) {

        SendMessage sendMess = new SendMessage(chatId, message);
        sendMess.replyMarkup(KeyBoardService.prepareKeyboardInfoShelter(shelter));
        SendResponse response = telegramBot.execute(sendMess);
    }
    public void responseOnCommandContactVolunteerShelter(long chatId, User telegramCustomer, String nameCustomer){
        sendMessages.sendMessage(chatId, callVolunteer);
        sendMessages.sendMessage(1284536796L, "Клиент" + nameCustomer + " нуждается\n" +
                " в консультации. @" + telegramCustomer.username());
    }
    public void responseOnCommandCatRecommendation(Long chatId, String message){
        SendMessage sendMess = new SendMessage(chatId, message);
        sendMess.replyMarkup(KeyBoardService.prepareKeyboardCatRecommendation("кошек"));
        SendResponse response = telegramBot.execute(sendMess);
    }
    public void responseOnCommandDogRecommendation(Long chatId, String message){
        SendMessage sendMess = new SendMessage(chatId, message);
        sendMess.replyMarkup(KeyBoardService.prepareKeyboardDogRecommendation("собак"));
        SendResponse response = telegramBot.execute(sendMess);
    }
    public void responseOnCommandGetPet(Long chatId, String message, String shelter){
        SendMessage sendMess = new SendMessage(chatId,message);
        sendMess.replyMarkup(KeyBoardService.prepareKeyboardGetPet(shelter));
        SendResponse response = telegramBot.execute(sendMess);
    }
    private void responseOnCommandSendTextReport(String text){
        sendMessages.sendMessage(1284536796L, "Привет");
        if (text.toLowerCase().contains("отчет")) {
            sendMessages.sendMessage(1284536796L, text);
        } else {
            sendMessages.sendMessage(1284536796L, "Пустое сообщение");
        }
    }

}
