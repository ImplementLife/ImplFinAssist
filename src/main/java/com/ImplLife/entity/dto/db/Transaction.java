package com.ImplLife.entity.dto.db;

import lombok.*;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

@Entity
@Table(name = "fa_transaction")
public class Transaction {
    //region Fields
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToMany(cascade = {CascadeType.ALL})
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Comment> comments;
    private Date date;
    private Double value;
    private Boolean isBilling;
    @Transient
    private List<Category> categories;

    private String catIds; //JSON array
    private Long userId;
    @Transient
    private Long number;
    //endregion
}
