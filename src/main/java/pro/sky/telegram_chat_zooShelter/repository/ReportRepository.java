package pro.sky.telegram_chat_zooShelter.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pro.sky.telegram_chat_zooShelter.model.Report;

import java.util.Optional;

@Repository
public interface ReportRepository extends JpaRepository<Report, Long> {
    @Override
    Optional<Report> findById(Long petId);
}
