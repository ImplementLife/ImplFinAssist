package com.ImplLife.entity.dto.db;

import lombok.*;

import javax.persistence.*;


@AllArgsConstructor
@NoArgsConstructor
@Data

@Entity
@Table(name = "fa_category")
@Builder(toBuilder = true)
public class Category {
    public static final Category DEFAULT_MANUAL = builder().id(-1L).name("DEFAULT_MANUAL").build();
    public static final Category DEFAULT_EXCEL = builder().id(-2L).name("DEFAULT_EXCEL").build();

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
}
