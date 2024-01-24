package com.example.demo.customer;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CustomerConfig {
    @Bean
    CommandLineRunner commandLineRunner(CustomerRepository repository){
        return args -> {
            Customer kek = new Customer(
                    "Susanna",
                    "heelo@gmail.com",
                    "+25112412455"
            );
            repository.save(kek);
            Customer kek1 = new Customer(
                    "Susdawanna",
                    "heelo@gawdmail.com"
            );
            repository.save(kek1);
        };

    }
}
