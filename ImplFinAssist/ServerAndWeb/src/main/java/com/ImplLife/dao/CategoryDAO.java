package com.ImplLife.dao;

import com.ImplLife.entity.dto.db.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface CategoryDAO extends JpaRepository<Category, Long>  {
    List<Category> findAllByName(String name);
}
