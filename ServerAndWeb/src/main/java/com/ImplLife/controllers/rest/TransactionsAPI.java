package com.ImplLife.controllers.rest;


import com.ImplLife.entity.dto.db.Category;
import com.ImplLife.entity.dto.db.Transaction;
import org.springframework.web.bind.annotation.RequestBody;

import java.security.Principal;
import java.util.List;

public interface TransactionsAPI {
    List<Transaction> getAllTransactions(Principal principal);
    List<Transaction> searchTransactions(Principal principal, @RequestBody Transaction searchCriteria);
    Transaction getSimpleTransaction(Principal principal, @RequestBody Long id);
    Transaction addSimpleTransaction(Principal principal, @RequestBody Transaction searchCriteria);
    Transaction updateSimpleTransaction(Principal principal, @RequestBody Transaction searchCriteria);
    void deleteSimpleTransaction(Principal principal, @RequestBody Long id);
    void deleteListTransaction(Principal principal, @RequestBody List<Long> id); // TODO: 26.12.2021 : need impl?
    void deleteAllTransaction(Principal principal);

    List<Category> getAllCategories(Principal principal);
    Category getLastSelectedCategory(Principal principal);
    void setLastSelectedCategory(Principal principal, @RequestBody Category category);
    Category addNewCategory(Principal principal, @RequestBody Category category);
    Category updateCategory(Principal principal, @RequestBody Category category);
    void deleteCategory(Principal principal, @RequestBody Long id);
}
