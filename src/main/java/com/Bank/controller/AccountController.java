package com.Bank.controller;

import com.Bank.service.TransactionService;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import com.Bank.dto.CreateAccountRequset;
import com.Bank.entity.Account;
import com.Bank.entity.BankTransaction;
import com.Bank.service.AccountServices;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {

	private final TransactionService transactionServices;
	private final AccountServices accountServices;

    public AccountController(AccountServices accountServices, TransactionService transactionService) {
        this.accountServices = accountServices;
		this.transactionServices = transactionService;
    }

    // Create a new account
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Account createAccount(
            @RequestBody CreateAccountRequset request) {

        return accountServices.createAccount(
                request.getAccountNumber(),
                request.getCustomerName());
    }

    // Get all accounts
    @GetMapping
    public List<Account> getAllAccounts() {
        return accountServices.getAllAccounts();
    }

    // Get account by ID
    @GetMapping("/{id}")
    public Account getAccountById(@PathVariable("id") Long id) {
        return accountServices.getAccountById(id);
    }

 // View transaction history
 @GetMapping("/{id}/transactions")
 public List<BankTransaction> getTransactionHistory(
         @PathVariable("id") Long id) {
     return transactionServices.getTransactionHistory(id);
 }
 
}