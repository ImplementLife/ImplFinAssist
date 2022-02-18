package com.ImplLife.dao;

import com.ImplLife.entity.dto.db.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentDAO extends JpaRepository<Comment, Long> {
}
