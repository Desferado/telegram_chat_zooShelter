package pro.sky.telegram_chat_zooShelter.services;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import pro.sky.telegram_chat_zooShelter.model.Customer;
import pro.sky.telegram_chat_zooShelter.model.Pets;
import pro.sky.telegram_chat_zooShelter.model.Report;
import pro.sky.telegram_chat_zooShelter.model.Shelters;
import pro.sky.telegram_chat_zooShelter.repository.PetsRepository;
import pro.sky.telegram_chat_zooShelter.repository.ReportRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ContextConfiguration(classes = {ReportService.class})
@ExtendWith(SpringExtension.class)
class ReportServiceTest {
    @MockBean
    private PetsRepository petsRepository;

    @MockBean
    private PetsService petsService;

    @MockBean
    private ReportRepository reportRepository;

    @Autowired
    private ReportService reportService;

    /**
     * Method under test: {@link ReportService#findAllReports()}
     */
    @Test
    void testFindAllReports() {
        // Создаем пустой список отчетов для тестирования
        ArrayList<Report> reportList = new ArrayList<>();

        // Создаем мок для метода findAll репозитория
        when(reportRepository.findAll()).thenReturn(reportList);

        // Вызываем метод findAllReports и сохраняем результат
        List<Report> actualFindAllReportsResult = reportService.findAllReports();

        // Проверяем, что метод findAll был вызван
        verify(reportRepository).findAll();

        // Проверяем, что результат метода пустой и совпадает с ожидаемым пустым списком
        assertTrue(actualFindAllReportsResult.isEmpty());
        assertSame(reportList, actualFindAllReportsResult);
    }


    /**
     * Method under test: {@link ReportService#findAllReports()}
     */
    @Test
    void testFindAllReports2() {
        // Создаем мок для метода findAll репозитория, который бросит исключение
        when(reportRepository.findAll()).thenThrow(new IllegalArgumentException("foo"));

        // Вызываем findAllReports и ожидаем исключение
        assertThrows(IllegalArgumentException.class, () -> reportService.findAllReports());

        // Проверяем, что метод findAll был вызван
        verify(reportRepository).findAll();
    }


    /**
     * Method under test: {@link ReportService#findReportById(long)}
     */
    @Test
    void testFindReportById() {

        // Создаем объекты для тестовых данных, включая отчет и питомца
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

        Report report = new Report();
        report.setDate(LocalDate.of(1970, 1, 1).atStartOfDay());
        report.setId(1L);
        report.setPetReport("Bella");
        report.setPets(pets);

        // Создаем мок для метода findById репозитория, который возвращает заданный отчет
        Optional<Report> ofResult = Optional.of(report);
        when(reportRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);

        // Вызываем findReportById с определенным идентификатором и ожидаем получить этот отчет
        Report actualFindReportByIdResult = reportService.findReportById(1L);

        // Проверяем, что метод findById был вызван с правильным идентификатором
        verify(reportRepository).findById(Mockito.<Long>any());

        // Проверяем, что вернувшийся отчет совпадает с ожидаемым отчетом
        assertSame(report, actualFindReportByIdResult);
    }

    /**
     * Method under test: {@link ReportService#findReportById(long)}
     */
    @Test
    void testFindReportById2() {
        // Создаем мок для метода findById репозитория, который выбрасывает исключение
        when(reportRepository.findById(Mockito.<Long>any())).thenThrow(new IllegalArgumentException("foo"));

        // Проверяем, что при вызове findReportById с определенным идентификатором выбрасывается исключение IllegalArgumentException
        assertThrows(IllegalArgumentException.class, () -> reportService.findReportById(1L));

        // Проверяем, что метод findById был вызван с правильным идентификатором
        verify(reportRepository).findById(Mockito.<Long>any());
    }


    /**
     * Method under test: {@link ReportService#deleteReport(long)}
     */
    @Test
    void testDeleteReport() {

        // Создаем объекты Customer, Shelters, Pets и Report
        // и устанавливаем им значения
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

        Report report = new Report();
        report.setDate(LocalDate.of(1970, 1, 1).atStartOfDay());
        report.setId(1L);
        report.setPetReport("Bella");
        report.setPets(pets);

        // Создаем мок Optional с результатом
        Optional<Report> ofResult = Optional.of(report);

        // Создаем мок для метода delete репозитория, который ничего не делает
        doNothing().when(reportRepository).delete(Mockito.<Report>any());

        // Создаем мок для метода findById репозитория, который возвращает ofResult
        when(reportRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);

        // Вызываем метод deleteReport и сохраняем его результат
        Report actualDeleteReportResult = reportService.deleteReport(1L);

        // Проверяем, что метод delete был вызван с правильным объектом Report
        verify(reportRepository).delete(Mockito.<Report>any());

        // Проверяем, что метод findById был вызван с правильным идентификатором
        verify(reportRepository).findById(Mockito.<Long>any());

        // Проверяем, что возвращенный объект Report совпадает с ожидаемым
        assertSame(report, actualDeleteReportResult);
    }

    /**
     * Method under test: {@link ReportService#deleteReport(long)}
     */
    @Test
    void testDeleteReport2() {

        // Создаем объекты Customer, Shelters, Pets и Report, как обычно
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

        Report report = new Report();
        report.setDate(LocalDate.of(1970, 1, 1).atStartOfDay());
        report.setId(1L);
        report.setPetReport("Bella");
        report.setPets(pets);

        // Создаем мок Optional с результатом, содержащим созданный Report
        Optional<Report> ofResult = Optional.of(report);

        // Создаем мок для метода delete репозитория, который бросает исключение IllegalArgumentException
        doThrow(new IllegalArgumentException("foo")).when(reportRepository).delete(Mockito.<Report>any());

        // Создаем мок для метода findById репозитория, который возвращает ofResult
        when(reportRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);

        // При вызове метода deleteReport ожидаем, что будет брошено исключение IllegalArgumentException
        assertThrows(IllegalArgumentException.class, () -> reportService.deleteReport(1L));

        // Проверяем, что метод delete был вызван с объектом Report
        verify(reportRepository).delete(Mockito.<Report>any());

        // Проверяем, что метод findById был вызван с правильным идентификатором
        verify(reportRepository).findById(Mockito.<Long>any());
    }

    /**
     * Method under test: {@link ReportService#deleteReport(long)}
     */
    @Test
    void testDeleteReport3() {
        // Создаем мок Optional с пустым результатом
        Optional<Report> emptyResult = Optional.empty();

        // Создаем мок для метода findById репозитория, который возвращает emptyResult
        when(reportRepository.findById(Mockito.<Long>any())).thenReturn(emptyResult);

        // Вызываем метод deleteReport и сохраняем его результат
        Report actualDeleteReportResult = reportService.deleteReport(1L);

        // Проверяем, что метод findById был вызван с правильным идентификатором
        verify(reportRepository).findById(Mockito.<Long>any());

        // Проверяем, что результат равен null, так как отчет не был найден
        assertNull(actualDeleteReportResult);
    }


    /**
     * Method under test: {@link ReportService#createReport(Report)}
     */
    @Test
    void testCreateReport() {

        // Создаем фиктивные объекты для тестирования, такие как 'customer', 'shelters', 'pets', и 'report'.
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

        Report report = new Report();
        report.setDate(LocalDate.of(1970, 1, 1).atStartOfDay());
        report.setId(1L);
        report.setPetReport("Bella");
        report.setPets(pets);

        // Настроим моки и ожидаемое поведение репозитория reportRepository.
        when(reportRepository.save(Mockito.<Report>any())).thenReturn(report);

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

        Report report2 = new Report();
        report2.setDate(LocalDate.of(1970, 1, 1).atStartOfDay());
        report2.setId(1L);
        report2.setPetReport("Bella");
        report2.setPets(pets2);

        // Вызываем метод, который мы тестируем, и сохраняем его результат.
        Report actualCreateReportResult = reportService.createReport(report2);

        // Проверяем, что метод reportRepository.save() был вызван, чтобы сохранить объект report.
        verify(reportRepository).save(Mockito.<Report>any());

        // Убеждаемся, что метод возвращает ожидаемый результат.
        assertSame(report, actualCreateReportResult);
    }

    /**
     * Method under test: {@link ReportService#updateReport(Report)}
     */
    @Test
    void testUpdateReport() {

        // Создаем фиктивные объекты для тестирования, такие как 'customer', 'shelters', 'pets', и 'report'.
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

        Report report = new Report();
        report.setDate(LocalDate.of(1970, 1, 1).atStartOfDay());
        report.setId(1L);
        report.setPetReport("Bella");
        report.setPets(pets);

        // Настроим моки и ожидаемое поведение репозитория reportRepository.
        when(reportRepository.save(Mockito.<Report>any())).thenReturn(report);

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

        Report report2 = new Report();
        report2.setDate(LocalDate.of(1970, 1, 1).atStartOfDay());
        report2.setId(1L);
        report2.setPetReport("Bella");
        report2.setPets(pets2);

        // Вызываем метод, который мы тестируем (updateReport), и сохраняем его результат.
        Report actualUpdateReportResult = reportService.updateReport(report2);

        // Проверяем, что метод reportRepository.save() был вызван, чтобы сохранить объект report.
        verify(reportRepository).save(Mockito.<Report>any());

        // Убеждаемся, что метод возвращает ожидаемый результат (report).
        assertSame(report, actualUpdateReportResult);
    }

    /**
     * Method under test: {@link ReportService#findTodayCompletedReportsByPetId(Long)}
     */
    @Test
    void testFindTodayCompletedReportsByPetId() {

        // Создаем фиктивные объекты для тестирования, такие как 'customer', 'shelters', 'pets', и 'report'.
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

        Report report = new Report();
        report.setDate(LocalDate.of(1970, 1, 1).atStartOfDay());
        report.setId(1L);
        report.setPetReport("Bella");
        report.setPets(pets);

        // Настроим моки и ожидаемое поведение репозитория reportRepository.
        when(reportRepository.findFirstByPetsIdAndPetReportNotNullAndDateBetween(Mockito.<Long>any(),
                Mockito.<LocalDateTime>any(), Mockito.<LocalDateTime>any())).thenReturn(report);

        // Вызываем метод, который мы тестируем (findTodayCompletedReportsByPetId), и сохраняем его результат.
        Report actualFindTodayCompletedReportsByPetIdResult = reportService.findTodayCompletedReportsByPetId(1L);

        // Проверяем, что метод reportRepository.findFirstByPetsIdAndPetReportNotNullAndDateBetween() был вызван с ожидаемыми аргументами.
        verify(reportRepository).findFirstByPetsIdAndPetReportNotNullAndDateBetween(Mockito.<Long>any(),
                Mockito.<LocalDateTime>any(), Mockito.<LocalDateTime>any());

        // Убеждаемся, что метод возвращает ожидаемый результат (report).
        assertSame(report, actualFindTodayCompletedReportsByPetIdResult);
    }

    /**
     * Method under test: {@link ReportService#findTodayCompletedReportsByPetId(Long)}
     */
    @Test
    void testFindTodayCompletedReportsByPetId2() {
        // Мокируем поведение репозитория reportRepository, чтобы он бросал исключение IllegalArgumentException.
        when(reportRepository.findFirstByPetsIdAndPetReportNotNullAndDateBetween(Mockito.<Long>any(),
                Mockito.<LocalDateTime>any(), Mockito.<LocalDateTime>any())).thenThrow(new IllegalArgumentException("foo"));

        // Вызываем метод, который мы тестируем (findTodayCompletedReportsByPetId), и ожидаем, что он бросит исключение IllegalArgumentException.
        assertThrows(IllegalArgumentException.class, () -> reportService.findTodayCompletedReportsByPetId(1L));

        // Проверяем, что метод reportRepository.findFirstByPetsIdAndPetReportNotNullAndDateBetween() был вызван с ожидаемыми аргументами.
        verify(reportRepository).findFirstByPetsIdAndPetReportNotNullAndDateBetween(Mockito.<Long>any(),
                Mockito.<LocalDateTime>any(), Mockito.<LocalDateTime>any());
    }


    /**
     * Method under test: {@link ReportService#findTodayReportByPetId(Long)}
     */
    @Test
    void testFindTodayReportByPetId() {

        // Создаем необходимые объекты данных (customer, shelters, pets, report).
        // Важно, что report имеет заданную дату в прошлом.
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

        Report report = new Report();
        report.setDate(LocalDate.of(1970, 1, 1).atStartOfDay());
        report.setId(1L);
        report.setPetReport("Bella");
        report.setPets(pets);

        // Мокируем поведение репозитория reportRepository, чтобы он вернул созданный объект report.
        when(reportRepository.findReportByPetsIdAndDateBetween(Mockito.<Long>any(), Mockito.<LocalDateTime>any(),
                Mockito.<LocalDateTime>any())).thenReturn(report);

        // Вызываем метод, который мы тестируем (findTodayReportByPetId), и ожидаем, что он вернет объект report.
        Report actualFindTodayReportByPetIdResult = reportService.findTodayReportByPetId(1L);

        // Проверяем, что метод reportRepository.findReportByPetsIdAndDateBetween() был вызван с ожидаемыми аргументами.
        verify(reportRepository).findReportByPetsIdAndDateBetween(Mockito.<Long>any(), Mockito.<LocalDateTime>any(),
                Mockito.<LocalDateTime>any());

        // Проверяем, что метод вернул ожидаемый объект report.
        assertSame(report, actualFindTodayReportByPetIdResult);
    }

    /**
     * Method under test: {@link ReportService#findTodayReportByPetId(Long)}
     */
    @Test
    void testFindTodayReportByPetId2() {
        // Мокируем поведение репозитория reportRepository, чтобы он выбросил исключение IllegalArgumentException с сообщением "foo".
        when(reportRepository.findReportByPetsIdAndDateBetween(Mockito.<Long>any(), Mockito.<LocalDateTime>any(),
                Mockito.<LocalDateTime>any())).thenThrow(new IllegalArgumentException("foo"));

        // Проверяем, что при вызове метода findTodayReportByPetId с аргументом 1L, ожидается исключение IllegalArgumentException с сообщением "foo".
        assertThrows(IllegalArgumentException.class, () -> reportService.findTodayReportByPetId(1L));

        // Проверяем, что метод reportRepository.findReportByPetsIdAndDateBetween() был вызван с ожидаемыми аргументами.
        verify(reportRepository).findReportByPetsIdAndDateBetween(Mockito.<Long>any(), Mockito.<LocalDateTime>any(),
                Mockito.<LocalDateTime>any());
    }


    /**
     * Method under test: {@link ReportService#findPetsWithoutTodayReport(Customer)}
     */
    @Test
    void testFindPetsWithoutTodayReport() {
        // Мокируем поведение репозитория petsRepository, чтобы он вернул пустой список питомцев при вызове метода findPetsByCustomer с любым аргументом типа Customer.
        when(petsRepository.findPetsByCustomer(Mockito.<Customer>any())).thenReturn(new ArrayList<>());

        // Создаем объект Customer с заданными свойствами.
        Customer customer = new Customer();
        customer.setAddress("42 Main St");
        customer.setChatId(1L);
        customer.setEmail("jane.doe@example.org");
        customer.setId(1L);
        customer.setName("Name");
        customer.setPhone("6625550144");
        customer.setSecondName("Second Name");
        customer.setSurname("Doe");

        // Вызываем метод findPetsWithoutTodayReport с созданным объектом customer.
        List<Pets> actualFindPetsWithoutTodayReportResult = reportService.findPetsWithoutTodayReport(customer);

        // Проверяем, что метод petsRepository.findPetsByCustomer был вызван с ожидаемым аргументом, и что результат является пустым списком.
        verify(petsRepository).findPetsByCustomer(Mockito.<Customer>any());
        assertTrue(actualFindPetsWithoutTodayReportResult.isEmpty());
    }


    /**
     * Method under test: {@link ReportService#findPetsWithoutTodayReport(Customer)}
     */
    @Test
    void testFindPetsWithoutTodayReport2() {
        // Создаем объект Customer с заданными свойствами.
        Customer customer = new Customer();
        customer.setAddress("42 Main St");
        customer.setChatId(1L);
        customer.setEmail("jane.doe@example.org");
        customer.setId(1L);
        customer.setName("Name");
        customer.setPhone("6625550144");
        customer.setSecondName("Second Name");
        customer.setSurname("Doe");

        // Создаем объект Shelters с заданными свойствами.
        Shelters shelters = new Shelters();
        shelters.setAdress("Adress");
        shelters.setId(1L);
        shelters.setLocation("Location");
        shelters.setName("Name");

        // Создаем объект Pets с заданными свойствами и привязываем его к customer и shelters.
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

        // Создаем объект Report с заданными свойствами и привязываем его к pets.
        Report report = new Report();
        report.setDate(LocalDate.of(1970, 1, 1).atStartOfDay());
        report.setId(1L);
        report.setPetReport("Bella");
        report.setPets(pets);

        // Настраиваем мокирование метода findFirstByPetsIdAndPetReportNotNullAndDateBetween так, чтобы он возвращал созданный объект report при вызове с любыми аргументами.
        when(reportRepository.findFirstByPetsIdAndPetReportNotNullAndDateBetween(Mockito.<Long>any(),
                Mockito.<LocalDateTime>any(), Mockito.<LocalDateTime>any())).thenReturn(report);

        // Создаем второго customer, shelters и pets, и добавляем pets в список petsList.
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

        ArrayList<Pets> petsList = new ArrayList<>();
        petsList.add(pets2);

        // Настраиваем мокирование метода findPetsByCustomer так, чтобы он возвращал список petsList при вызове с любым аргументом типа Customer.
        when(petsRepository.findPetsByCustomer(Mockito.<Customer>any())).thenReturn(petsList);

        // Создаем третьего customer и вызываем метод findPetsWithoutTodayReport с этим объектом.
        Customer customer3 = new Customer();
        customer3.setAddress("42 Main St");
        customer3.setChatId(1L);
        customer3.setEmail("jane.doe@example.org");
        customer3.setId(1L);
        customer3.setName("Name");
        customer3.setPhone("6625550144");
        customer3.setSecondName("Second Name");
        customer3.setSurname("Doe");

        List<Pets> actualFindPetsWithoutTodayReportResult = reportService.findPetsWithoutTodayReport(customer3);

        // Проверяем, что методы petsRepository.findPetsByCustomer и reportRepository.findFirstByPetsIdAndPetReportNotNullAndDateBetween
        // были вызваны с ожидаемыми аргументами и что результат является пустым списком.
        verify(petsRepository).findPetsByCustomer(Mockito.<Customer>any());
        verify(reportRepository).findFirstByPetsIdAndPetReportNotNullAndDateBetween(Mockito.<Long>any(),
                Mockito.<LocalDateTime>any(), Mockito.<LocalDateTime>any());
        assertTrue(actualFindPetsWithoutTodayReportResult.isEmpty());
    }


    /**
     * Method under test: {@link ReportService#findCustomersWithoutTodayReport()}
     */
    @Test
    void testFindCustomersWithoutTodayReport() {
        // Настраиваем мокирование метода findPetsByCustomerNotNull так, чтобы он возвращал пустой список при вызове.
        when(petsRepository.findPetsByCustomerNotNull()).thenReturn(new ArrayList<>());

        // Вызываем метод findCustomersWithoutTodayReport и сохраняем его результат в actualFindCustomersWithoutTodayReportResult.
        List<Customer> actualFindCustomersWithoutTodayReportResult = reportService.findCustomersWithoutTodayReport();

        // Проверяем, что метод petsRepository.findPetsByCustomerNotNull был вызван и результат является пустым списком.
        verify(petsRepository).findPetsByCustomerNotNull();
        assertTrue(actualFindCustomersWithoutTodayReportResult.isEmpty());
    }


    /**
     * Method under test: {@link ReportService#findCustomersWithoutTodayReport()}
     */
    @Test
    void testFindCustomersWithoutTodayReport2() {
        // Создаем первого заказчика (customer) и его питомца (pets).
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

        // Создаем отчет (report) для питомца (pets) и настраиваем мокирование метода findFirstByPetsIdAndPetReportNotNullAndDateBetween
        // так, чтобы он возвращал этот отчет.
        Report report = new Report();
        report.setDate(LocalDate.of(1970, 1, 1).atStartOfDay());
        report.setId(1L);
        report.setPetReport("Bella");
        report.setPets(pets);
        when(reportRepository.findFirstByPetsIdAndPetReportNotNullAndDateBetween(Mockito.<Long>any(),
                Mockito.<LocalDateTime>any(), Mockito.<LocalDateTime>any())).thenReturn(report);

        // Создаем второго заказчика (customer2) и его питомца (pets2).
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

        // Создаем список питомцев (petsList) и добавляем в него питомца (pets2).
        ArrayList<Pets> petsList = new ArrayList<>();
        petsList.add(pets2);

        // Настраиваем мокирование метода findPetsByCustomerNotNull так, чтобы он возвращал созданный список питомцев (petsList).
        when(petsRepository.findPetsByCustomerNotNull()).thenReturn(petsList);

        // Вызываем метод findCustomersWithoutTodayReport и сохраняем его результат в actualFindCustomersWithoutTodayReportResult.
        List<Customer> actualFindCustomersWithoutTodayReportResult = reportService.findCustomersWithoutTodayReport();

        // Проверяем, что методы petsRepository.findPetsByCustomerNotNull и reportRepository.findFirstByPetsIdAndPetReportNotNullAndDateBetween
        // были вызваны и результат является пустым списком.
        verify(petsRepository).findPetsByCustomerNotNull();
        verify(reportRepository).findFirstByPetsIdAndPetReportNotNullAndDateBetween(Mockito.<Long>any(),
                Mockito.<LocalDateTime>any(), Mockito.<LocalDateTime>any());
        assertTrue(actualFindCustomersWithoutTodayReportResult.isEmpty());
    }

}

