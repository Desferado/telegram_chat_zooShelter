package pro.sky.telegram_chat_zooShelter.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pro.sky.telegram_chat_zooShelter.model.Customer;
import pro.sky.telegram_chat_zooShelter.services.CustomerService;

import java.util.List;

@RequestMapping("/customer")
@RestController
public class CustomerController {
    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }
@Operation(
        summary = "Получение списка всех клиентов",
    responses = {
        @ApiResponse(
                responseCode = "200",
                description = "Получение списка всех клиентов",
                content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                array = @ArraySchema (schema = @Schema(implementation = Customer.class))
                )
        )
})
    @GetMapping("/get-customers")
    public ResponseEntity <List<Customer>> getAllCustomers() {
        return ResponseEntity.ok(customerService.getCustomers());
    }
    @Operation(
            summary = "Поиск клиента по id",
            responses = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Поиск клиента по id",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = Customer.class)
                    )
            )
    })
    @GetMapping("{id}")
    public ResponseEntity <Customer> getCustomerById(@Parameter (description = "Клиент с данным id") @PathVariable Long id) {
        Customer customer = customerService.findCustomerById(id);
        if (customer == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(customer);
        }
    }
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Заведение клиента в базу",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation =  Customer.class)
                    )
            )
    })
    @PostMapping
    public ResponseEntity <Customer> createCustomer(@RequestBody Customer customer) {
        return ResponseEntity.ok(customerService.createCustomer(customer));
    }
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Изменение клиента",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation =  Customer.class)
                    )
            )
    })
    @PutMapping
    public ResponseEntity <Customer> updateCustomer(@RequestBody Customer newCustomer) {
        Customer customer = customerService.updateCustomer(newCustomer);
        if (customer == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(customer);
        }
    }
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Удаление клиента из базы",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE)
            )
    })
    @DeleteMapping("{id}")
    public ResponseEntity <Customer> removeCustomer(@PathVariable Long id) {
        Customer customer = customerService.deleteCustomerById(id);
        if (customer == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return ResponseEntity.ok(customer);
    }

}
