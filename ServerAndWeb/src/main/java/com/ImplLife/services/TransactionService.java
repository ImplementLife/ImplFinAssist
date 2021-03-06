package com.ImplLife.services;

import com.ImplLife.dao.CategoryDAO;
import com.ImplLife.dao.TransactionDAO;
import com.ImplLife.dao.UserDAO;
import com.ImplLife.entity.dto.db.Category;
import com.ImplLife.entity.dto.db.Transaction;
import com.ImplLife.entity.dto.db.User;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

import static com.ImplLife.entity.dto.db.Category.DEFAULT_EXCEL;
import static com.ImplLife.entity.dto.db.Category.DEFAULT_MANUAL;

@Service
public class TransactionService {
    @Autowired
    private TransactionDAO transactionDAO;
    @Autowired
    private CategoryDAO categoryDAO;
    @Autowired
    private UserDAO userDAO;
    @Autowired
    private EntityManager entityManager;

    private Transaction loadCategories(Transaction transaction) {
        try {
            String catIds = transaction.getCatIds();
            JSONArray array = (JSONArray) new JSONParser().parse(catIds);
            List<Category> trCat = new ArrayList<>();
            for (Object o : array) {
                long l = Long.parseLong(o.toString());
                Optional<Category> byId = categoryDAO.findById(l);
                trCat.add(byId.get());
            }
            transaction.setCategories(trCat);
            return transaction;
        } catch (ParseException e) {
            throw new RuntimeException("Error with view saved categories ids", e);
        }
    }

    private Transaction prepareToSaveWithCat(Transaction transaction) {
        try {
            JSONArray array;
            if (transaction.getCatIds() != null) {
                array = (JSONArray) new JSONParser().parse(transaction.getCatIds());
            } else {
                array = new JSONArray();
            }

            for (Category category : transaction.getCategories()) {
                array.add(category.getId());
            }
            transaction.setCatIds(array.toJSONString());
            return transaction;
        } catch (ParseException e) {
            throw new RuntimeException("Error with view saved categories ids", e);
        }
    }

    public List<Transaction> getAllTransactions(User user) {
        List<Transaction> byUserId = transactionDAO.findByOwnerId(user.getId());
        for (Transaction transaction : byUserId) {
            loadCategories(transaction);
        }
        return byUserId;
    }

    public List<Transaction> searchTransactions(User user, Transaction searchCriteria) {
        throw new UnsupportedOperationException();
    }

    public Transaction getSimpleTransaction(User user, Long id) {
        if (!userTransactionsIds(user).contains(id)) throw new AccessDeniedException("This user not have access to this transaction");
        Transaction transaction = transactionDAO.findById(id).orElseThrow(() -> new IllegalArgumentException("Transaction with id -> " + id + " is not exist"));
        return loadCategories(transaction);
    }

    public Transaction addTransaction(User user, Transaction transaction) {
        transaction.setOwner(user);
        prepareToSaveWithCat(transaction);
        transactionDAO.saveAndFlush(transaction);
        return transaction;
    }

    private List<Long> userTransactionsIds(User user) { // TODO: 26.12.2021 replace to sql query
        List<Transaction> userTransactions = user.getTransactions();
        List<Long> ids = new LinkedList<>();
        userTransactions.forEach(ut -> ids.add(ut.getId()));
        return ids;
    }

    private Map<Long, Transaction> userTransactionMap(User user) {
        return user.getTransactions().stream()
                .collect(Collectors.toMap(Transaction::getId, Function.identity()));
    }

    public void deleteTransaction(User user, List<Long> extId) {
        Map<Long, Transaction> userTransactionMap = userTransactionMap(user);
        List<Long> ids = userTransactionsIds(user);
        List<Transaction> finalList = new LinkedList<>();
        extId.forEach(ext -> {
            if (ids.contains(ext)) finalList.add(userTransactionMap.get(ext));
            else throw new IllegalArgumentException("This user do not have transaction with this id -> " + ext);
        });
        transactionDAO.deleteAll(finalList);
    }

    public void deleteTransaction(User user, Long extId) {
        List<Long> ids = userTransactionsIds(user);
        if (ids.contains(extId)) transactionDAO.deleteById(extId);
        else throw new IllegalArgumentException("This user do not have transaction with this id -> " + extId);
    }

    public void deleteAllTransaction(User user) { // TODO: 26.12.2021 split delete on excel or manual input
        transactionDAO.deleteAll(user.getTransactions());
    }

    public List<Category> getAllCategories(User user) {
        return user.getCategories();
    }

    public Category getLastSelectedCategory(User user) {
        return user.getLastSelectedCategory();
    }

    public void setLastSelectedCategory(User user, Category category) {
        user.setLastSelectedCategory(category);
        userDAO.save(user);
    }

    private Category getById(Long id) {
        Optional<Category> byId = categoryDAO.findById(id);
        return byId.orElseThrow(() -> new IllegalArgumentException("Category with id -> " + id + " was not find."));
    }

    private Map<String, Category> filterByUser(List<Category> list, User user) {
        Map<String, Category> collectByUser = list.stream().filter(c -> {
            return user.getCategories().contains(c);
        }).collect(Collectors.toMap(Category::getName, Function.identity()));
        return collectByUser;
    }

    public Category addNewCategory(User user, String name) {
        Category category = new Category();
        category.setName(name);
        List<Category> categories = new LinkedList<>(user.getCategories());
        categories.add(category);
        user.setCategories(categories);
        userDAO.save(user);
        User newUser = userDAO.findById(user.getId()).get();
        List<Category> allByName = categoryDAO.findAllByName(name);
        Map<String, Category> collectByUser = filterByUser(allByName, newUser);
        return collectByUser.get(name);
    }

    public void updateCategory(User user, Long id, String newName) {
        Category category = getById(id);
        if (user.getCategories().contains(category)) {
            category.setName(newName);
            categoryDAO.save(category);
        }
    }

    private void throwIfDefault(Category category) {
        if (DEFAULT_MANUAL.equals(category)) throw new IllegalArgumentException("DEFAULT_MANUAL can not be renamed (default final category)");
        if (DEFAULT_EXCEL.equals(category)) throw new IllegalArgumentException("DEFAULT_EXCEL can not be renamed (default final category)");
    }

    public void deleteCategory(User user, Long id) {
        Category category = getById(id);
        throwIfDefault(category);
        if (user.getCategories().contains(category)) {
            replaceCategory(user.getTransactions(), category, DEFAULT_MANUAL); // TODO: 26.12.2021 need add check if DEFAULT_EXCEL...

        }
    }

    public List<Transaction> filterByCategory(List<Transaction> transactions, Category category) {
//        List<Transaction> transWithCat = transactions
//                .stream().filter(tr -> tr.getCategories()
//                        .stream().anyMatch(cat -> cat.getId().equals(category.getId())))
//                .collect(Collectors.toList());
        throw new UnsupportedOperationException();
    }

    private void replaceCategory(List<Transaction> transactions, Category oldCat, Category newCat) {
        if (oldCat.equals(newCat)) throw new IllegalArgumentException("old category equals new, replace do not need");
        List<Transaction> transWithCat = transactions
                .stream().filter(tr -> tr.getCategories()
                        .stream().anyMatch(cat -> cat.getId().equals(oldCat.getId())))
                .collect(Collectors.toList());
        transWithCat.forEach(tr -> {
            HashSet<Category> categorySet = new HashSet<>(tr.getCategories());
            categorySet.remove(oldCat);
            categorySet.add(newCat);
        });
    }

}
