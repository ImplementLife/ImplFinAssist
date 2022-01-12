package com.ImplLife.dao;

import com.ImplLife.entity.dto.db.Transaction;
import com.ImplLife.entity.dto.db.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransactionDAO extends JpaRepository<Transaction, Long> {
    List<Transaction> findByOwnerId(Long ownerId);
}
