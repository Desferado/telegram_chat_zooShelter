package pro.sky.telegram_chat_zooShelter.repository;

import org.jetbrains.annotations.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import pro.sky.telegram_chat_zooShelter.model.Volunteer;

import java.util.List;
import java.util.Optional;

public interface VolunteerRepository extends JpaRepository<Volunteer, Long> {
    @Override
    @NotNull
    List<Volunteer> findAll();

    Optional<Volunteer> findVolunteerByShelters_Id(Long idShelters);
}
