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
        java.sql.Date date1 = mock(java.sql.Date.class);
        when(date1.toInstant()).thenReturn(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
        NotificationService.sendNotification(1, date1,
                java.util.Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        verify(date1).toInstant();
    }
}

