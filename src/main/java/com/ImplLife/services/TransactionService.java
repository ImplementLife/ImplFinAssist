package com.ImplLife.services;

import com.ImplLife.dao.TransactionDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TransactionService {
    @Autowired
    private TransactionDAO transactionDAO;
}
