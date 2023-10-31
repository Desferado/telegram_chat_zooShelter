package pro.sky.telegram_chat_zooShelter.services;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import pro.sky.telegram_chat_zooShelter.model.Customer;
import pro.sky.telegram_chat_zooShelter.model.Pets;
import pro.sky.telegram_chat_zooShelter.model.PhotoPet;
import pro.sky.telegram_chat_zooShelter.model.Report;
import pro.sky.telegram_chat_zooShelter.repository.PetsRepository;
import pro.sky.telegram_chat_zooShelter.repository.ReportRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;


@Service
public class ReportService {
    private final static Logger LOGGER = LoggerFactory.getLogger(ReportService.class);

    private final ReportRepository reportRepository;
    private final PetsService petService;
    private final PetsRepository petsRepository;
    private final PhotoPetService photoPetService;

    public ReportService(ReportRepository reportRepository, PetsService petService, PetsRepository petsRepository, PhotoPetService photoPetService) {
        this.petService = petService;
        this.reportRepository = reportRepository;
        this.petsRepository = petsRepository;
        this.photoPetService = photoPetService;
    }

    /**
     * Search for all reports from the database. (Table - Report)
     * The repository method is used{@link JpaRepository#findAll()}
     *
     * @return - found the reports
     */
    public List<Report> findAllReports() {
        return reportRepository.findAll();
    }

    /**
     * Search for a report by its ID in the database. (Table - Report)
     * The repository method is used{@link JpaRepository#findById(Object)}
     *
     * @param id - ID of the report we are looking for.
     * @return - found the report
     */
    public Report findReportById(long id) {
        return reportRepository.findById(id).orElse(null);
    }

    /**
     * Deleting  for a report by its ID in the database. (Table - Report)
     * The repository method is used{@link JpaRepository#deleteById(Object)}
     *
     * @param id - ID of the report we want to delete.
     */

    public Report deleteReport(long id) {
        Report report = findReportById(id);
        if (report != null) {
            reportRepository.delete(report);
        }
        return report;
    }

    /**
     * Create a report and add it to the database. (Table - Report)
     * The repository method is used{@link JpaRepository#save(Object)}
     *
     * @param report - The entity of the report we want to create.
     * @return - created the report
     */
    public Report createReport(Report report) {
        if (report == null) {
            throw new IllegalArgumentException("Отчет не может быть пустым.");
        }

        try {
            return reportRepository.save(report);
        } catch (DataAccessException e) {

            throw new RuntimeException("Произошла ошибка при сохранении отчета.", e);
        }
    }


    /**
     * Update an existing report in the database.
     * The repository method is used{@link JpaRepository#save(Object)
     *
     * @param report - ID of the volunteer we want to update.
     * @return - updated a report
     */
    public Report updateReport(Report report) {
        return reportRepository.save(report);
    }

    /**
     * Метод ищет сегодняшний ЗАВЕРШЕННЫЙ отчет по id питомца
     *
     * @param petsId id питомца
     * @return найденный отчет
     */
    public Report findTodayCompletedReportsByPetId(Long petsId) {
        LocalDate localDate = LocalDate.now();
        LocalDateTime startTime = localDate.atStartOfDay();
        LocalDateTime finishTime = LocalTime.MAX.atDate(localDate);
        return reportRepository.findFirstByPetsIdAndPetReportNotNullAndDateBetween(petsId, startTime, finishTime);
    }

    /**
     * Метод ищет сегодняшние отчеты по id питомца
     *
     * @param petsId id питомца
     * @return найденный отчет
     */
    public Report findTodayReportByPetId(Long petsId) {
        LocalDate localDate = LocalDate.now();
        LocalDateTime startTime = localDate.atStartOfDay();
        LocalDateTime finishTime = LocalTime.MAX.atDate(localDate);
        return reportRepository.findReportByPetsIdAndDateBetween(petsId, startTime, finishTime);
    }

    /**
     * Метод ищет питомцев пользователя, для которых сегодня не был сдан отчет.
     *
     * @return список питомцев
     */
    public List<Pets> findPetsWithoutTodayReport(Long petsId) {
        List<Pets> petWithoutReportList = new ArrayList<>();
        for (Pets pets : petsRepository.findAllByProbationStatusContainsIgnoreCase("процесс")) {
            Report report = findTodayCompletedReportsByPetId(pets.getId());
            if (null == report) {
                petWithoutReportList.add(pets);
            }
        }
        return petWithoutReportList;
    }


/*    *//**
     * Формирую список пользователей, которые не сдали сегодня отчет
     *
     * @return список пользователей без отчета сегодня
     */
    public List<Customer> findCustomersWithoutTodayReport() {
        List<Customer> customerWithoutReportList = new ArrayList<>();
        for (Pets pets : petsRepository.findPetsByCustomerNotNull()) {
            Report report = findTodayCompletedReportsByPetId(pets.getId());
            if (null == report) {
                customerWithoutReportList.add(pets.getCustomer());
            }
        }
        return customerWithoutReportList;
    }
    public  PhotoPet getPhotoPetByReportId(Long reportId){
        return photoPetService.findPhotoPetByReport_Id(reportId);
    }
}

