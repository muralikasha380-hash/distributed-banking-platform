package com.Bank.service;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.Bank.entity.Account;
import com.Bank.entity.BankTransaction;
import com.Bank.exception.AccountNotFoundException;
import com.Bank.repository.AccountRepository;
import com.Bank.repository.TransactionRepository;

@Service
public class TransactionService {

    private final AccountRepository accountRepository;
    private final TransactionRepository transactionRepository;

    public TransactionService(
            AccountRepository accountRepository,
            TransactionRepository transactionRepository) {

        this.accountRepository = accountRepository;
        this.transactionRepository = transactionRepository;
    }

    // Deposit money into an account
    @Transactional
    public BankTransaction deposit(Long accountId, BigDecimal amount) {

        validateAmount(amount);

        Account account = findAccount(accountId);

        BigDecimal newBalance = account.getBalance().add(amount);
        account.setBalance(newBalance);

        accountRepository.save(account);

        BankTransaction transaction = new BankTransaction();
        transaction.setAccountId(accountId);
        transaction.settransactionType("DEPOSIT");
        transaction.setAmount(amount);

        return transactionRepository.save(transaction);
    }

    // Withdraw money from an account
    @Transactional
    public BankTransaction withdraw(Long accountId, BigDecimal amount) {

        validateAmount(amount);

        Account account = findAccount(accountId);

        if (account.getBalance().compareTo(amount) < 0) {
            throw new IllegalArgumentException("Insufficient balance in account");
        }

        BigDecimal newBalance = account.getBalance().subtract(amount);
        account.setBalance(newBalance);

        accountRepository.save(account);

        BankTransaction transaction = new BankTransaction();
        transaction.setAccountId(accountId);
        transaction.settransactionType("WITHDRAW");
        transaction.setAmount(amount);

        return transactionRepository.save(transaction);
    }

    // Transfer money between two accounts
    @Transactional
    public BankTransaction transfer(
            Long fromAccountId,
            Long toAccountId,
            BigDecimal amount) {

        validateAmount(amount);

        if (fromAccountId.equals(toAccountId)) {
            throw new IllegalArgumentException(
                    "Source and destination accounts must be different");
        }

        Account fromAccount = findAccount(fromAccountId);
        Account toAccount = findAccount(toAccountId);

        if (fromAccount.getBalance().compareTo(amount) < 0) {
            throw new IllegalArgumentException(
                    "Insufficient balance in source account");
        }

        fromAccount.setBalance(
                fromAccount.getBalance().subtract(amount));

        toAccount.setBalance(
                toAccount.getBalance().add(amount));

        accountRepository.save(fromAccount);
        accountRepository.save(toAccount);

        BankTransaction transaction = new BankTransaction();
        transaction.setAccountId(fromAccountId);
        transaction.settransactionType("TRANSFER");
        transaction.setAmount(amount);

        return transactionRepository.save(transaction);
    }

    // Find an account or throw an exception
    private Account findAccount(Long accountId) {

        return accountRepository.findById(accountId)
                .orElseThrow(() -> new AccountNotFoundException(
                        "Account not found with ID: " + accountId));
    }

    // Validate the transaction amount
    private void validateAmount(BigDecimal amount) {

        if (amount == null || amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException(
                    "Amount must be greater than zero");
        }
    }
 // Get transaction history for an account
 public List<BankTransaction> getTransactionHistory(Long accountId) {

     findAccount(accountId);

     return transactionRepository.findByAccountId(accountId);
 }
}