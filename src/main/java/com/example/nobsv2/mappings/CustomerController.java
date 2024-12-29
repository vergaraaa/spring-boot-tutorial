package com.example.nobsv2.mappings;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@RestController
@RequestMapping("/customer")
public class CustomerController {

    private final CustomerRepository customerRepository;

    public CustomerController(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }


    @GetMapping("/{id}")
    public ResponseEntity<Object> getCustomerById(@PathVariable Integer id) {
        // normally abstract this to a service layer
        
        return ResponseEntity.ok(customerRepository.findById(id).get());
    }
    
}
