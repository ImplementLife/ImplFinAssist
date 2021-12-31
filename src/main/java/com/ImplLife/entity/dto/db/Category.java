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
@Builder(toBuilder = true)
public class Category {
    @Transient
    public static final Category DEFAULT_MANUAL = builder().id(-1L).name("DEFAULT_MANUAL").build();
    @Transient
    public static final Category DEFAULT_EXCEL = builder().id(-2L).name("DEFAULT_EXCEL").build();

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

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
}
