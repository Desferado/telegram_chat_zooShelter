package pro.sky.telegram_chat_zooShelter.listener;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.model.PhotoSize;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.model.User;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.telegram.telegrambots.meta.api.methods.*;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import pro.sky.telegram_chat_zooShelter.component.ResponseOnCommand;
import pro.sky.telegram_chat_zooShelter.component.SendMessages;
import pro.sky.telegram_chat_zooShelter.model.Customer;
import pro.sky.telegram_chat_zooShelter.services.CustomerService;
import pro.sky.telegram_chat_zooShelter.services.PhotoPetService;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

import static pro.sky.telegram_chat_zooShelter.constants.Constants.*;
@Service
public class TelegramBotUpdatesListener implements UpdatesListener {
    private final Logger logger = LoggerFactory.getLogger(TelegramBotUpdatesListener.class);
    @Value("${path.to.avatars.folder}")
    private String coversDir;
    @Autowired
    private TelegramBot telegramBot;
    private User telegramCustomer;
    private final CustomerService customer;
    private final SendMessages sendMessages;
    private final ResponseOnCommand responseOnCommand;
    private final PhotoPetService photoPetService;

    private String nameCustomer;
    private String tlText;
    @Value("${telegram.bot.token}")
    private String token;

    public TelegramBotUpdatesListener(TelegramBot telegramBot, CustomerService customer,
                                      SendMessages sendMessages, ResponseOnCommand responseOnCommand,
                                      PhotoPetService photoPetService) {
        this.telegramBot = telegramBot;
        this.customer = customer;
        this.sendMessages = sendMessages;
        this.responseOnCommand = responseOnCommand;
        this.photoPetService = photoPetService;
    }

    @PostConstruct
    public void init() {
        telegramBot.setUpdatesListener(this);
    }

    @Override
    public int process(List<Update> updates) {
        updates.forEach(update -> {
            logger.info("Processing update: {}", update);
            if (update.message() != null && update.message().text() != null) {
                telegramCustomer = update.message().from();
                tlText = update.message().text();
                nameCustomer = telegramCustomer.firstName();
                if (tlText.equals("/start")) {
                    Long chatId = update.message().chat().id();
                    responseOnCommand.responseOnCommandStart(chatId, nameCustomer);
                    if (customer.findCustomerByChatId(chatId) == null) {
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
                } else if (tlText.toLowerCase().contains("отчет")) {
                    sendMessages.sendMessage(1284536796L, LocalDate.now() + " - Клиент "
                            + nameCustomer
                            + " id" + telegramCustomer.id()
                            + " прислал отчет: " + tlText);
                } else if (tlText.toLowerCase().contains("контакт")) {
                    sendMessages.sendMessage(1284536796L, "Клиент " + nameCustomer
                            + " прислал свои контактные данные для связи: " + tlText);
                }
            } else if (update.message() != null && update.message().photo() != null) {
                telegramCustomer = update.message().from();
                nameCustomer = telegramCustomer.firstName();
                Long chatId = update.message().chat().id();
                long petsId = customer.findCustomerByChatId(chatId).getPets().getId();
                PhotoSize[] photoSizes = update.message().photo();
                List<PhotoSize> photos = Arrays.stream(photoSizes).toList();
                PhotoSize photoSize = Objects.requireNonNull(photos.stream()
                        .max(Comparator.comparing(PhotoSize::fileSize))
                        .orElse(null));
                String f_id = photoSize.fileId();
                String fileT = photoSize.toString();
                String nameFile = LocalDate.now() + " Фото питомца с id = " + petsId
                        + ".";
                sendMessages.sendMessage(1284536796L, "Клиент " + nameCustomer +
                        " id = "
                        + " прислал фото " + nameFile
                );
                try {
                    GetFile getFileMethod = new GetFile(f_id);
                   File file = execute(getFileMethod); // Выполнение запроса на получение информации о файле
                    String filePath = file.getFilePath(); // Получаем путь к файлу
                    String originalFileName = filePath.substring(filePath.lastIndexOf("/") + 1); // Извлекаем имя файла из пути
                    System.out.println("Original File Name: " + originalFileName);
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
                try {
                    uploadPhoto(petsId, nameFile);
                } catch (IOException e) {
                    throw new RuntimeException(e);
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
                        responseOnCommand.responseOnCommandText(chatId, aboutDogShelter);
                        break;
                    case ("CONTSHELTER" + "кошек"):
                        responseOnCommand.responseOnCommandText(chatId, contactCatShelter);
                        break;
                    case ("CONTSHELTER" + "собак"):
                        responseOnCommand.responseOnCommandText(chatId, contactDogShelter);
                        break;
                    case ("CONTSECURITY" + "кошек"):
                        responseOnCommand.responseOnCommandText(chatId, contactSecurityCatShelter);
                        break;
                    case ("CONTSECURITY" + "собак"):
                        responseOnCommand.responseOnCommandText(chatId, contactSecurityDogShelter);
                        break;
                    case ("CALL_VOLUNTEER" + "кошек"), ("CALL_VOLUNTEER" + "собак"):
                        responseOnCommand.responseOnCommandContactVolunteerShelter(chatId, telegramCustomer, callVolunteer);
                        break;
                    case ("SENDREPORT"):
                        responseOnCommand.responseOnCommandText(chatId, reportShelter);
                        break;
                    case ("GETPET" + "кошек"):
                        responseOnCommand.responseOnCommandGetPet(chatId, greeting, "кошек");
                        break;
                    case ("GETPET" + "собак"):
                        responseOnCommand.responseOnCommandGetPet(chatId, greeting, "собак");
                        break;
                    case ("RULES" + "кошек"), ("RULES" + "собак"):
                        responseOnCommand.responseOnCommandText(chatId, rulesShelter);
                        break;
                    case ("RULESGETTING" + "кошек"), ("RULESGETTING" + "собак"):
                        responseOnCommand.responseOnCommandText(chatId, rules);
                        break;
                    case ("LISTDOCUMENTS" + "кошек"), ("LISTDOCUMENTS" + "собак"):
                        responseOnCommand.responseOnCommandText(chatId, listDocuments);
                        break;
                    case ("RECOMMENDATIONS" + "кошек"):
                        responseOnCommand.responseOnCommandCatRecommendation(chatId, recommendations);
                        break;
                    case ("RECOMMENDATIONS" + "собак"):
                        responseOnCommand.responseOnCommandDogRecommendation(chatId, recommendations);
                        break;
                    case ("REJECTION" + "кошек"), ("REJECTION" + "собак"):
                        responseOnCommand.responseOnCommandText(chatId, rejection);
                        break;
                    case ("CONNECT" + "кошек"), ("CONNECT" + "собак"):
                        responseOnCommand.responseOnCommandText(chatId, getContact);
                        break;
                    case ("TRANSPORT" + "кошек"), ("TRANSPORT" + "собак"):
                        responseOnCommand.responseOnCommandText(chatId, transportRecom);
                        break;
                    case ("YANG" + "кошек"), ("YANG" + "собак"):
                        responseOnCommand.responseOnCommandText(chatId, yangRecom);
                        break;
                    case ("OLD" + "кошек"), ("OLD" + "собак"):
                        responseOnCommand.responseOnCommandText(chatId, oldRecom);
                        break;
                    case ("DISABILITIES" + "кошек"), ("DISABILITIES" + "собак"):
                        responseOnCommand.responseOnCommandText(chatId, disabilitiesRecom);
                        break;
                    case ("CYNOLOGIST"):
                        responseOnCommand.responseOnCommandText(chatId, cynologistRecom);
                        break;
                    case ("BESTCYNOLOGIST"):
                        responseOnCommand.responseOnCommandText(chatId, bestCynologist);
                        break;
                }
            }
        });
        return UpdatesListener.CONFIRMED_UPDATES_ALL;
    }

    private MultipartFile createMultipartFileFromPhotoSize() throws IOException {
        // Сохраняем фото на сервере
        String filePath = coversDir + nameFile;
        try {
            File file = new File(filePath);
            // Чтение содержимого файла
            InputStream inputStream = Files.newInputStream(Paths.get(filePath));
            String originalFilename = file.getName();
            // Создаем объект MultipartFile из сохраненного файла
            return new MockMultipartFile(originalFilename, inputStream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    private void uploadPhoto(Long petsId, String nameFile ) throws IOException {
        MultipartFile multipartFile = createMultipartFileFromPhotoSize(nameFile);
        photoPetService.uploadPhotoPet(petsId, multipartFile);
    }


}