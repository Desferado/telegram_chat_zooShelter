package pro.sky.telegram_chat_zooShelter.services;

import org.springframework.stereotype.Service;
import pro.sky.telegram_chat_zooShelter.model.Customer;
import pro.sky.telegram_chat_zooShelter.repository.CustomerRepository;

import java.util.List;

@Service
public class CustomerService {
    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public List<Customer> getCustomers() {
        return customerRepository.findAll();
    }

    public Customer findCustomerById(Long id) {
        return customerRepository.findCustomerById(id).orElse(null);
    }
    public Customer findCustomerByChatId(Long chatId) {
        return customerRepository.findCustomerByChatId(chatId).orElse(null);
    }
    public Customer createCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    public Customer updateCustomer(Customer customer) {
        if (findCustomerById(customer.getId()) == null) {
            return null;
        } else {
            return customerRepository.save(customer);
        }
    }

    public Customer deleteCustomerById(Long id) {
        Customer customer = findCustomerById(id);
        if (customer == null) {
            return null;
        } else {
            customerRepository.deleteCustomerById(id);
            return customer;
        }
    }

}
