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
        ArrayList<Report> reportList = new ArrayList<>();
        when(reportRepository.findAll()).thenReturn(reportList);
        List<Report> actualFindAllReportsResult = reportService.findAllReports();
        verify(reportRepository).findAll();
        assertTrue(actualFindAllReportsResult.isEmpty());
        assertSame(reportList, actualFindAllReportsResult);
    }

    /**
     * Method under test: {@link ReportService#findAllReports()}
     */
    @Test
    void testFindAllReports2() {
        when(reportRepository.findAll()).thenThrow(new IllegalArgumentException("foo"));
        assertThrows(IllegalArgumentException.class, () -> reportService.findAllReports());
        verify(reportRepository).findAll();
    }

    /**
     * Method under test: {@link ReportService#findReportById(long)}
     */
    @Test
    void testFindReportById() {
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
        Optional<Report> ofResult = Optional.of(report);
        when(reportRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);
        Report actualFindReportByIdResult = reportService.findReportById(1L);
        verify(reportRepository).findById(Mockito.<Long>any());
        assertSame(report, actualFindReportByIdResult);
    }

    /**
     * Method under test: {@link ReportService#findReportById(long)}
     */
    @Test
    void testFindReportById2() {
        when(reportRepository.findById(Mockito.<Long>any())).thenThrow(new IllegalArgumentException("foo"));
        assertThrows(IllegalArgumentException.class, () -> reportService.findReportById(1L));
        verify(reportRepository).findById(Mockito.<Long>any());
    }

    /**
     * Method under test: {@link ReportService#deleteReport(long)}
     */
    @Test
    void testDeleteReport() {
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
        Optional<Report> ofResult = Optional.of(report);
        doNothing().when(reportRepository).delete(Mockito.<Report>any());
        when(reportRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);
        Report actualDeleteReportResult = reportService.deleteReport(1L);
        verify(reportRepository).delete(Mockito.<Report>any());
        verify(reportRepository).findById(Mockito.<Long>any());
        assertSame(report, actualDeleteReportResult);
    }

    /**
     * Method under test: {@link ReportService#deleteReport(long)}
     */
    @Test
    void testDeleteReport2() {
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
        Optional<Report> ofResult = Optional.of(report);
        doThrow(new IllegalArgumentException("foo")).when(reportRepository).delete(Mockito.<Report>any());
        when(reportRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);
        assertThrows(IllegalArgumentException.class, () -> reportService.deleteReport(1L));
        verify(reportRepository).delete(Mockito.<Report>any());
        verify(reportRepository).findById(Mockito.<Long>any());
    }

    /**
     * Method under test: {@link ReportService#deleteReport(long)}
     */
    @Test
    void testDeleteReport3() {
        Optional<Report> emptyResult = Optional.empty();
        when(reportRepository.findById(Mockito.<Long>any())).thenReturn(emptyResult);
        Report actualDeleteReportResult = reportService.deleteReport(1L);
        verify(reportRepository).findById(Mockito.<Long>any());
        assertNull(actualDeleteReportResult);
    }

    /**
     * Method under test: {@link ReportService#createReport(Report)}
     */
    @Test
    void testCreateReport() {
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
        Report actualCreateReportResult = reportService.createReport(report2);
        verify(reportRepository).save(Mockito.<Report>any());
        assertSame(report, actualCreateReportResult);
    }

    /**
     * Method under test: {@link ReportService#updateReport(Report)}
     */
    @Test
    void testUpdateReport() {
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
        Report actualUpdateReportResult = reportService.updateReport(report2);
        verify(reportRepository).save(Mockito.<Report>any());
        assertSame(report, actualUpdateReportResult);
    }

    /**
     * Method under test: {@link ReportService#findTodayCompletedReportsByPetId(Long)}
     */
    @Test
    void testFindTodayCompletedReportsByPetId() {
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
        when(reportRepository.findFirstByPetsIdAndPetReportNotNullAndDateBetween(Mockito.<Long>any(),
                Mockito.<LocalDateTime>any(), Mockito.<LocalDateTime>any())).thenReturn(report);
        Report actualFindTodayCompletedReportsByPetIdResult = reportService.findTodayCompletedReportsByPetId(1L);
        verify(reportRepository).findFirstByPetsIdAndPetReportNotNullAndDateBetween(Mockito.<Long>any(),
                Mockito.<LocalDateTime>any(), Mockito.<LocalDateTime>any());
        assertSame(report, actualFindTodayCompletedReportsByPetIdResult);
    }

    /**
     * Method under test: {@link ReportService#findTodayCompletedReportsByPetId(Long)}
     */
    @Test
    void testFindTodayCompletedReportsByPetId2() {
        when(reportRepository.findFirstByPetsIdAndPetReportNotNullAndDateBetween(Mockito.<Long>any(),
                Mockito.<LocalDateTime>any(), Mockito.<LocalDateTime>any())).thenThrow(new IllegalArgumentException("foo"));
        assertThrows(IllegalArgumentException.class, () -> reportService.findTodayCompletedReportsByPetId(1L));
        verify(reportRepository).findFirstByPetsIdAndPetReportNotNullAndDateBetween(Mockito.<Long>any(),
                Mockito.<LocalDateTime>any(), Mockito.<LocalDateTime>any());
    }

    /**
     * Method under test: {@link ReportService#findTodayReportByPetId(Long)}
     */
    @Test
    void testFindTodayReportByPetId() {
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
        when(reportRepository.findReportByPetsIdAndDateBetween(Mockito.<Long>any(), Mockito.<LocalDateTime>any(),
                Mockito.<LocalDateTime>any())).thenReturn(report);
        Report actualFindTodayReportByPetIdResult = reportService.findTodayReportByPetId(1L);
        verify(reportRepository).findReportByPetsIdAndDateBetween(Mockito.<Long>any(), Mockito.<LocalDateTime>any(),
                Mockito.<LocalDateTime>any());
        assertSame(report, actualFindTodayReportByPetIdResult);
    }

    /**
     * Method under test: {@link ReportService#findTodayReportByPetId(Long)}
     */
    @Test
    void testFindTodayReportByPetId2() {
        when(reportRepository.findReportByPetsIdAndDateBetween(Mockito.<Long>any(), Mockito.<LocalDateTime>any(),
                Mockito.<LocalDateTime>any())).thenThrow(new IllegalArgumentException("foo"));
        assertThrows(IllegalArgumentException.class, () -> reportService.findTodayReportByPetId(1L));
        verify(reportRepository).findReportByPetsIdAndDateBetween(Mockito.<Long>any(), Mockito.<LocalDateTime>any(),
                Mockito.<LocalDateTime>any());
    }

    /**
     * Method under test: {@link ReportService#findPetsWithoutTodayReport(Customer)}
     */
    @Test
    void testFindPetsWithoutTodayReport() {
        when(petsRepository.findPetsByCustomer(Mockito.<Customer>any())).thenReturn(new ArrayList<>());

        Customer customer = new Customer();
        customer.setAddress("42 Main St");
        customer.setChatId(1L);
        customer.setEmail("jane.doe@example.org");
        customer.setId(1L);
        customer.setName("Name");
        customer.setPhone("6625550144");
        customer.setSecondName("Second Name");
        customer.setSurname("Doe");
        List<Pets> actualFindPetsWithoutTodayReportResult = reportService.findPetsWithoutTodayReport(customer);
        verify(petsRepository).findPetsByCustomer(Mockito.<Customer>any());
        assertTrue(actualFindPetsWithoutTodayReportResult.isEmpty());
    }

    /**
     * Method under test: {@link ReportService#findPetsWithoutTodayReport(Customer)}
     */
    @Test
    void testFindPetsWithoutTodayReport2() {
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
        when(reportRepository.findFirstByPetsIdAndPetReportNotNullAndDateBetween(Mockito.<Long>any(),
                Mockito.<LocalDateTime>any(), Mockito.<LocalDateTime>any())).thenReturn(report);

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
        when(petsRepository.findPetsByCustomer(Mockito.<Customer>any())).thenReturn(petsList);

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
        when(petsRepository.findPetsByCustomerNotNull()).thenReturn(new ArrayList<>());
        List<Customer> actualFindCustomersWithoutTodayReportResult = reportService.findCustomersWithoutTodayReport();
        verify(petsRepository).findPetsByCustomerNotNull();
        assertTrue(actualFindCustomersWithoutTodayReportResult.isEmpty());
    }

    /**
     * Method under test: {@link ReportService#findCustomersWithoutTodayReport()}
     */
    @Test
    void testFindCustomersWithoutTodayReport2() {
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
        when(reportRepository.findFirstByPetsIdAndPetReportNotNullAndDateBetween(Mockito.<Long>any(),
                Mockito.<LocalDateTime>any(), Mockito.<LocalDateTime>any())).thenReturn(report);

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
        when(petsRepository.findPetsByCustomerNotNull()).thenReturn(petsList);
        List<Customer> actualFindCustomersWithoutTodayReportResult = reportService.findCustomersWithoutTodayReport();
        verify(petsRepository).findPetsByCustomerNotNull();
        verify(reportRepository).findFirstByPetsIdAndPetReportNotNullAndDateBetween(Mockito.<Long>any(),
                Mockito.<LocalDateTime>any(), Mockito.<LocalDateTime>any());
        assertTrue(actualFindCustomersWithoutTodayReportResult.isEmpty());

    }
}

