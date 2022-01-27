package com.ImplLife.entity.dto.db;

import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter

@Entity
@Table(name = "fa_transaction")
@Builder(toBuilder = true)
public class Transaction {
    //region Fields
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToMany
    private List<Comment> comments;
    private Date date;
    private String value;
    @OneToMany
    private List<Category> categories;
    //endregion
}
