package pro.sky.telegram_chat_zooShelter.services;

import org.springframework.stereotype.Service;
import pro.sky.telegram_chat_zooShelter.model.Pets;

import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.time.ZoneId;
import java.time.LocalDate;

@Service
public class NotificationService {
    private Pets pets;
    private PetsService petsService;
    static int limit;

    public static void sendNotification (int addDays, Date  date1, Date date2) {
       long daysBetween = calculateDaysBetween(date1,date2);
        limit = limit - addDays;
        if ( daysBetween < limit) {
           //  check report
        } else if (daysBetween == limit - 1) {
//            send message to volunteer check adopter probation
//            change addDays

        } else if (daysBetween == limit) {
//            send adopter congratulation with success
        }

    }



       private static long calculateDaysBetween(Date date1, Date date2){
        LocalDate localDate1 = date1.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate localDate2 = date2.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    return ChronoUnit.DAYS.between(localDate1,localDate2);
    }
}
