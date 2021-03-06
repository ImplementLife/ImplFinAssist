package com.ImplLife.entity.dto.db;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

@Entity
@Table(name = "fa_people")
public class People {
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

    @ManyToOne
    @JoinColumn(name = "linked_user_id")
    private User linkedUser;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "fa_groups_peoples",
            joinColumns = { @JoinColumn(name = "people_id") },
            inverseJoinColumns = { @JoinColumn(name = "group_id") }
    )
    private List<Group> groups;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "fa_requisitions_peoples",
            joinColumns = { @JoinColumn(name = "people_id") },
            inverseJoinColumns = { @JoinColumn(name = "requisition_id") }
    )
    private List<Requisition> requisitions;
}
