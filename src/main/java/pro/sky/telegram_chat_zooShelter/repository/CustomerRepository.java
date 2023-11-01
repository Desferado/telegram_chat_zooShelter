package pro.sky.telegram_chat_zooShelter.repository;

import org.jetbrains.annotations.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pro.sky.telegram_chat_zooShelter.model.Customer;
import pro.sky.telegram_chat_zooShelter.model.Report;

import java.util.List;
import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    @Override
    @NotNull List<Customer> findAll();
    Optional<Customer> findCustomerById(Long id);
//    Customer findCustomerByReport(Report report);
    void deleteCustomerById(Long id);

    Optional<Customer> findCustomerByChatId(Long chatId);
}
