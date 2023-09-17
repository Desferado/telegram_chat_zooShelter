package pro.sky.telegram_chat_zooShelter.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import pro.sky.telegram_chat_zooShelter.model.Volunteer;

public interface VolunteerRepository extends JpaRepository<Volunteer, Long> {

}
