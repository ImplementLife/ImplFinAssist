package com.ImplLife.entity.dto.endpoints;

import com.ImplLife.entity.dto.db.Category;
import com.ImplLife.entity.dto.db.Comment;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder(toBuilder = true)
public class TransactionDto {
    private List<Comment> comments;
    private List<Category> categoriesId;
}
