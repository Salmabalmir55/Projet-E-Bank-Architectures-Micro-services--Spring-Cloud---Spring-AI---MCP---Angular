package net.balmir.ebankservice;

import net.balmir.ebankservice.repository.BankAccountRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class EbankServiceApplicationTests {

    @Autowired
    private BankAccountRepository accountRepository;

    @Test
    void contextLoads() {
        assertThat(accountRepository.count()).isEqualTo(18);
    }
}
