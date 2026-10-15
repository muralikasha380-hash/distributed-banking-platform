package com.Bank.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.Bank.entity.BankTransaction;

public interface TransactionRepository extends JpaRepository<BankTransaction, Long> {
    List<BankTransaction> findByAccountId(Long accountId);

}
