package com.works.restcontrollers;

import com.works.dto.CustomerDto;
import com.works.dto.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/customer")
public class CustomerController {

    private final CustomerService customerService;

    @PostMapping("/create")
    public ResponseEntity<CustomerDto> createCustomer(@RequestBody CustomerDto customer) {
        CustomerDto resultCustomer = customerService.createCustomer(customer);
        return ResponseEntity.ok(resultCustomer);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<CustomerDto>> getCustomers() {
        List<CustomerDto> customers = customerService.getCustomers();
        return ResponseEntity.ok(customers);
    }

    @GetMapping("/getById/{id}")
    public ResponseEntity<CustomerDto> getCustomer(@PathVariable("id") Long id) {
        CustomerDto customer = customerService.getCustomer();
        return ResponseEntity.ok(customer);

    }

}
