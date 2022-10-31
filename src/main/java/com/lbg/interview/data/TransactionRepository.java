package com.lbg.interview.data;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lbg.interview.model.entity.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {

}
