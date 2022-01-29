package com.ImplLife.entity.dto.db;

import lombok.*;

import javax.persistence.*;
import java.util.Objects;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

@Entity
@Table(name = "fa_category")
public class Category {
    @Transient
    public static final Category DEFAULT_MANUAL = new Category(-1L, "DEFAULT_MANUAL");
    @Transient
    public static final Category DEFAULT_EXCEL = new Category(-2L, "DEFAULT_EXCEL");

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @ManyToOne
    @JoinColumn(name = "user_owner_id")
    private User owner;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Category category = (Category) o;
        return Objects.equals(id, category.id) && Objects.equals(name, category.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    public Category(Long id, String name) {
        this.id = id;
        this.name = name;
    }
}
