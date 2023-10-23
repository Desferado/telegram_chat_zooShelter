package pro.sky.telegram_chat_zooShelter.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pro.sky.telegram_chat_zooShelter.model.PhotoPet;
import pro.sky.telegram_chat_zooShelter.model.Report;

import java.util.List;
import java.util.Optional;

@Repository
public interface PhotoPetRepository extends JpaRepository<PhotoPet, Long> {

    Optional<PhotoPet> findPhotoPetById(Long reportId);
    List<PhotoPet> findAllByReport_Id (Long reportId);
}