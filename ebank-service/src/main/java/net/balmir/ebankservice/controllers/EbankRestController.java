package net.balmir.ebankservice.controllers;


import net.balmir.ebankservice.entities.BankAccount;
import net.balmir.ebankservice.services.EbankService;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class EbankRestController {
    private EbankService ebankService ;

    public EbankRestController(EbankService ebanService){
        this.ebankService = ebanService ;
    }

    @GetMapping("/accounts")
    public List<BankAccount> getAllBankAccounts(){
        return ebankService.getAllBankAccounts();
    }

    @GetMapping("/accounts/{id}")
    public BankAccount getBankAccountById(@PathVariable String id) {
        return ebankService.getBankAccountById(id);
    }

    @PostMapping("/accounts")
    public BankAccount save(@RequestBody BankAccount bankAccount){
         return ebankService.save(bankAccount);
    }
}
