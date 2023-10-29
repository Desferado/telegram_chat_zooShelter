package pro.sky.telegram_chat_zooShelter.services;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.multipart.MultipartFile;
import pro.sky.telegram_chat_zooShelter.model.*;
import pro.sky.telegram_chat_zooShelter.repository.PhotoPetRepository;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.FileAttribute;
import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.*;

@ContextConfiguration(classes = {PhotoPetService.class})
@ExtendWith(SpringExtension.class)
class PhotoPetServiceTest {
    @MockBean
    private PetsService petsService;

    @MockBean
    private PhotoPetRepository photoPetRepository;

    @Autowired
    private PhotoPetService photoPetService;

    /**
     * Method under test: {@link PhotoPetService#uploadPhotoPet(Long, MultipartFile)}
     */
    @Test
    void testUploadPhotoPet() throws IOException {
        // Создаем статический мок (MockedStatic) для класса Files
        try (MockedStatic<Files> mockFiles = mockStatic(Files.class)) {

            // Устанавливаем моки для статических методов класса Files
            // Первый мок для метода deleteIfExists - возвращаем true
            mockFiles.when(() -> Files.deleteIfExists(Mockito.<Path>any())).thenReturn(true);

            // Второй мок для метода newOutputStream - возвращаем ByteArrayOutputStream
            mockFiles.when(() -> Files.newOutputStream(Mockito.<Path>any(), isA(OpenOption[].class)))
                    .thenReturn(new ByteArrayOutputStream(1));

            // Третий мок для метода createDirectories - возвращаем путь во временной директории
            mockFiles.when(() -> Files.createDirectories(Mockito.<Path>any(), isA(FileAttribute[].class)))
                    .thenReturn(Paths.get(System.getProperty("java.io.tmpdir"), "test.txt"));

            // Создаем объекты для тестирования
            Customer customer = new Customer();
            customer.setAddress("42 Main St");
            customer.setChatId(1L);
            customer.setEmail("jane.doe@example.org");
            customer.setId(1L);
            customer.setName("Name");
            customer.setPhone("6625550144");
            customer.setSecondName("Second Name");
            customer.setSurname("Doe");

            Shelters shelters = new Shelters();
            shelters.setAdress("Adress");
            shelters.setId(1L);
            shelters.setLocation("Location");
            shelters.setName("Name");

            Pets pets = new Pets();
            pets.setAge(1);
            pets.setBreed("Breed");
            pets.setCustomer(customer);
            pets.setDecisionDate(LocalDate.of(1970, 1, 1));
            pets.setId(1L);
            pets.setLimit_probation(1L);
            pets.setName("Bella");
            pets.setProbationStatus("Probation Status");
            pets.setShelters(shelters);
            pets.setType_pets("Bella");
            when(petsService.findPetById(Mockito.<Long>any())).thenReturn(pets);

            Customer customer2 = new Customer();
            customer2.setAddress("42 Main St");
            customer2.setChatId(1L);
            customer2.setEmail("jane.doe@example.org");
            customer2.setId(1L);
            customer2.setName("Name");
            customer2.setPhone("6625550144");
            customer2.setSecondName("Second Name");
            customer2.setSurname("Doe");

            Shelters shelters2 = new Shelters();
            shelters2.setAdress("Adress");
            shelters2.setId(1L);
            shelters2.setLocation("Location");
            shelters2.setName("Name");

            Pets pets2 = new Pets();
            pets2.setAge(1);
            pets2.setBreed("Breed");
            pets2.setCustomer(customer2);
            pets2.setDecisionDate(LocalDate.of(1970, 1, 1));
            pets2.setId(1L);
            pets2.setLimit_probation(1L);
            pets2.setName("Bella");
            pets2.setProbationStatus("Probation Status");
            pets2.setShelters(shelters2);
            pets2.setType_pets("Bella");

            Customer customer3 = new Customer();
            customer3.setAddress("42 Main St");
            customer3.setChatId(1L);
            customer3.setEmail("jane.doe@example.org");
            customer3.setId(1L);
            customer3.setName("Name");
            customer3.setPhone("6625550144");
            customer3.setSecondName("Second Name");
            customer3.setSurname("Doe");

            Shelters shelters3 = new Shelters();
            shelters3.setAdress("Adress");
            shelters3.setId(1L);
            shelters3.setLocation("Location");
            shelters3.setName("Name");

            Pets pets3 = new Pets();
            pets3.setAge(1);
            pets3.setBreed("Breed");
            pets3.setCustomer(customer3);
            pets3.setDecisionDate(LocalDate.of(1970, 1, 1));
            pets3.setId(1L);
            pets3.setLimit_probation(1L);
            pets3.setName("Bella");
            pets3.setProbationStatus("Probation Status");
            pets3.setShelters(shelters3);
            pets3.setType_pets("Bella");

            Report report = new Report();
            report.setDate(LocalDate.of(1970, 1, 1).atStartOfDay());
            report.setId(1L);
            report.setPetReport("Bella");
            report.setPets(pets3);

            PhotoPet photoPet = new PhotoPet();
            photoPet.setFilePath("/directory/foo.txt");
            photoPet.setFileSize(3L);
            photoPet.setId(1L);
            photoPet.setMediaType("Media Type");
            photoPet.setPets(pets2);
            photoPet.setReport(report);

            Customer customer4 = new Customer();
            customer4.setAddress("42 Main St");
            customer4.setChatId(1L);
            customer4.setEmail("jane.doe@example.org");
            customer4.setId(1L);
            customer4.setName("Name");
            customer4.setPhone("6625550144");
            customer4.setSecondName("Second Name");
            customer4.setSurname("Doe");

            Shelters shelters4 = new Shelters();
            shelters4.setAdress("Adress");
            shelters4.setId(1L);
            shelters4.setLocation("Location");
            shelters4.setName("Name");

            Pets pets4 = new Pets();
            pets4.setAge(1);
            pets4.setBreed("Breed");
            pets4.setCustomer(customer4);
            pets4.setDecisionDate(LocalDate.of(1970, 1, 1));
            pets4.setId(1L);
            pets4.setLimit_probation(1L);
            pets4.setName("Bella");
            pets4.setProbationStatus("Probation Status");
            pets4.setShelters(shelters4);
            pets4.setType_pets("Bella");

            Customer customer5 = new Customer();
            customer5.setAddress("42 Main St");
            customer5.setChatId(1L);
            customer5.setEmail("jane.doe@example.org");
            customer5.setId(1L);
            customer5.setName("Name");
            customer5.setPhone("6625550144");
            customer5.setSecondName("Second Name");
            customer5.setSurname("Doe");

            Shelters shelters5 = new Shelters();
            shelters5.setAdress("Adress");
            shelters5.setId(1L);
            shelters5.setLocation("Location");
            shelters5.setName("Name");

            Pets pets5 = new Pets();
            pets5.setAge(1);
            pets5.setBreed("Breed");
            pets5.setCustomer(customer5);
            pets5.setDecisionDate(LocalDate.of(1970, 1, 1));
            pets5.setId(1L);
            pets5.setLimit_probation(1L);
            pets5.setName("Bella");
            pets5.setProbationStatus("Probation Status");
            pets5.setShelters(shelters5);
            pets5.setType_pets("Bella");

            Report report2 = new Report();
            report2.setDate(LocalDate.of(1970, 1, 1).atStartOfDay());
            report2.setId(1L);
            report2.setPetReport("Bella");
            report2.setPets(pets5);

            PhotoPet photoPet2 = new PhotoPet();
            photoPet2.setFilePath("/directory/foo.txt");
            photoPet2.setFileSize(3L);
            photoPet2.setId(1L);
            photoPet2.setMediaType("Media Type");
            photoPet2.setPets(pets4);
            photoPet2.setReport(report2);
            Optional<PhotoPet> ofResult = Optional.of(photoPet2);

            // Моки для сервисов и репозиториев
            when(photoPetRepository.save(Mockito.<PhotoPet>any())).thenReturn(photoPet);
            when(photoPetRepository.findPhotoPetById(Mockito.<Long>any())).thenReturn(ofResult);

            // Вызываем метод uploadPhotoPet, передавая MockMultipartFile
            photoPetService.uploadPhotoPet(1L,
                    new MockMultipartFile("Name", new ByteArrayInputStream("AXAXAXAX".getBytes("UTF-8"))));

            // Проверяем, что статические методы Files были вызваны
            mockFiles.verify(() -> Files.createDirectories(Mockito.<Path>any(), isA(FileAttribute[].class)));
            mockFiles.verify(() -> Files.deleteIfExists(Mockito.<Path>any()));
            mockFiles.verify(() -> Files.newOutputStream(Mockito.<Path>any(), isA(OpenOption[].class)));

            // Проверяем, что методы сервисов и репозиториев были вызваны
            verify(photoPetRepository, atLeast(1)).save(Mockito.<PhotoPet>any());
            verify(photoPetRepository).findPhotoPetById(Mockito.<Long>any());
            verify(petsService).findPetById(Mockito.<Long>any());
        }
    }

    /**
     * Method under test: {@link PhotoPetService#findPhotoPet(Long)}
     */
    @Test
    void testFindPhotoPet() {
        Customer customer = new Customer();
        customer.setAddress("42 Main St");
        customer.setChatId(1L);
        customer.setEmail("jane.doe@example.org");
        customer.setId(1L);
        customer.setName("Name");
        customer.setPhone("6625550144");
        customer.setSecondName("Second Name");
        customer.setSurname("Doe");

        Shelters shelters = new Shelters();
        shelters.setAdress("Adress");
        shelters.setId(1L);
        shelters.setLocation("Location");
        shelters.setName("Name");

        Pets pets = new Pets();
        pets.setAge(1);
        pets.setBreed("Breed");
        pets.setCustomer(customer);
        pets.setDecisionDate(LocalDate.of(1970, 1, 1));
        pets.setId(1L);
        pets.setLimit_probation(1L);
        pets.setName("Bella");
        pets.setProbationStatus("Probation Status");
        pets.setShelters(shelters);
        pets.setType_pets("Bella");

        Customer customer2 = new Customer();
        customer2.setAddress("42 Main St");
        customer2.setChatId(1L);
        customer2.setEmail("jane.doe@example.org");
        customer2.setId(1L);
        customer2.setName("Name");
        customer2.setPhone("6625550144");
        customer2.setSecondName("Second Name");
        customer2.setSurname("Doe");

        Shelters shelters2 = new Shelters();
        shelters2.setAdress("Adress");
        shelters2.setId(1L);
        shelters2.setLocation("Location");
        shelters2.setName("Name");

        Pets pets2 = new Pets();
        pets2.setAge(1);
        pets2.setBreed("Breed");
        pets2.setCustomer(customer2);
        pets2.setDecisionDate(LocalDate.of(1970, 1, 1));
        pets2.setId(1L);
        pets2.setLimit_probation(1L);
        pets2.setName("Bella");
        pets2.setProbationStatus("Probation Status");
        pets2.setShelters(shelters2);
        pets2.setType_pets("Bella");

        Report report = new Report();
        report.setDate(LocalDate.of(1970, 1, 1).atStartOfDay());
        report.setId(1L);
        report.setPetReport("Bella");
        report.setPets(pets2);

        PhotoPet photoPet = new PhotoPet();
        photoPet.setFilePath("/directory/foo.txt");
        photoPet.setFileSize(3L);
        photoPet.setId(1L);
        photoPet.setMediaType("Media Type");
        photoPet.setPets(pets);
        photoPet.setReport(report);
        Optional<PhotoPet> ofResult = Optional.of(photoPet);
        when(photoPetRepository.findPhotoPetById(Mockito.<Long>any())).thenReturn(ofResult);
        PhotoPet actualFindPhotoPetResult = photoPetService.findPhotoPet(1L);
        verify(photoPetRepository).findPhotoPetById(Mockito.<Long>any());
        assertSame(photoPet, actualFindPhotoPetResult);
    }

    /**
     * Method under test: {@link PhotoPetService#savePhotoReport(PhotoPet)}
     */
    @Test
    void testSavePhotoReport() {

        // Создаем объекты для тестирования
        Customer customer = new Customer();
        customer.setAddress("42 Main St");
        customer.setChatId(1L);
        customer.setEmail("jane.doe@example.org");
        customer.setId(1L);
        customer.setName("Name");
        customer.setPhone("6625550144");
        customer.setSecondName("Second Name");
        customer.setSurname("Doe");

        Shelters shelters = new Shelters();
        shelters.setAdress("Adress");
        shelters.setId(1L);
        shelters.setLocation("Location");
        shelters.setName("Name");

        Pets pets = new Pets();
        pets.setAge(1);
        pets.setBreed("Breed");
        pets.setCustomer(customer);
        pets.setDecisionDate(LocalDate.of(1970, 1, 1));
        pets.setId(1L);
        pets.setLimit_probation(1L);
        pets.setName("Bella");
        pets.setProbationStatus("Probation Status");
        pets.setShelters(shelters);
        pets.setType_pets("Bella");

        Customer customer2 = new Customer();
        customer2.setAddress("42 Main St");
        customer2.setChatId(1L);
        customer2.setEmail("jane.doe@example.org");
        customer2.setId(1L);
        customer2.setName("Name");
        customer2.setPhone("6625550144");
        customer2.setSecondName("Second Name");
        customer2.setSurname("Doe");

        Shelters shelters2 = new Shelters();
        shelters2.setAdress("Adress");
        shelters2.setId(1L);
        shelters2.setLocation("Location");
        shelters2.setName("Name");

        Pets pets2 = new Pets();
        pets2.setAge(1);
        pets2.setBreed("Breed");
        pets2.setCustomer(customer2);
        pets2.setDecisionDate(LocalDate.of(1970, 1, 1));
        pets2.setId(1L);
        pets2.setLimit_probation(1L);
        pets2.setName("Bella");
        pets2.setProbationStatus("Probation Status");
        pets2.setShelters(shelters2);
        pets2.setType_pets("Bella");

        Report report = new Report();
        report.setDate(LocalDate.of(1970, 1, 1).atStartOfDay());
        report.setId(1L);
        report.setPetReport("Bella");
        report.setPets(pets2);

        // Создаем мок для метода findPhotoPetById репозитория
        PhotoPet photoPet = new PhotoPet();
        photoPet.setFilePath("/directory/foo.txt");
        photoPet.setFileSize(3L);
        photoPet.setId(1L);
        photoPet.setMediaType("Media Type");
        photoPet.setPets(pets);
        photoPet.setReport(report);
        when(photoPetRepository.save(Mockito.<PhotoPet>any())).thenReturn(photoPet);

        Customer customer3 = new Customer();
        customer3.setAddress("42 Main St");
        customer3.setChatId(1L);
        customer3.setEmail("jane.doe@example.org");
        customer3.setId(1L);
        customer3.setName("Name");
        customer3.setPhone("6625550144");
        customer3.setSecondName("Second Name");
        customer3.setSurname("Doe");

        Shelters shelters3 = new Shelters();
        shelters3.setAdress("Adress");
        shelters3.setId(1L);
        shelters3.setLocation("Location");
        shelters3.setName("Name");

        Pets pets3 = new Pets();
        pets3.setAge(1);
        pets3.setBreed("Breed");
        pets3.setCustomer(customer3);
        pets3.setDecisionDate(LocalDate.of(1970, 1, 1));
        pets3.setId(1L);
        pets3.setLimit_probation(1L);
        pets3.setName("Bella");
        pets3.setProbationStatus("Probation Status");
        pets3.setShelters(shelters3);
        pets3.setType_pets("Bella");

        Customer customer4 = new Customer();
        customer4.setAddress("42 Main St");
        customer4.setChatId(1L);
        customer4.setEmail("jane.doe@example.org");
        customer4.setId(1L);
        customer4.setName("Name");
        customer4.setPhone("6625550144");
        customer4.setSecondName("Second Name");
        customer4.setSurname("Doe");

        Shelters shelters4 = new Shelters();
        shelters4.setAdress("Adress");
        shelters4.setId(1L);
        shelters4.setLocation("Location");
        shelters4.setName("Name");

        Pets pets4 = new Pets();
        pets4.setAge(1);
        pets4.setBreed("Breed");
        pets4.setCustomer(customer4);
        pets4.setDecisionDate(LocalDate.of(1970, 1, 1));
        pets4.setId(1L);
        pets4.setLimit_probation(1L);
        pets4.setName("Bella");
        pets4.setProbationStatus("Probation Status");
        pets4.setShelters(shelters4);
        pets4.setType_pets("Bella");

        Report report2 = new Report();
        report2.setDate(LocalDate.of(1970, 1, 1).atStartOfDay());
        report2.setId(1L);
        report2.setPetReport("Bella");
        report2.setPets(pets4);

        PhotoPet photoPet2 = new PhotoPet();
        photoPet2.setFilePath("/directory/foo.txt");
        photoPet2.setFileSize(3L);
        photoPet2.setId(1L);
        photoPet2.setMediaType("Media Type");
        photoPet2.setPets(pets3);
        photoPet2.setReport(report2);

        // Вызываем метод findPhotoPet и сохраняем результат
        PhotoPet actualSavePhotoReportResult = photoPetService.savePhotoReport(photoPet2);

        // Проверяем, что метод findPhotoPetById был вызван с правильным аргументом
        verify(photoPetRepository).save(Mockito.<PhotoPet>any());

        // Проверяем, что результат метода совпадает с ожидаемым объектом PhotoPet
        assertSame(photoPet, actualSavePhotoReportResult);
    }
}

