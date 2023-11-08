package pro.sky.telegram_chat_zooShelter.repository;

import org.jetbrains.annotations.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pro.sky.telegram_chat_zooShelter.model.Customer;
import pro.sky.telegram_chat_zooShelter.model.Pets;

import java.util.List;
import java.util.Optional;

@Repository
public interface PetsRepository extends JpaRepository<Pets, Long> {
    List<Pets> findAllByProbationStatusContainsIgnoreCase(String status);
    @Override
    @NotNull List<Pets> findAll();
    List<Pets> findPetsByBreed(String breed);

    Optional<Pets> findPetsById(Long id);
    Pets findPetsByCustomer(Customer customer);
    List<Pets> findPetsByCustomerNotNull();

}
