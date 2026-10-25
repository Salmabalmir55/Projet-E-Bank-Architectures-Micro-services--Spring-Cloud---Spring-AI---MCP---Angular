package net.balmir.customerservice;


import net.balmir.customerservice.entities.Customer;
import net.balmir.customerservice.service.CustomerService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class CustomerServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(CustomerServiceApplication.class, args);
    }

    @Bean
    CommandLineRunner seedCustomers(CustomerService customerService) {
        return args -> {
            List<String> names = List.of("Acil", "Celia", "Tasnim");
            names.forEach(name -> customerService.saveCustomer(Customer.builder()
                    .name(name)
                    .email(name + "@gmail.com")
                    .build()));
        };
    }
}
