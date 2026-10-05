package net.balmir.customersevice;

import net.balmir.customersevice.entities.Customer;
import net.balmir.customersevice.service.CustomerService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class CustomerSeviceApplication {

    public static void main(String[] args) {
        SpringApplication.run(CustomerSeviceApplication.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner(CustomerService customerService) {
        return args -> {
            List<String> names = List.of("Acil" , "Celia" , "Tasnim");
            names.forEach(name ->{
                customerService.saveCustomer(Customer.builder()
                        .name(name).email(name+"@gmail.com")
                        .build());
            });
                

        };
    }


}
