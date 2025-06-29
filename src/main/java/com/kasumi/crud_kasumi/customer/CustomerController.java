package com.kasumi.crud_kasumi.customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path ="api/v1/customers")
public class CustomerController {
    private final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping
    public List<Customer> getCustomers() {
        return customerService.getCustomers();
    }

    @PostMapping
    public ResponseEntity<Object> registryCustomer(@RequestBody Customer customer) {
        return this.customerService.newCustomer(customer);
    }

    @PutMapping
    public ResponseEntity<Object> updateCustomer(@RequestBody Customer customer) {
        return this.customerService.newCustomer(customer);
    }

    @DeleteMapping(path = "{customerId}")
    public ResponseEntity<Object> deleteCustomer(@PathVariable("customerId") Long id) {
        return this.customerService.deleteCustomer(id);
    }
}
