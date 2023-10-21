package pro.sky.telegram_chat_zooShelter.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pro.sky.telegram_chat_zooShelter.model.Report;

import java.time.LocalDateTime;

public interface ReportRepository extends JpaRepository<Report, Long> {

    /**
     *
     * @param petsId value of "petId" field
     * @param startTime beginning of the time period
     * @param finishTime end of the time period
     * @return a "Report" found in a certain time period
     */
    Report findReportByPetsIdAndDateBetween(Long petsId, LocalDateTime startTime, LocalDateTime finishTime);

    Report findFirstByPetsIdAndPetReportNotNullAndDateBetween(Long petsId, LocalDateTime startTime, LocalDateTime finishTime);
}
