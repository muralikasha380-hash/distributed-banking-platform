package com.Bank.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;

@Entity
@Table(name = "BANK_TRANSACTIONS")
public class BankTransaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long accountId;

    @Column(name = "TRANSACTION_TYPE", nullable = false, length = 20)
    private String transactionType;

    @Column(nullable = false, precision = 19, scale = 2)
    private BigDecimal amount;

    @Column
    private Long targetAccountId;

    @Column(nullable = false)
    private LocalDateTime transactionDate;

    public BankTransaction() {
    }
    
    public BankTransaction(
            Long accountId,
            String transactionType,
            BigDecimal amount,
            Long targetAccountId) {

        this.accountId = accountId;
        this.transactionType =transactionType ;
        this.amount = amount;
        this.targetAccountId = targetAccountId;
    }

    @PrePersist
    public void setTransactionDateBeforeSaving() {
        if (transactionDate == null) {
            transactionDate = LocalDateTime.now();
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public String gettransactionType() {
        return transactionType;
    }

    public void settransactionType(String type) {
        this.transactionType = type;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Long getTargetAccountId() {
        return targetAccountId;
    }

    public void setTargetAccountId(Long targetAccountId) {
        this.targetAccountId = targetAccountId;
    }

    public LocalDateTime getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(LocalDateTime transactionDate) {
        this.transactionDate = transactionDate;
    }
}