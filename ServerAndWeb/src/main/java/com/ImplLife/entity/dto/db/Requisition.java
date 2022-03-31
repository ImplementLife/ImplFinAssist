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
@Table(name = "fa_requisition")
public class Requisition {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Boolean released;

    @ManyToOne
    @JoinColumn(name = "user_owner_id")
    private User owner;

    @ManyToMany
    private List<People> peoples;

    @OneToMany
    private List<Receipt> receipts;

}
