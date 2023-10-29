package pro.sky.telegram_chat_zooShelter.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import pro.sky.telegram_chat_zooShelter.model.Customer;
import pro.sky.telegram_chat_zooShelter.services.CustomerService;

import java.util.ArrayList;

import static org.mockito.Mockito.when;

@ContextConfiguration(classes = {CustomerController.class})
@ExtendWith(SpringExtension.class)
class CustomerControllerTest {
    @Autowired
    private CustomerController customerController;

    @MockBean
    private CustomerService customerService;

    /**
     * Method under test: {@link CustomerController#createCustomer(Customer)}
     */
    @Test
    void testCreateCustomer() throws Exception {
        Customer customer = new Customer();
        customer.setAddress("42 Main St");
        customer.setChatId(1L);
        customer.setEmail("jane.doe@example.org");
        customer.setId(1L);
        customer.setName("Name");
        customer.setPhone("6625550144");
        customer.setSecondName("Second Name");
        customer.setSurname("Doe");
        String content = (new ObjectMapper()).writeValueAsString(customer);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/customer")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(customerController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(405));
    }

    /**
     * Method under test: {@link CustomerController#getAllCustomers()}
     */
    @Test
    void testGetAllCustomers() throws Exception {
        when(customerService.getCustomers()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/customer/get-customers");
        MockMvcBuilders.standaloneSetup(customerController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test: {@link CustomerController#getCustomerById(Long)}
     */
    @Test
    void testGetCustomerById() throws Exception {
        Customer customer = new Customer();
        customer.setAddress("42 Main St");
        customer.setChatId(1L);
        customer.setEmail("jane.doe@example.org");
        customer.setId(1L);
        customer.setName("Name");
        customer.setPhone("6625550144");
        customer.setSecondName("Second Name");
        customer.setSurname("Doe");
        when(customerService.findCustomerById(Mockito.<Long>any())).thenReturn(customer);
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/customer/42");
        MockHttpServletRequestBuilder requestBuilder = getResult.param("номер клиента", String.valueOf(1L));
        MockMvcBuilders.standaloneSetup(customerController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"id\":1,\"chatId\":1,\"surname\":\"Doe\",\"name\":\"Name\",\"secondName\":\"Second Name\",\"phone\":\"6625550144\","
                                        + "\"address\":\"42 Main St\",\"email\":\"jane.doe@example.org\"}"));
    }

    /**
     * Method under test: {@link CustomerController#removeCustomer(Long)}
     */
    @Test
    void testRemoveCustomer() throws Exception {
        Customer customer = new Customer();
        customer.setAddress("42 Main St");
        customer.setChatId(1L);
        customer.setEmail("jane.doe@example.org");
        customer.setId(1L);
        customer.setName("Name");
        customer.setPhone("6625550144");
        customer.setSecondName("Second Name");
        customer.setSurname("Doe");
        when(customerService.deleteCustomerById(Mockito.<Long>any())).thenReturn(customer);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/customer/42");
        MockMvcBuilders.standaloneSetup(customerController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"id\":1,\"chatId\":1,\"surname\":\"Doe\",\"name\":\"Name\",\"secondName\":\"Second Name\",\"phone\":\"6625550144\","
                                        + "\"address\":\"42 Main St\",\"email\":\"jane.doe@example.org\"}"));
    }
}

