package net.balmir.ebankservice.services;


import net.balmir.ebankservice.entities.BankAccount;
import net.balmir.ebankservice.feign.CustomerRestClient;
import net.balmir.ebankservice.model.Customer;
import net.balmir.ebankservice.repository.BankAccountRepository;
import org.springframework.ai.mcp.annotation.McpTool;
import org.springframework.ai.mcp.annotation.McpToolParam;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;


@Service
public class EbankService {
    private final BankAccountRepository accountRepository;
    private final CustomerRestClient customerRestClient;

    //inject via constructeur
    public EbankService(BankAccountRepository accountRepository , CustomerRestClient customerRestClient) {
        this.accountRepository = accountRepository;
        this.customerRestClient = customerRestClient;
    }


    //retourner tous les comptes
    @McpTool(description="Get All Bank Accounts")
    public List<BankAccount> getAllBankAccounts(){
        return accountRepository.findAll().stream()
                .map(this::attachCustomer)
                .toList();
    }

    @McpTool(description="Get Bank Account by ID")
    public BankAccount getBankAccountById(@McpToolParam(description = "The Bank Account Id") String id){
        BankAccount bankAccount = accountRepository.findById(id)
                .orElseThrow(() -> new org.springframework.web.server.ResponseStatusException(
                        org.springframework.http.HttpStatus.NOT_FOUND, "Account not found"));
        return attachCustomer(bankAccount);
    }

    @McpTool(description="Save a new Bank Account")
    public BankAccount save(@McpToolParam (description = "The Bank Account to save (balance, type , customerId)")
                                BankAccount bankAccount){
        Customer customer = customerRestClient.getCustomerById(bankAccount.getCustomerId());
        bankAccount.setId(UUID.randomUUID().toString());
        bankAccount.setCreatedAt(new Date());
        bankAccount.setCustomer(customer);
        return accountRepository.save(bankAccount);

    }

    private BankAccount attachCustomer(BankAccount bankAccount) {
        bankAccount.setCustomer(customerRestClient.getCustomerById(bankAccount.getCustomerId()));
        return bankAccount;
    }
}
