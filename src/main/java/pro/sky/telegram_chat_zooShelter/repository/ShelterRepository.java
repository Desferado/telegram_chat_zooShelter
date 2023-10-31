package pro.sky.telegram_chat_zooShelter.repository;

import org.jetbrains.annotations.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pro.sky.telegram_chat_zooShelter.model.Shelters;

import java.util.List;

@Repository
public interface ShelterRepository extends JpaRepository<Shelters, Long> {
    @Override
    @NotNull
    List<Shelters> findAll();
}
