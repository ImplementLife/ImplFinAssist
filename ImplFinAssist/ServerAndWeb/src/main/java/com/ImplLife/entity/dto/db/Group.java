package com.ImplLife.entity.dto.db;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

@Entity
@Table(name = "fa_group")
public class Group {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private boolean hidden;
    private boolean primary;
    private int numInList;

    @ManyToOne
    @JoinColumn(name = "user_owner_id")
    private User owner;

    @ManyToMany(mappedBy = "groups")
    private List<People> members;
}
