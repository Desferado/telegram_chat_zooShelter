package pro.sky.telegram_chat_zooShelter.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pro.sky.telegram_chat_zooShelter.model.PhotoPet;

import java.util.Optional;

@Repository
public interface PhotoPetRepository extends JpaRepository<PhotoPet, Long> {

    Optional<PhotoPet> findPhotoPetByPetId(Long petId);
}