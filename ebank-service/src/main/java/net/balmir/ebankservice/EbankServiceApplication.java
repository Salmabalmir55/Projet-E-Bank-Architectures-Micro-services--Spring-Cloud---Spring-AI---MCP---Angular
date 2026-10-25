package net.balmir.ebankservice;

import net.balmir.ebankservice.entities.BankAccount;
import net.balmir.ebankservice.repository.BankAccountRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

import java.util.Date;
import java.util.UUID;

@EnableFeignClients
@SpringBootApplication
public class EbankServiceApplication {

    public static void main(String[] args) {

        SpringApplication.run(EbankServiceApplication.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner(BankAccountRepository accountRepository) {
        return args -> {
            if (accountRepository.count() == 0) {
                for (int customerId = 1; customerId <= 3; customerId++) {
                    for (int i = 0; i < 6; i++) {
                        accountRepository.save(BankAccount.builder()
                                    .id(UUID.randomUUID().toString())
                                    .createdAt(new Date())
                                    .type(Math.random() > 0.5 ? "CURRENT-ACCOUNT" : "SAVING-ACCOUNT")
                                    .balance(1000 + Math.random() * 60000)
                                    .customerId(customerId)
                                    .build());
                    }
                }
            }
        };
    }

}
