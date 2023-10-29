package pro.sky.telegram_chat_zooShelter.services;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.ZoneOffset;

import static org.mockito.Mockito.*;

class NotificationServiceTest {
    /**
     * Method under test: {@link NotificationService#sendNotification(int, java.util.Date, java.util.Date)}
     */
    @Test
    void testSendNotification() {
        // Создаем экземпляр NotificationService (предполагая, что он имеет конструктор по умолчанию)
        NotificationService notificationService = new NotificationService();

        // Создаем mock объект java.sql.Date
        java.sql.Date date1 = mock(java.sql.Date.class);

        // Настроим mock, чтобы он возвращал нужное значение, когда вызывается toInstant()
        when(date1.toInstant()).thenReturn(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());

        // Вызываем метод sendNotification на экземпляре NotificationService
        notificationService.sendNotification(1, date1,
                java.util.Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));

        // Проверяем, что метод toInstant() был вызван на mock объекте date1
        verify(date1).toInstant();
    }

}

