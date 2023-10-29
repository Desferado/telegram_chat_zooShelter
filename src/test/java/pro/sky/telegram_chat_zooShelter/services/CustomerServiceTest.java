package pro.sky.telegram_chat_zooShelter.services;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import pro.sky.telegram_chat_zooShelter.model.Customer;
import pro.sky.telegram_chat_zooShelter.repository.CustomerRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ContextConfiguration(classes = {CustomerService.class})
@ExtendWith(SpringExtension.class)
class CustomerServiceTest {
    @MockBean
    private CustomerRepository customerRepository;

    @Autowired
    private CustomerService customerService;

    /**
     * Method under test: {@link CustomerService#getCustomers()}
     */
    @Test
    void testGetCustomers() {
        ArrayList<Customer> customerList = new ArrayList<>();
        when(customerRepository.findAll()).thenReturn(customerList);
        List<Customer> actualCustomers = customerService.getCustomers();
        verify(customerRepository).findAll();
        assertTrue(actualCustomers.isEmpty());
        assertSame(customerList, actualCustomers);
    }

    /**
     * Method under test: {@link CustomerService#findCustomerById(Long)}
     */
    @Test
    void testFindCustomerById() {
        Customer customer = new Customer();
        customer.setAddress("42 Main St");
        customer.setChatId(1L);
        customer.setEmail("jane.doe@example.org");
        customer.setId(1L);
        customer.setName("Name");
        customer.setPhone("6625550144");
        customer.setSecondName("Second Name");
        customer.setSurname("Doe");
        Optional<Customer> ofResult = Optional.of(customer);
        when(customerRepository.findCustomerById(Mockito.<Long>any())).thenReturn(ofResult);
        Customer actualFindCustomerByIdResult = customerService.findCustomerById(1L);
        verify(customerRepository).findCustomerById(Mockito.<Long>any());
        assertSame(customer, actualFindCustomerByIdResult);
    }

    /**
     * Method under test: {@link CustomerService#findCustomerByChatId(Long)}
     */
    @Test
    void testFindCustomerByChatId() {
        Customer customer = new Customer();
        customer.setAddress("42 Main St");
        customer.setChatId(1L);
        customer.setEmail("jane.doe@example.org");
        customer.setId(1L);
        customer.setName("Name");
        customer.setPhone("6625550144");
        customer.setSecondName("Second Name");
        customer.setSurname("Doe");
        Optional<Customer> ofResult = Optional.of(customer);
        when(customerRepository.findCustomerByChatId(Mockito.<Long>any())).thenReturn(ofResult);
        Customer actualFindCustomerByChatIdResult = customerService.findCustomerByChatId(1L);
        verify(customerRepository).findCustomerByChatId(Mockito.<Long>any());
        assertSame(customer, actualFindCustomerByChatIdResult);
    }

    /**
     * Method under test: {@link CustomerService#createCustomer(Customer)}
     */
    @Test
    void testCreateCustomer() {
        Customer customer = new Customer();
        customer.setAddress("42 Main St");
        customer.setChatId(1L);
        customer.setEmail("jane.doe@example.org");
        customer.setId(1L);
        customer.setName("Name");
        customer.setPhone("6625550144");
        customer.setSecondName("Second Name");
        customer.setSurname("Doe");
        when(customerRepository.save(Mockito.<Customer>any())).thenReturn(customer);

        Customer customer2 = new Customer();
        customer2.setAddress("42 Main St");
        customer2.setChatId(1L);
        customer2.setEmail("jane.doe@example.org");
        customer2.setId(1L);
        customer2.setName("Name");
        customer2.setPhone("6625550144");
        customer2.setSecondName("Second Name");
        customer2.setSurname("Doe");
        Customer actualCreateCustomerResult = customerService.createCustomer(customer2);
        verify(customerRepository).save(Mockito.<Customer>any());
        assertSame(customer, actualCreateCustomerResult);
    }

    /**
     * Method under test: {@link CustomerService#updateCustomer(Customer)}
     */
    @Test
    void testUpdateCustomer() {
        Customer customer = new Customer();
        customer.setAddress("42 Main St");
        customer.setChatId(1L);
        customer.setEmail("jane.doe@example.org");
        customer.setId(1L);
        customer.setName("Name");
        customer.setPhone("6625550144");
        customer.setSecondName("Second Name");
        customer.setSurname("Doe");
        Optional<Customer> ofResult = Optional.of(customer);

        Customer customer2 = new Customer();
        customer2.setAddress("42 Main St");
        customer2.setChatId(1L);
        customer2.setEmail("jane.doe@example.org");
        customer2.setId(1L);
        customer2.setName("Name");
        customer2.setPhone("6625550144");
        customer2.setSecondName("Second Name");
        customer2.setSurname("Doe");
        when(customerRepository.save(Mockito.<Customer>any())).thenReturn(customer2);
        when(customerRepository.findCustomerById(Mockito.<Long>any())).thenReturn(ofResult);

        Customer customer3 = new Customer();
        customer3.setAddress("42 Main St");
        customer3.setChatId(1L);
        customer3.setEmail("jane.doe@example.org");
        customer3.setId(1L);
        customer3.setName("Name");
        customer3.setPhone("6625550144");
        customer3.setSecondName("Second Name");
        customer3.setSurname("Doe");
        Customer actualUpdateCustomerResult = customerService.updateCustomer(customer3);
        verify(customerRepository).save(Mockito.<Customer>any());
        verify(customerRepository).findCustomerById(Mockito.<Long>any());
        assertSame(customer2, actualUpdateCustomerResult);
    }

    /**
     * Method under test: {@link CustomerService#updateCustomer(Customer)}
     */
    @Test
    void testUpdateCustomer2() {
        Optional<Customer> emptyResult = Optional.empty();
        when(customerRepository.findCustomerById(Mockito.<Long>any())).thenReturn(emptyResult);

        Customer customer = new Customer();
        customer.setAddress("42 Main St");
        customer.setChatId(1L);
        customer.setEmail("jane.doe@example.org");
        customer.setId(1L);
        customer.setName("Name");
        customer.setPhone("6625550144");
        customer.setSecondName("Second Name");
        customer.setSurname("Doe");
        Customer actualUpdateCustomerResult = customerService.updateCustomer(customer);
        verify(customerRepository).findCustomerById(Mockito.<Long>any());
        assertNull(actualUpdateCustomerResult);
    }

    /**
     * Method under test: {@link CustomerService#deleteCustomerById(Long)}
     */
    @Test
    void testDeleteCustomerById() {
        Customer customer = new Customer();
        customer.setAddress("42 Main St");
        customer.setChatId(1L);
        customer.setEmail("jane.doe@example.org");
        customer.setId(1L);
        customer.setName("Name");
        customer.setPhone("6625550144");
        customer.setSecondName("Second Name");
        customer.setSurname("Doe");
        Optional<Customer> ofResult = Optional.of(customer);
        doNothing().when(customerRepository).deleteCustomerById(Mockito.<Long>any());
        when(customerRepository.findCustomerById(Mockito.<Long>any())).thenReturn(ofResult);
        Customer actualDeleteCustomerByIdResult = customerService.deleteCustomerById(1L);
        verify(customerRepository).deleteCustomerById(Mockito.<Long>any());
        verify(customerRepository).findCustomerById(Mockito.<Long>any());
        assertSame(customer, actualDeleteCustomerByIdResult);
    }

    /**
     * Method under test: {@link CustomerService#deleteCustomerById(Long)}
     */
    @Test
    void testDeleteCustomerById2() {
        Optional<Customer> emptyResult = Optional.empty();
        when(customerRepository.findCustomerById(Mockito.<Long>any())).thenReturn(emptyResult);
        Customer actualDeleteCustomerByIdResult = customerService.deleteCustomerById(1L);
        verify(customerRepository).findCustomerById(Mockito.<Long>any());
        assertNull(actualDeleteCustomerByIdResult);
    }
}

