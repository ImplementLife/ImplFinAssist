package com.ImplLife.entity.dto.endpoints;

import com.ImplLife.entity.dto.db.*;
import java.util.List;

public class UserData {
    private Long id;
    private String email;
    private String username;

    private List<Transaction> transactions;
    private List<Category> categories;
    private Category lastSelectedCategory;
    private List<People> peoples;
    private List<Group> groups;
    private List<Requisition> requisitions;
}
