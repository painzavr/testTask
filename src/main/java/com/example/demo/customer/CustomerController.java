package com.example.demo.customer;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path = "api/customers")
public class CustomerController {
    private final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService){
        this.customerService = customerService;
    }

    @PostMapping
    public Customer registerNewCustomer(@RequestBody Customer customer){
        customerService.addNewCustomer(customer);
        return customer;
    }

    @GetMapping
    public List<Customer> getCustomers(){
        return customerService.getCustomers();
    }

    @GetMapping(path = "{customerId}")
    public Customer getCustomer(@PathVariable("customerId") Long customerId){
        return customerService.getCustomer(customerId);
    }

    @PutMapping(path = "{customerId}")
    public Customer updateCustomer(@PathVariable("customerId") Long customerId, @RequestBody Map<String , String> requstestedParam){
        customerService.updateCustomer(customerId, requstestedParam.get("fullName"), requstestedParam.get("phone"));
        return customerService.getCustomer(customerId);
    }

    @DeleteMapping(path = "{customerId}")
    public void deleteCustomer(@PathVariable("customerId") Long customerId){
        customerService.deleteCustomer(customerId);
    }
}
