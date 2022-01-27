package com.ImplLife.entity.dto.db;

import lombok.*;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter

@Entity
@Table(name = "fa_category")
@Builder(toBuilder = true)
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
}
