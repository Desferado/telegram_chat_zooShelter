package pro.sky.telegram_chat_zooShelter.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


import pro.sky.telegram_chat_zooShelter.model.Customer;
import pro.sky.telegram_chat_zooShelter.services.CustomerService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest(CustomerController.class)
public class CustomerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CustomerService customerService;
    private List<Customer> customers;

    @BeforeEach
    public void setUp() {
        customers = new ArrayList<>(
                Arrays.asList(
                        new Customer(1L,10L, "Ivanov","Ivan","Ivanovich",
                                "212121","Adress1","email1"),
                        new Customer(2L,20L, "Petrov","Petr","Petrovich",
                                "3232323","Adress2","email2")
                )
        );
    }

    @Test
    public void getAllCustomersTest() throws Exception {
        when(customerService.getCustomers()).thenReturn(customers);

        mockMvc.perform(get("/customer/get-customers"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(customers.size()));

        verify(customerService).getCustomers();
    }


    @Test
    public void getCustomerByIdTest() throws Exception {
        Customer customer = customers.get(0);
        Long customerId = customer.getId();

        when(customerService.findCustomerById(customerId)).thenReturn(customer);

        mockMvc.perform(get("/customer/{id}", customerId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(customerId));

        verify(customerService).findCustomerById(customerId);
    }

    // Тест для создания клиента
    @Test
    public void createCustomerTest() throws Exception {
        Customer newCustomer = new Customer(3L,30L, "Dibrov","Karl","Sidorovich",
                "3235353","Adress3","email3");

        when(customerService.createCustomer(any(Customer.class))).thenReturn(newCustomer);

        mockMvc.perform(post("/customer")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"id\":3,\"имя клиента\":\"Karl\"}\"")) // Если объект Customer имеет поле "имя клиента".
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(newCustomer.getId()));

        verify(customerService).createCustomer(any(Customer.class));
    }

    // Тест для обновления клиента
    @Test
    public void updateCustomerTest() throws Exception {
        Customer updatedCustomer = customers.get(0);
        updatedCustomer.setName("Updated John Doe");

        when(customerService.updateCustomer(any(Customer.class))).thenReturn(updatedCustomer);

        mockMvc.perform(put("/customer")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"id\":1,\"имя клиента\":\"Updated John Doe\"}\""))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(updatedCustomer.getId()));

        verify(customerService).updateCustomer(any(Customer.class));
    }

    // Тест для удаления клиента
    @Test
    public void removeCustomerTest() throws Exception {
        Customer deletedCustomer = customers.get(0);
        Long customerId = deletedCustomer.getId();

        when(customerService.deleteCustomerById(customerId)).thenReturn(deletedCustomer);

        mockMvc.perform(delete("/customer/{id}", customerId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(customerId));

        verify(customerService).deleteCustomerById(customerId);
    }
}