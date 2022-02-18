package com.ImplLife.entity.dto.db;

import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

@Entity
@Table(name = "fa_comment")
public class Comment {
    //region Fields
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String value;
    private Date date;
    @ManyToOne
    @JoinColumn(name = "transaction_id")
    private Transaction transaction;
    //endregion
}
