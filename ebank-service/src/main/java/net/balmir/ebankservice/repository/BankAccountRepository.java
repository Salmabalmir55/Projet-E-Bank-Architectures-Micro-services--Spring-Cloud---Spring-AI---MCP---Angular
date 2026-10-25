package net.balmir.ebankservice.repository;

import net.balmir.ebankservice.entities.BankAccount;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BankAccountRepository extends JpaRepository<BankAccount, String> {    //retourner les customers d'un client
    List<BankAccount> findByCustomerId(long id);


}
