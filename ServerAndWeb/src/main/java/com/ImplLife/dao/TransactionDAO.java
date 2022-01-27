package com.ImplLife.dao;

import com.ImplLife.entity.dto.db.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionDAO extends JpaRepository<Transaction, Long> {
}
