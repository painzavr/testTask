package com.example.demo.customer;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;
    @Autowired
    public CustomerService(CustomerRepository customerRepository){
        this.customerRepository = customerRepository;
    }
    public void addNewCustomer(Customer customer){
        if(checkName(customer.getFullName())){
            throw new IllegalStateException("invalid name form");
        }else if(customer.getEmail().length()>=100 ||
                customer.getEmail().length()<=2 ||
                !customer.getEmail().contains("@")){
            throw new IllegalStateException("invalid email form");

        }else if(customerRepository.findCustomerByEmail(customer.getEmail()).isPresent()){
            throw new IllegalStateException("email taken");
        }else if(customer.getPhone() != null){
            if(checkPhone(customer.getPhone())) {
                throw new IllegalStateException("invalid phone form");
            }
        }
        customerRepository.save(customer);
    }
    public List<Customer> getCustomers(){
        return customerRepository.findAll();
    }
    @Transactional
    public void updateCustomer(Long customerId, String name, String phone) {
        Customer customer = customerRepository.findById(customerId).orElseThrow(()-> new IllegalStateException("" +
                "customer with id " + customerId + " doesn`t exist"));
        if(!checkName(name)){
            customer.setFullName(name);
        }else{
            throw new IllegalStateException("cannot apply changes, invalid name form");
        }
        if(!checkPhone(phone)){
            customer.setPhone(phone);
        }else{
            throw new IllegalStateException("cannot apply changes, invalid phone form");
        }
        customer.setUpdated(System.currentTimeMillis());
    }

    public Customer getCustomer(Long customerId) {
        Optional<Customer> optionalCustomer = customerRepository.findById(customerId);
        if(optionalCustomer.isPresent()){
            return optionalCustomer.get();
        }else{
            throw new IllegalStateException("no customer with id " + customerId);
        }

    }

    private boolean checkName(String name){
        return (name.length()>50 || name.length()<2);
    }
    private boolean checkPhone(String phone){
        return (phone.length() > 14 ||
                phone.length() < 6 ||
                phone.substring(1).matches(".*\\D.*")||
                phone.charAt(0)!='+');
    }
    @Transactional
    public void deleteCustomer(Long customerId) {
        Customer customer = customerRepository.findById(customerId).orElseThrow(()-> new IllegalStateException(
                "cannot delete customer with id " + customerId + " due to non-existing"));
        customer.setActive(false);
    }

}
