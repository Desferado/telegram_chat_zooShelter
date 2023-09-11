package pro.sky.telegram_chat_zooShelter.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pro.sky.telegram_chat_zooShelter.model.Shelters;

@Repository
public interface ShelterRepository extends JpaRepository<Shelters, Long> {
}
