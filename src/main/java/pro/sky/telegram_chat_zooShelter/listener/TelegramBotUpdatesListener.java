package pro.sky.telegram_chat_zooShelter.listener;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.model.User;

import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import pro.sky.telegram_chat_zooShelter.component.ResponseOnCommand;
import pro.sky.telegram_chat_zooShelter.component.SendMessages;
import pro.sky.telegram_chat_zooShelter.model.Customer;
import pro.sky.telegram_chat_zooShelter.services.CustomerService;
import pro.sky.telegram_chat_zooShelter.services.PhotoPetService;
import pro.sky.telegram_chat_zooShelter.model.UploadPhoto;
import com.pengrad.telegrambot.model.PhotoSize;
//import org.telegram.telegrambots.meta.api.objects.PhotoSize;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import static pro.sky.telegram_chat_zooShelter.constants.Constants.*;
@Service
public class TelegramBotUpdatesListener implements UpdatesListener {
    private final Logger logger = LoggerFactory.getLogger(TelegramBotUpdatesListener.class);

    @Autowired
    private TelegramBot telegramBot;
    private User telegramCustomer;
    private final CustomerService customer;

    private final PhotoPetService photoPetService;
    private final SendMessages sendMessages;
    private  final ResponseOnCommand responseOnCommand;

    private String nameCustomer;
    private String tlText;
    @Value("${telegram.bot.token}")
    private String token;

    public TelegramBotUpdatesListener(TelegramBot telegramBot, CustomerService customer, PhotoPetService photoPetService, SendMessages sendMessages, ResponseOnCommand responseOnCommand) {
        this.telegramBot = telegramBot;
        this.customer = customer;
        this.photoPetService = photoPetService;
        this.sendMessages = sendMessages;
        this.responseOnCommand = responseOnCommand;
    }
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
                tlText = update.message().text();
                if (tlText != null && tlText.equals("/start")) {
                    Long chatId = update.message().chat().id();
                    nameCustomer = update.message().from().firstName();
                    responseOnCommand.responseOnCommandStart(chatId, nameCustomer);
                    if(customer.findCustomerByChatId(chatId)== null) {
                        customer.createCustomer(new Customer(
                                telegramCustomer.id(),
                                chatId,
                                telegramCustomer.lastName(),
                                telegramCustomer.firstName(),
                                telegramCustomer.username(),
                                null,
                                null,
                                null,
                                null,
                                null
                        ));
                    }
                } else if (update.message().photo() != null ) {
                    sendMessages.sendMessage(1284536796L, "Клиент " + telegramCustomer.firstName() +
                                    " id = "
                            + " прислал фото");
                    PhotoSize[] photos = update.message().photo();
                    Long chatId = update.message().chat().id();
                    Long petsId = customer.findCustomerByChatId(chatId).getPets().getId();
//                    try {
//                        photoPetService.uploadPhotoPet(petsId,UploadPhoto.createMultipartFileFromPhotoSize(photos));
//                    } catch (IOException e) {
//                        throw new RuntimeException(e);
//                    }
                } else if (tlText.toLowerCase().contains("отчет")) {
                    sendMessages.sendMessage (1284536796L, LocalDate.now() + " - Клиент "
                            + telegramCustomer.firstName()
                            + " id" + telegramCustomer.id()
                            + " прислал отчет: " + tlText);
                } else if (tlText.toLowerCase().contains("контакт")){
                    sendMessages.sendMessage (1284536796L, "Клиент " + nameCustomer
                            + " прислал свои контактные данные для связи: " + tlText);

                }
            } else if (update.callbackQuery() != null) {
                Long chatId = update.callbackQuery().message().chat().id();
                switch (update.callbackQuery().data()) {
                    case ("CAT"):
                        responseOnCommand.responseOnCommandShelter(chatId, greetingTextCat, "кошек");
                        break;
                    case ("DOG"):
                        responseOnCommand.responseOnCommandShelter(chatId, greetingTextCat, "собак");
                        break;
                    case ("INFO" + "кошек"):
                        responseOnCommand.responseOnCommandInfoShelter(chatId, helloShelter, "кошек");
                        break;
                    case ("INFO" + "собак"):
                        responseOnCommand.responseOnCommandInfoShelter(chatId, helloShelter, "собак");
                        break;
                    case ("INFOSHELTER" + "кошек"):
                        responseOnCommand.responseOnCommandText(chatId, aboutCatShelter);
                        break;
                    case ("INFOSHELTER" + "собак"):
                        responseOnCommand.responseOnCommandText(chatId,aboutDogShelter);
                        break;
                    case("CONTSHELTER" + "кошек"):
                        responseOnCommand.responseOnCommandText(chatId, contactCatShelter);
                        break;
                    case("CONTSHELTER" + "собак"):
                        responseOnCommand.responseOnCommandText(chatId, contactDogShelter);
                        break;
                    case("CONTSECURITY" + "кошек"):
                        responseOnCommand.responseOnCommandText(chatId, contactSecurityCatShelter);
                        break;
                    case("CONTSECURITY" + "собак"):
                        responseOnCommand.responseOnCommandText(chatId, contactSecurityDogShelter);
                        break;
                    case("CALL_VOLUNTEER" + "кошек"), ("CALL_VOLUNTEER" + "собак"):
                        responseOnCommand.responseOnCommandContactVolunteerShelter(chatId,telegramCustomer,callVolunteer);
                        break;
                    case("SENDREPORT"):
                        responseOnCommand.responseOnCommandText(chatId, reportShelter);
                        break;
                    case("GETPET" + "кошек"):
                        responseOnCommand.responseOnCommandGetPet(chatId,greeting, "кошек");
                        break;
                    case("GETPET" + "собак"):
                        responseOnCommand.responseOnCommandGetPet(chatId,greeting, "собак");
                        break;
                    case("RULES" + "кошек"), ("RULES" + "собак"):
                        responseOnCommand.responseOnCommandText(chatId, rulesShelter);
                        break;
                    case("RULESGETTING" + "кошек"), ("RULESGETTING" + "собак"):
                        responseOnCommand.responseOnCommandText(chatId, rules);
                        break;
                    case("LISTDOCUMENTS" + "кошек"), ("LISTDOCUMENTS" + "собак"):
                        responseOnCommand.responseOnCommandText(chatId, listDocuments);
                        break;
                    case("RECOMMENDATIONS" + "кошек"):
                        responseOnCommand.responseOnCommandCatRecommendation(chatId, recommendations);
                        break;
                    case("RECOMMENDATIONS" + "собак"):
                        responseOnCommand.responseOnCommandDogRecommendation(chatId, recommendations);
                        break;
                    case("REJECTION" + "кошек"), ("REJECTION" + "собак"):
                        responseOnCommand.responseOnCommandText(chatId, rejection);
                        break;
                    case("CONNECT" + "кошек"), ("CONNECT" + "собак"):
                        responseOnCommand.responseOnCommandText(chatId, getContact);
                        break;
                    case("TRANSPORT" + "кошек"), ("TRANSPORT" + "собак"):
                        responseOnCommand.responseOnCommandText(chatId, transportRecom);
                        break;
                    case("YANG" + "кошек"), ("YANG" + "собак"):
                        responseOnCommand.responseOnCommandText(chatId, yangRecom);
                        break;
                    case("OLD" + "кошек"), ("OLD" + "собак"):
                        responseOnCommand.responseOnCommandText(chatId, oldRecom);
                        break;
                    case("DISABILITIES" + "кошек"), ("DISABILITIES" + "собак"):
                        responseOnCommand.responseOnCommandText(chatId, disabilitiesRecom);
                        break;
                    case("CYNOLOGIST"):
                        responseOnCommand.responseOnCommandText(chatId, cynologistRecom);
                        break;
                    case("BESTCYNOLOGIST"):
                        responseOnCommand.responseOnCommandText(chatId, bestCynologist);
                        break;
                }
            }
        });
        return UpdatesListener.CONFIRMED_UPDATES_ALL;
    }
}