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
        // Создаем пустой список клиентов
        ArrayList<Customer> customerList = new ArrayList<>();

        // Заглушаем вызов метода findAll репозитория и указываем возвращаемый пустой список
        when(customerRepository.findAll()).thenReturn(customerList);

        // Вызываем метод getCustomers() из сервиса
        List<Customer> actualCustomers = customerService.getCustomers();

        // Проверяем, что метод findAll был вызван у репозитория
        verify(customerRepository).findAll();

        // Проверяем, что возвращенный список actualCustomers пустой
        assertTrue(actualCustomers.isEmpty());

        // Проверяем, что actualCustomers и customerList - это один и тот же объект
        assertSame(customerList, actualCustomers);
    }


    /**
     * Method under test: {@link CustomerService#findCustomerById(Long)}
     */
    @Test
    void testFindCustomerById() {
        // Создаем объект клиента с данными
        Customer customer = new Customer();
        customer.setAddress("42 Main St");
        customer.setChatId(1L);
        customer.setEmail("jane.doe@example.org");
        customer.setId(1L);
        customer.setName("Name");
        customer.setPhone("6625550144");
        customer.setSecondName("Second Name");
        customer.setSurname("Doe");

        // Создаем заглушку для возвращаемого значения метода findCustomerById
        Optional<Customer> ofResult = Optional.of(customer);

        // Заглушаем вызов метода findCustomerById репозитория и указываем возвращаемое значение
        when(customerRepository.findCustomerById(Mockito.<Long>any())).thenReturn(ofResult);

        // Вызываем метод findCustomerById() из сервиса
        Customer actualFindCustomerByIdResult = customerService.findCustomerById(1L);

        // Проверяем, что метод findCustomerById был вызван у репозитория
        verify(customerRepository).findCustomerById(Mockito.<Long>any());

        // Проверяем, что возвращенный объект actualFindCustomerByIdResult является тем же самым объектом customer
        assertSame(customer, actualFindCustomerByIdResult);
    }

    /**
     * Method under test: {@link CustomerService#findCustomerByChatId(Long)}
     */
    @Test
    void testFindCustomerByChatId() {
        // Создаем объект клиента с данными
        Customer customer = new Customer();
        customer.setAddress("42 Main St");
        customer.setChatId(1L);
        customer.setEmail("jane.doe@example.org");
        customer.setId(1L);
        customer.setName("Name");
        customer.setPhone("6625550144");
        customer.setSecondName("Second Name");
        customer.setSurname("Doe");

        // Создаем заглушку для возвращаемого значения метода findCustomerByChatId
        Optional<Customer> ofResult = Optional.of(customer);

        // Заглушаем вызов метода findCustomerByChatId репозитория и указываем возвращаемое значение
        when(customerRepository.findCustomerByChatId(Mockito.<Long>any())).thenReturn(ofResult);

        // Вызываем метод findCustomerByChatId() из  сервиса
        Customer actualFindCustomerByChatIdResult = customerService.findCustomerByChatId(1L);

        // Проверяем, что метод findCustomerByChatId был вызван у репозитория
        verify(customerRepository).findCustomerByChatId(Mockito.<Long>any());

        // Проверяем, что возвращенный объект actualFindCustomerByChatIdResult является тем же самым объектом customer
        assertSame(customer, actualFindCustomerByChatIdResult);
    }


    /**
     * Method under test: {@link CustomerService#createCustomer(Customer)}
     */
    @Test
    void testCreateCustomer() {
        // Создаем объект клиента с данными
        Customer customer = new Customer();
        customer.setAddress("42 Main St");
        customer.setChatId(1L);
        customer.setEmail("jane.doe@example.org");
        customer.setId(1L);
        customer.setName("Name");
        customer.setPhone("6625550144");
        customer.setSecondName("Second Name");
        customer.setSurname("Doe");

        // Заглушаем вызов метода save репозитория и указываем возвращаемое значение
        when(customerRepository.save(Mockito.<Customer>any())).thenReturn(customer);

        // Создаем второй объект клиента с данными
        Customer customer2 = new Customer();
        customer2.setAddress("42 Main St");
        customer2.setChatId(1L);
        customer2.setEmail("jane.doe@example.org");
        customer2.setId(1L);
        customer2.setName("Name");
        customer2.setPhone("6625550144");
        customer2.setSecondName("Second Name");
        customer2.setSurname("Doe");

        // Вызываем метод createCustomer() из  сервиса с объектом customer2
        Customer actualCreateCustomerResult = customerService.createCustomer(customer2);

        // Проверяем, что метод save был вызван у репозитория
        verify(customerRepository).save(Mockito.<Customer>any());

        // Проверяем, что возвращенный объект actualCreateCustomerResult является тем же самым объектом customer
        assertSame(customer, actualCreateCustomerResult);
    }


    /**
     * Method under test: {@link CustomerService#updateCustomer(Customer)}
     */
    @Test
    void testUpdateCustomer() {
        // Создаем объект клиента с данными (первая версия клиента)
        Customer customer = new Customer();
        customer.setAddress("42 Main St");
        customer.setChatId(1L);
        customer.setEmail("jane.doe@example.org");
        customer.setId(1L);
        customer.setName("Name");
        customer.setPhone("6625550144");
        customer.setSecondName("Second Name");
        customer.setSurname("Doe");

        // Создаем Optional с первой версией клиента
        Optional<Customer> ofResult = Optional.of(customer);

        // Создаем второй объект клиента с данными (вторая версия клиента)
        Customer customer2 = new Customer();
        customer2.setAddress("42 Main St");
        customer2.setChatId(1L);
        customer2.setEmail("jane.doe@example.org");
        customer2.setId(1L);
        customer2.setName("Name");
        customer2.setPhone("6625550144");
        customer2.setSecondName("Second Name");
        customer2.setSurname("Doe");

        // Заглушаем вызов методов save и findCustomerById у репозитория и указываем возвращаемые значения
        when(customerRepository.save(Mockito.<Customer>any())).thenReturn(customer2);
        when(customerRepository.findCustomerById(Mockito.<Long>any())).thenReturn(ofResult);

        // Создаем третий объект клиента с данными (новая версия клиента)
        Customer customer3 = new Customer();
        customer3.setAddress("42 Main St");
        customer3.setChatId(1L);
        customer3.setEmail("jane.doe@example.org");
        customer3.setId(1L);
        customer3.setName("Name");
        customer3.setPhone("6625550144");
        customer3.setSecondName("Second Name");
        customer3.setSurname("Doe");

        // Вызываем метод updateCustomer() из сервиса с объектом customer3
        Customer actualUpdateCustomerResult = customerService.updateCustomer(customer3);

        // Проверяем, что метод save был вызван у репозитория
        verify(customerRepository).save(Mockito.<Customer>any());

        // Проверяем, что метод findCustomerById был вызван у репозитория
        verify(customerRepository).findCustomerById(Mockito.<Long>any());

        // Проверяем, что возвращенный объект actualUpdateCustomerResult является тем же самым объектом customer2
        assertSame(customer2, actualUpdateCustomerResult);
    }


    /**
     * Method under test: {@link CustomerService#updateCustomer(Customer)}
     */
    @Test
    void testUpdateCustomer2() {
        // Создаем пустой Optional (клиент не найден)
        Optional<Customer> emptyResult = Optional.empty();

        // Заглушаем вызов метода findCustomerById у репозитория и указываем, что клиент не найден
        when(customerRepository.findCustomerById(Mockito.<Long>any())).thenReturn(emptyResult);

        // Создаем объект клиента с данными
        Customer customer = new Customer();
        customer.setAddress("42 Main St");
        customer.setChatId(1L);
        customer.setEmail("jane.doe@example.org");
        customer.setId(1L);
        customer.setName("Name");
        customer.setPhone("6625550144");
        customer.setSecondName("Second Name");
        customer.setSurname("Doe");

        // Вызываем метод updateCustomer() из  сервиса с объектом customer
        Customer actualUpdateCustomerResult = customerService.updateCustomer(customer);

        // Проверяем, что метод findCustomerById был вызван у репозитория
        verify(customerRepository).findCustomerById(Mockito.<Long>any());

        // Проверяем, что возвращенный объект actualUpdateCustomerResult равен null, так как клиент не найден
        assertNull(actualUpdateCustomerResult);
    }


    /**
     * Method under test: {@link CustomerService#deleteCustomerById(Long)}
     */
    @Test
    void testDeleteCustomerById() {
        // Создаем объект клиента с данными
        Customer customer = new Customer();
        customer.setAddress("42 Main St");
        customer.setChatId(1L);
        customer.setEmail("jane.doe@example.org");
        customer.setId(1L);
        customer.setName("Name");
        customer.setPhone("6625550144");
        customer.setSecondName("Second Name");
        customer.setSurname("Doe");

        // Создаем Optional с данным клиентом
        Optional<Customer> ofResult = Optional.of(customer);

        // Заглушаем вызов метода findCustomerById у репозитория и указываем, что клиент найден
        when(customerRepository.findCustomerById(Mockito.<Long>any())).thenReturn(ofResult);

        // Заглушаем вызов метода deleteCustomerById у репозитория и указываем, что метод ничего не делает (doNothing)
        doNothing().when(customerRepository).deleteCustomerById(Mockito.<Long>any());

        // Вызываем метод deleteCustomerById() из вашего сервиса для удаления клиента
        Customer actualDeleteCustomerByIdResult = customerService.deleteCustomerById(1L);

        // Проверяем, что метод deleteCustomerById был вызван у репозитория
        verify(customerRepository).deleteCustomerById(Mockito.<Long>any());

        // Проверяем, что метод findCustomerById был вызван у репозитория
        verify(customerRepository).findCustomerById(Mockito.<Long>any());

        // Проверяем, что возвращенный объект actualDeleteCustomerByIdResult равен удаленному клиенту
        assertSame(customer, actualDeleteCustomerByIdResult);
    }

    /**
     * Method under test: {@link CustomerService#deleteCustomerById(Long)}
     */
    @Test
    void testDeleteCustomerById2() {
        // Создаем пустой Optional, чтобы указать, что клиент не найден
        Optional<Customer> emptyResult = Optional.empty();

        // Заглушаем вызов метода findCustomerById у репозитория и указываем, что клиент не найден
        when(customerRepository.findCustomerById(Mockito.<Long>any())).thenReturn(emptyResult);

        // Вызываем метод deleteCustomerById() из вашего сервиса для удаления клиента
        Customer actualDeleteCustomerByIdResult = customerService.deleteCustomerById(1L);

        // Проверяем, что метод findCustomerById был вызван у репозитория
        verify(customerRepository).findCustomerById(Mockito.<Long>any());

        // Проверяем, что метод вернул null, так как клиент не был найден
        assertNull(actualDeleteCustomerByIdResult);
    }

}

