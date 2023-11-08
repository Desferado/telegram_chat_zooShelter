package pro.sky.telegram_chat_zooShelter.component;


import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import pro.sky.telegram_chat_zooShelter.services.NotificationService;

@Component
public class CheckReportScheduled {

    public CheckReportScheduled(NotificationService notificationService) {
    }

    @Scheduled(cron = "0 0 22 * * *")
    @Transactional
    public void check() {
        NotificationService.notification();
    }
}
