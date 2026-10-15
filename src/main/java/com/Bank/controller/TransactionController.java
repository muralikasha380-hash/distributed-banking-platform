package com.Bank.controller;

import org.springframework.web.bind.annotation.*;
import com.Bank.dto.DepositRequest;
import com.Bank.dto.TransferRequest;
import com.Bank.dto.withdrawRequest;
import com.Bank.entity.BankTransaction;
import com.Bank.service.TransactionService;

@RestController
@RequestMapping("/api/accounts")
public class TransactionController {

    private final TransactionService transactionServices;

    public TransactionController(TransactionService transactionServices) {
        this.transactionServices = transactionServices;
    }

    // Deposit money into an account
    @PostMapping("/{id}/deposit")
    public BankTransaction deposit(
            @PathVariable("id") Long id,
            @RequestBody DepositRequest request) {

        return transactionServices.deposit(id, request.getAmount());
    }

    // Withdraw money from an account
    @PostMapping("/{id}/withdraw")
    public BankTransaction withdraw(
            @PathVariable("id") Long id,
            @RequestBody withdrawRequest request) {

        return transactionServices.withdraw(id, request.getAmount());
    }

    // Transfer money between two accounts
    @PostMapping("/transfer")
    public BankTransaction transfer(
            @RequestBody TransferRequest request) {

        return transactionServices.transfer(
                request.getFromAccountId(),
                request.getToAccountId(),
                request.getAmount());
    }
}