package com.Bank.service;

import java.math.BigDecimal;
import java.util.List;
import org.springframework.stereotype.Service;
import com.Bank.entity.Account;
import com.Bank.repository.AccountRepository;

import jakarta.transaction.Transactional;

@Service
public class AccountServices {

	 private final AccountRepository accountRepository;

	 public AccountServices(AccountRepository accountRepository) {
	        this.accountRepository = accountRepository;
	    }

    // Create a new bank account
    public Account createAccount(String accountNumber, String customerName) {
            
        if (accountNumber == null || accountNumber.isBlank()) {
            throw new IllegalArgumentException("Account number is required");
        }

        if (customerName == null || customerName.isBlank()) {
            throw new IllegalArgumentException("Customer name is required");
        }

        if (accountRepository.findByAccountNumber(accountNumber).isPresent()) {
            throw new IllegalArgumentException("Account number already exists");
        }

        Account account = new Account();

        account.setAccountNumber(accountNumber);
        account.setCustomerName(customerName);
        account.setBalance(new BigDecimal("0.00"));

        return accountRepository.save(account);
    }
    
    // Get an account using its ID
    public Account getAccountById(Long id) {
        return accountRepository.findById(id)
                .orElseThrow(() ->
                    new RuntimeException("Account not found with ID: " + id));
    }

    // Get all bank accounts
    public List<Account> getAllAccounts() {
        return accountRepository.findAll();
    }

    @Transactional
    public Account depositMoney(Long id, BigDecimal amount) {

    	if (amount == null || amount.compareTo(BigDecimal.ZERO) <= 0) {
    		throw new IllegalArgumentException("Deposit amount must be greater than zero");
    	}

    	Account account = getAccountById(id);
    	account.setBalance(account.getBalance().add(amount));
    	return accountRepository.save(account);
    }
}