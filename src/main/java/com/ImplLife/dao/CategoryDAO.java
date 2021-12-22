package com.ImplLife.dao;

import com.ImplLife.entity.dto.db.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryDAO extends JpaRepository<Category, Long>  {
}
