package pro.sky.telegram_chat_zooShelter.services;

import com.pengrad.telegrambot.model.request.InlineKeyboardButton;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class KeyBoardServiceTest {
    /**
     * Method under test: {@link KeyBoardService#prepareKeyboardStart(String, String)}
     */
    @Test
    void testPrepareKeyboardStart() {
        // Вызываем метод prepareKeyboardStart() из вашего сервиса, передавая две строки "Text1" и "Text2"
        InlineKeyboardButton[][] inlineKeyboardResult = KeyBoardService.prepareKeyboardStart("Text1", "Text2")
                .inlineKeyboard();

        // Проверяем, что первый ряд клавиатуры содержит две кнопки с текстами "Приют Text1" и "Приют Text2"
        InlineKeyboardButton[] inlineKeyboardButtonArray2 = inlineKeyboardResult[0];
        assertEquals("Приют Text1", (inlineKeyboardButtonArray2[0]).text());
        assertEquals("Приют Text2", (inlineKeyboardButtonArray2[1]).text());

        // Проверяем, что второй ряд клавиатуры содержит две кнопки с текстами "Позвать волонтера Text1" и "Позвать волонтера Text2"
        InlineKeyboardButton[] inlineKeyboardButtonArray = inlineKeyboardResult[1];
        assertEquals("Позвать волонтера Text1", (inlineKeyboardButtonArray[0]).text());
        assertEquals("Позвать волонтера Text2", (inlineKeyboardButtonArray[1]).text());

        // Проверяем, что всего две строки в клавиатуре
        assertEquals(2, inlineKeyboardResult.length);
    }


    /**
     * Method under test: {@link KeyBoardService#prepareKeyboardInfoShelter(String)}
     */
    @Test
    void testPrepareKeyboardInfoShelter() {
        // Вызываем метод prepareKeyboardInfoShelter, который возвращает InlineKeyboard
        InlineKeyboardButton[][] inlineKeyboardResult = KeyBoardService.prepareKeyboardInfoShelter("Text").inlineKeyboard();

        // Проверяем, что тексты кнопок на клавиатуре соответствуют ожидаемым значениям
        InlineKeyboardButton[] inlineKeyboardButtonArray = inlineKeyboardResult[1];
        assertEquals("Контакты охраны", (inlineKeyboardButtonArray[0]).text());

        InlineKeyboardButton[] inlineKeyboardButtonArray2 = inlineKeyboardResult[0];
        assertEquals("Контакты приюта Text", (inlineKeyboardButtonArray2[1]).text());
        assertEquals("О приюте Text", (inlineKeyboardButtonArray2[0]).text());

        InlineKeyboardButton[] inlineKeyboardButtonArray3 = inlineKeyboardResult[2];
        assertEquals("Позвать волонетра", (inlineKeyboardButtonArray3[1]).text());
        assertEquals("Правила приюта", (inlineKeyboardButtonArray[1]).text());

        // Проверяем, что количество строк в клавиатуре соответствует ожидаемому (3 строки)
        assertEquals(3, inlineKeyboardResult.length);
    }


    /**
     * Method under test: {@link KeyBoardService#prepareKeyboardShelter(String)}
     */
    @Test
    void testPrepareKeyboardShelter() {
        // Вызываем метод prepareKeyboardShelter() из вашего сервиса, передавая строку "Text"
        InlineKeyboardButton[][] inlineKeyboardResult = KeyBoardService.prepareKeyboardShelter("Text").inlineKeyboard();

        // Проверяем, что первый ряд клавиатуры содержит две кнопки с текстами "Информацмя о приюте Text" и "Как взять питомца"
        InlineKeyboardButton[] inlineKeyboardButtonArray = inlineKeyboardResult[0];
        assertEquals("Информацмя о приюте Text", (inlineKeyboardButtonArray[0]).text());
        assertEquals("Как взять питомца", (inlineKeyboardButtonArray[1]).text());

        // Проверяем, что второй ряд клавиатуры содержит две кнопки с текстами "Отправить отчет" и "Позвать волонетра"
        InlineKeyboardButton[] inlineKeyboardButtonArray2 = inlineKeyboardResult[1];
        assertEquals("Отправить отчет", (inlineKeyboardButtonArray2[0]).text());
        assertEquals("Позвать волонетра", (inlineKeyboardButtonArray2[1]).text());

        // Проверяем, что всего две строки в клавиатуре
        assertEquals(2, inlineKeyboardResult.length);
    }


    /**
     * Method under test: {@link KeyBoardService#prepareKeyboardGetPet(String)}
     */
    @Test
    void testPrepareKeyboardGetPet() {
        // Вызываем метод prepareKeyboardGetPet, который возвращает InlineKeyboard
        InlineKeyboardButton[][] inlineKeyboardResult = KeyBoardService.prepareKeyboardGetPet("Text").inlineKeyboard();

        // Проверяем, что тексты кнопок на клавиатуре соответствуют ожидаемым значениям
        InlineKeyboardButton[] inlineKeyboardButtonArray = inlineKeyboardResult[2];
        assertEquals("Оставить контакты для связи", (inlineKeyboardButtonArray[0]).text());
        assertEquals("Позвать волонетра", (inlineKeyboardButtonArray[1]).text());

        InlineKeyboardButton[] inlineKeyboardButtonArray2 = inlineKeyboardResult[0];
        assertEquals("Правила знакомства ", (inlineKeyboardButtonArray2[0]).text());
        assertEquals("Список документов ", (inlineKeyboardButtonArray2[1]).text());

        InlineKeyboardButton[] inlineKeyboardButtonArray3 = inlineKeyboardResult[1];
        assertEquals("Рекомендаци", (inlineKeyboardButtonArray3[0]).text());
        assertEquals("Список причин для отказа", (inlineKeyboardButtonArray3[1]).text());

        // Проверяем, что количество строк в клавиатуре соответствует ожидаемому (3 строки)
        assertEquals(3, inlineKeyboardResult.length);
    }


    /**
     * Method under test: {@link KeyBoardService#prepareKeyboardCatRecommendation(String)}
     */
    @Test
    void testPrepareKeyboardCatRecommendation() {
        // Вызываем метод prepareKeyboardCatRecommendation, который возвращает InlineKeyboard
        InlineKeyboardButton[][] inlineKeyboardResult = KeyBoardService.prepareKeyboardCatRecommendation("Text").inlineKeyboard();

        // Проверяем, что тексты кнопок на клавиатуре соответствуют ожидаемым значениям
        assertEquals("Советы по обустройству\n дома для взрослого животного", (inlineKeyboardResult[2][0]).text());
        assertEquals("Советы по обустройству\n дома для животного\n с ограниченными возможностями",
                (inlineKeyboardResult[3][0]).text());
        assertEquals("Советы по обустройству\n дома для котенка", (inlineKeyboardResult[1][0]).text());
        assertEquals("Советы по транспортировке\n животного", (inlineKeyboardResult[0][0]).text());

        // Проверяем, что количество строк в клавиатуре соответствует ожидаемому (4 строки)
        assertEquals(4, inlineKeyboardResult.length);
    }



    /**
     * Method under test: {@link KeyBoardService#prepareKeyboardDogRecommendation(String)}
     */
    @Test
    void testPrepareKeyboardDogRecommendation() {
        // Вызываем метод prepareKeyboardDogRecommendation, который возвращает InlineKeyboard
        InlineKeyboardButton[][] inlineKeyboardResult = KeyBoardService.prepareKeyboardDogRecommendation("Text").inlineKeyboard();

        // Проверяем, что тексты кнопок на клавиатуре соответствуют ожидаемым значениям
        assertEquals("Рекомендации по проверенным кинологам", (inlineKeyboardResult[5][0]).text());
        assertEquals("Рекомендации по транспортировке\n животного", (inlineKeyboardResult[0][0]).text());
        assertEquals("Рекомендаций по обустройству\n дома для взрослого животного", (inlineKeyboardResult[2][0]).text());
        assertEquals("Рекомендаций по обустройству\n дома для животного\n с ограниченными возможностями",
                (inlineKeyboardResult[3][0]).text());
        assertEquals("Рекомендаций по обустройству\n дома для котенка", (inlineKeyboardResult[1][0]).text());
        assertEquals("Советы кинолога", (inlineKeyboardResult[4][0]).text());

        // Проверяем, что количество строк в клавиатуре соответствует ожидаемому (6 строк)
        assertEquals(6, inlineKeyboardResult.length);
    }


}

