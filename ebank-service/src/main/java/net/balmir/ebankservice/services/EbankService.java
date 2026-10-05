package net.balmir.ebankservice.services;


import net.balmir.ebankservice.entities.BankAccount;
import net.balmir.ebankservice.repository.BankAccountRepository;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;


@Service
public class EbankService {
    private BankAccountRepository accountRepository;

    //inject via constructeur
    public EbankService(BankAccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }


    //retourner tout les comptes
    public List<BankAccount> getAllBankAccounts(){
        return accountRepository.findAll();
    }

    public BankAccount getBankAccountById(String id){
        return accountRepository.findById(id)
                .orElseThrow(()->new RuntimeException("Account not Found"));
    }

    public BankAccount save(BankAccount bankAccount){
        bankAccount.setId(UUID.randomUUID().toString());
        bankAccount.setCreatedAt(new Date());
        return accountRepository.save(bankAccount);
    }

}
