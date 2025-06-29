package com.kasumi.crud_kasumi.customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {
    HashMap<String, Object> datos;

    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public List<Customer> getCustomers() {
        return this.customerRepository.findAll();
    }

    public ResponseEntity<Object> newCustomer(Customer customer) {
        Optional<Customer> res = customerRepository.findCustomerByName(customer.getName());
        datos = new HashMap<>();

        if(res.isPresent() && customer.getId()==null) {
            datos.put("error", true);
            datos.put("message", "Ya existe un cliente con ese nombre");
            return new ResponseEntity<>(
                    datos,
                    HttpStatus.CONFLICT
            );
        }
        datos.put("message", "Se ha guardadó el cliente con éxito");

        if(customer.getId()!=null) {
            datos.put("message", "El cliente se actualizo con éxito");
        }
        customerRepository.save(customer);
        datos.put("data", customer);

        return new ResponseEntity<>(
                datos,
                HttpStatus.CREATED
        );
    }

    public ResponseEntity<Object> deleteCustomer(Long id){
        datos = new HashMap<>();
       boolean exists = this.customerRepository.existsById(id);
        if(!exists) {
            datos.put("error", true);
            datos.put("message", "No existe un cliente con ese id");
            return new ResponseEntity<>(
                    datos,
                    HttpStatus.CONFLICT
            );
        }
        customerRepository.deleteById(id);
        datos.put("message", "Cliente eliminado");
        return new ResponseEntity<>(
                datos,
                HttpStatus.ACCEPTED
        );
    }
}
