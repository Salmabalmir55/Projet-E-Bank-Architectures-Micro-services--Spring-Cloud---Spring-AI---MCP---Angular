package net.balmir.customerservice;

import net.balmir.customerservice.service.CustomerService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class CustomerServiceApplicationTests {

    @Autowired
    private CustomerService customerService;

    @Test
    void contextLoads() {
        assertThat(customerService.getAllCustomers()).hasSize(3);
    }
}
