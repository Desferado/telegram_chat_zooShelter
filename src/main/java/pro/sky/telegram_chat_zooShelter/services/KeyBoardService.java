package pro.sky.telegram_chat_zooShelter.services;

import com.pengrad.telegrambot.model.request.InlineKeyboardButton;
import com.pengrad.telegrambot.model.request.InlineKeyboardMarkup;
import pro.sky.telegram_chat_zooShelter.constants.Icon;

public class KeyBoardService {
    public static InlineKeyboardMarkup prepareKeyboardStart(String text1, String text2) {
        InlineKeyboardMarkup markupInline = new InlineKeyboardMarkup();

        InlineKeyboardButton button1 = new InlineKeyboardButton("Приют " + text1);
        InlineKeyboardButton button2 = new InlineKeyboardButton("Приют " + text2);
        InlineKeyboardButton button3 = new InlineKeyboardButton("Позвать волонтера " + text1);
        InlineKeyboardButton button4 = new InlineKeyboardButton("Позвать волонтера " + text2);

        button1.callbackData("CAT");
        button2.callbackData("DOG");
        button3.callbackData("CALL_VOLUNTEER" + text1);
        button4.callbackData("CALL_VOLUNTEER" + text2);

        markupInline.addRow(button1, button2);
        markupInline.addRow(button3, button4);

        return markupInline;
    }
    public static InlineKeyboardMarkup prepareKeyboardInfoShelter(String text) {
        InlineKeyboardMarkup markupInline = new InlineKeyboardMarkup();

        InlineKeyboardButton button1 = new InlineKeyboardButton("О приюте " + text);
        InlineKeyboardButton button2 = new InlineKeyboardButton("Контакты приюта " + text);
        InlineKeyboardButton button3 = new InlineKeyboardButton("Контакты охраны");
        InlineKeyboardButton button4 = new InlineKeyboardButton("Правила приюта");
        InlineKeyboardButton button5 = new InlineKeyboardButton("Связаться");
        InlineKeyboardButton button6 = new InlineKeyboardButton("Позвать волонетра");

        button1.callbackData("INFOSHELTER" + text);
        button2.callbackData("CONTSHELTER" + text);
        button3.callbackData("CONTSECURITY" + text);
        button4.callbackData("RULES" + text);
        button5.callbackData("CONNECT" + text);
        button6.callbackData("CALL_VOLUNTEER" + text);


        markupInline.addRow(button1, button2);
        markupInline.addRow(button3, button4);
        markupInline.addRow(button5, button6);
        return markupInline;
    }
    public static InlineKeyboardMarkup prepareKeyboardShelter(String text) {
        InlineKeyboardMarkup markupInline = new InlineKeyboardMarkup();

        InlineKeyboardButton button1 = new InlineKeyboardButton("Информация о приюте " + text);
        InlineKeyboardButton button2 = new InlineKeyboardButton("Как взять питомца");
        InlineKeyboardButton button3 = new InlineKeyboardButton("Отправить отчет");
        InlineKeyboardButton button4 = new InlineKeyboardButton("Позвать волонетра");

        button1.callbackData("INFO" + text);
        button2.callbackData("GETPET" + text);
        button3.callbackData("SENDREPORT");
        button4.callbackData("CALL_VOLUNTEER" + text);


        markupInline.addRow(button1, button2);
        markupInline.addRow(button3, button4);
        return markupInline;
    }
    public static InlineKeyboardMarkup prepareKeyboardGetPet(String text) {
        InlineKeyboardMarkup markupInline = new InlineKeyboardMarkup();

        InlineKeyboardButton button1 = new InlineKeyboardButton("Правила знакомства ");
        InlineKeyboardButton button2 = new InlineKeyboardButton("Список документов ");
        InlineKeyboardButton button3 = new InlineKeyboardButton("Рекомендаци");
        InlineKeyboardButton button4 = new InlineKeyboardButton("Список причин для отказа");
        InlineKeyboardButton button5 = new InlineKeyboardButton("Оставить контакты для связи");
        InlineKeyboardButton button6 = new InlineKeyboardButton("Позвать волонетра");

        button1.callbackData("RULESGETTING" + text);
        button2.callbackData("LISTDOCUMENTS" + text);
        button3.callbackData("RECOMMENDATIONS" + text);
        button4.callbackData("REJECTION" + text);
        button5.callbackData("CONNECT" + text);
        button6.callbackData("CALL_VOLUNTEER" + text);


        markupInline.addRow(button1, button2);
        markupInline.addRow(button3, button4);
        markupInline.addRow(button5, button6);
        return markupInline;
    }
    public static InlineKeyboardMarkup prepareKeyboardCatRecommendation(String text) {
        InlineKeyboardMarkup markupInline = new InlineKeyboardMarkup();

        InlineKeyboardButton button1 = new InlineKeyboardButton("Советы по транспортировке\n" + " животного");
        InlineKeyboardButton button2 = new InlineKeyboardButton("Советы по обустройству\n" + " дома для котенка");
        InlineKeyboardButton button3 = new InlineKeyboardButton("Советы по обустройству\n" + " дома для взрослого животного");
        InlineKeyboardButton button4 = new InlineKeyboardButton("""
                Советы по обустройству
                 дома для животного
                 с ограниченными возможностями""");

        button1.callbackData("TRANSPORT" + text);
        button2.callbackData("YANG" + text);
        button3.callbackData("OLD" + text);
        button4.callbackData("DISABILITIES" + text);


        markupInline.addRow(button1);
        markupInline.addRow(button2);
        markupInline.addRow(button3);
        markupInline.addRow(button4);
        return markupInline;
    }
    public static InlineKeyboardMarkup prepareKeyboardDogRecommendation(String text) {
        InlineKeyboardMarkup markupInline = new InlineKeyboardMarkup();

        InlineKeyboardButton button1 = new InlineKeyboardButton("Рекомендации по транспортировке\n" + " животного");
        InlineKeyboardButton button2 = new InlineKeyboardButton("Рекомендаций по обустройству\n" + " дома для котенка");
        InlineKeyboardButton button3 = new InlineKeyboardButton("Рекомендаций по обустройству\n" + " дома для взрослого животного");
        InlineKeyboardButton button4 = new InlineKeyboardButton("""
                Рекомендаций по обустройству
                 дома для животного
                 с ограниченными возможностями""");
        InlineKeyboardButton button5 = new InlineKeyboardButton("Советы кинолога");
        InlineKeyboardButton button6 = new InlineKeyboardButton("Рекомендации по проверенным кинологам");

        button1.callbackData("TRANSPORT" + text);
        button2.callbackData("YANG" + text);
        button3.callbackData("OLD" + text);
        button4.callbackData("DISABILITIES" + text);
        button5.callbackData("CYNOLOGIST");
        button6.callbackData("BESTCYNOLOGIST");


        markupInline.addRow(button1);
        markupInline.addRow(button2);
        markupInline.addRow(button3);
        markupInline.addRow(button4);
        markupInline.addRow(button5);
        markupInline.addRow(button6);
        return markupInline;
    }
    /** метод создает инлайн клавиатуру после отправки команды "/start"
     * @return клавиатура с подсообщением
     */
    public static InlineKeyboardMarkup prepareKeyboardStart() {
        return KeyBoardService.prepareKeyboardStart("кошек" + Icon.CAT_Icon.get(), "собак"+Icon.DOG_Icon.get());
    }
}
