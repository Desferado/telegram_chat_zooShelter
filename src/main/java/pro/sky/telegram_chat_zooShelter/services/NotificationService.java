package pro.sky.telegram_chat_zooShelter.services;

import org.springframework.stereotype.Service;
import pro.sky.telegram_chat_zooShelter.component.SendMessages;
import pro.sky.telegram_chat_zooShelter.model.Pets;
import pro.sky.telegram_chat_zooShelter.model.Report;

import java.time.temporal.ChronoUnit;
import java.time.LocalDate;
import java.util.List;

import static pro.sky.telegram_chat_zooShelter.constants.Constants.*;

@Service
public class NotificationService {
    private Pets pets;
    private static PetsService petsService;
    private static ReportService reportService;
    private static CustomerService customerService;
    private static SendMessages sendMessages;
    private static VolunteerService volunteerService;
    static int limit;

    public static void notification (){
        List<Pets> petsList = petsService.findPetsWithCustomer();
        for (Pets pet: petsList) {
            LocalDate dateDes = pet.getDecisionDate();
            LocalDate dateNow = LocalDate.now();
            Integer addDays  = pet.getLimit_probation();
            sendNotification(addDays, dateDes, dateNow, pet);
        }
    }

    private static void sendNotification (Integer addDays, LocalDate  date1, LocalDate date2, Pets pet) {
       long daysBetween = calculateDaysBetween(date1,date2);
        limit = limit - addDays;
        if (daysBetween < limit) {
            if (pet.getProbationStatus().equals("в процессе")) {
                checkReport(pet);
            } else if (pet.getProbationStatus().equals("не пройден")) {
                sendMessages.sendMessage(pet.getCustomer().getChatId(), messageCustomer);
            }
        } else if (daysBetween == limit - 1) {
            if (pet.getProbationStatus().equals("в процессе")) {
                sendMessages.sendMessage(getChatIdVolunteer(pet), "У усыновителя питомца по имени" + pet.getName()
                        + " заканчивается испытательный срок.\n"
                        + " Необходимо принять решение.");
            } else if (pet.getProbationStatus().equals("не пройден")) {
                sendMessages.sendMessage(pet.getCustomer().getChatId(), messageCustomer);
            }
        } else if (daysBetween == limit) {
            if (pet.getProbationStatus().equals("в процессе")) {
                sendMessages.sendMessage(pet.getCustomer().getChatId(), greetingCustomer);
            } else if (pet.getProbationStatus().equals("не пройден")) {
                sendMessages.sendMessage(pet.getCustomer().getChatId(), messageCustomer);
            }
        }

    }

    private static void checkReport (Pets pet){
        if(pet.getProbationStatus().equals("в процессе")) {
            Long idPets = pet.getId();
            Report report = reportService.findTodayReportByPetId(idPets);
            if(report == null){
                Long chatId = customerService.findCustomerByPets(pet).getChatId();
                sendMessages.sendMessage(chatId, nullReportMessage);
            } else if (report.getPhotoPet() == null) {
                Long chatId = customerService.findCustomerByPets(pet).getChatId();
                sendMessages.sendMessage(chatId, nullPhotoPetMessage);
            }
        }
    }
    private static Long getChatIdVolunteer (Pets pet){
            Long shelterId = pet.getShelters().getId();
        return volunteerService.findVolunteerByShelters(shelterId).getChatId();
    }
       private static long calculateDaysBetween(LocalDate date1, LocalDate date2){
    return ChronoUnit.DAYS.between(date1,date2);
    }
}
