package com.ImplLife.dao;

import com.ImplLife.entity.dto.db.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDAO extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
