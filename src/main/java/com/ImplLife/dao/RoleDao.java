package com.ImplLife.dao;


import com.ImplLife.entity.dto.db.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleDao extends JpaRepository<Role, Long> {
}
