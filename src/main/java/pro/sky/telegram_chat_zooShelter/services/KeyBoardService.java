package pro.sky.telegram_chat_zooShelter.services;

import com.pengrad.telegrambot.model.request.InlineKeyboardButton;
import com.pengrad.telegrambot.model.request.InlineKeyboardMarkup;

public class KeyBoardService {
    public static InlineKeyboardMarkup preparekeyboardStart(String text1, String text2) {
        InlineKeyboardMarkup markupInline = new InlineKeyboardMarkup();

        InlineKeyboardButton button1 = new InlineKeyboardButton("Приют " + text1);
        InlineKeyboardButton button2 = new InlineKeyboardButton("Приют " + text2);
        InlineKeyboardButton button3 = new InlineKeyboardButton("Позвать волонтера");

        button1.callbackData("CAT");
        button2.callbackData("DOG");
        button3.callbackData("CALL_VOLUNTEER");


        markupInline.addRow(button1, button2);
        markupInline.addRow(button3);

        return markupInline;
    }

    public static InlineKeyboardMarkup preparekeyboardInfoShelter(String text) {
        InlineKeyboardMarkup markupInline = new InlineKeyboardMarkup();

        InlineKeyboardButton button1 = new InlineKeyboardButton("О приюте " + text);
        InlineKeyboardButton button2 = new InlineKeyboardButton("Контакты приюта " + text);
        InlineKeyboardButton button3 = new InlineKeyboardButton("Контакты охраны");
        InlineKeyboardButton button4 = new InlineKeyboardButton("Правила");
        InlineKeyboardButton button5 = new InlineKeyboardButton("Связаться");
        InlineKeyboardButton button6 = new InlineKeyboardButton("Позвать волонетра");

        button1.callbackData("INFO");
        button2.callbackData("CONTSHELTER");
        button3.callbackData("CONTSECURITY");
        button4.callbackData("RULES");
        button5.callbackData("CONNECT");
        button6.callbackData("VOLUNTEER");


        markupInline.addRow(button1, button2);
        markupInline.addRow(button3, button4);
        markupInline.addRow(button5, button6);
        return markupInline;
    }

}
