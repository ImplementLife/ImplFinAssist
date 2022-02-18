package com.ImplLife.controllers.rest;

import com.ImplLife.entity.dto.db.Category;
import com.ImplLife.entity.dto.db.Transaction;
import com.ImplLife.services.ExcelService;
import com.ImplLife.services.TransactionService;
import com.ImplLife.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.security.Principal;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user/api")
public class IUserAPI implements UserAPI {
    @Autowired
    private UserService userService;
    @Autowired
    private TransactionService transactionService;
    @Autowired
    private ExcelService excelService;


    @Override
    @PostMapping("/getAllTransactions")
    public List<Transaction> getAllTransactions(Principal principal) {
        return transactionService.getAllTransactions(userService.userInfo(principal));
    }

    @Override
    @PostMapping("/searchTransactions")
    public List<Transaction> searchTransactions(Principal principal, Transaction searchCriteria) {
        throw new UnsupportedOperationException();
    }

    @Override
    @PostMapping("/getSimpleTransaction")
    public Transaction getSimpleTransaction(Principal principal, Long id) {
        throw new UnsupportedOperationException();
    }

    @Override
    @PostMapping("/addSimpleTransaction")
    public Transaction addSimpleTransaction(Principal principal, Transaction transaction) {
        return transactionService.addTransaction(userService.userInfo(principal), transaction);
    }

    @Override
    @PostMapping("updateSimpleTransaction")
    public Transaction updateSimpleTransaction(Principal principal, Transaction searchCriteria) {
        throw new UnsupportedOperationException();
    }

    @Override
    @PostMapping("/deleteSimpleTransaction")
    public void deleteSimpleTransaction(Principal principal, Long id) {
        throw new UnsupportedOperationException();
    }

    @Override
    @PostMapping("/deleteListTransaction")
    public void deleteListTransaction(Principal principal, List<Long> id) {
        throw new UnsupportedOperationException();
    }

    @Override
    @PostMapping("/deleteAllTransaction")
    public void deleteAllTransaction(Principal principal) {
        throw new UnsupportedOperationException();
    }

    @Override
    @PostMapping("/getAllCategories")
    public List<Category> getAllCategories(Principal principal) {
        return transactionService.getAllCategories(userService.userInfo(principal));
    }

    @Override
    @PostMapping("/getLastSelectedCategory")
    public Category getLastSelectedCategory(Principal principal) {
        throw new UnsupportedOperationException();
    }

    @Override
    @PostMapping("/setLastSelectedCategory")
    public void setLastSelectedCategory(Principal principal, Category category) {
        throw new UnsupportedOperationException();
    }

    @Override
    @PostMapping("/addNewCategory")
    public Category addNewCategory(Principal principal, Category category) {
        return transactionService.addNewCategory(userService.userInfo(principal), category.getName());
    }

    @Override
    @PostMapping("/updateCategory")
    public Category updateCategory(Principal principal, Category category) {
        throw new UnsupportedOperationException();
    }

    @Override
    @PostMapping("/deleteCategory")
    public void deleteCategory(Principal principal, Long id) {
        throw new UnsupportedOperationException();
    }

    @Override
    @PostMapping("/userInfo")
    public Map<String, String> userInfo(Principal principal) {
        throw new UnsupportedOperationException();
    }

    @Override
    @PostMapping("/procExcel")
    public void procExcel(Principal principal, MultipartFile excel) {
        throw new UnsupportedOperationException();
    }
}
