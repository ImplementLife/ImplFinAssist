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
@Table(name = "fa_receipt")
public class Receipt {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "receipt", fetch = FetchType.LAZY)
    private List<ReceiptItem> receiptItems;

    @ManyToOne
    @JoinColumn(name = "user_owner_id")
    private User owner;

    @ElementCollection
    @CollectionTable(name = "fa_receipt_images", joinColumns = @JoinColumn(name = "receipt_owner_id"))
    @Column(name = "image_name")
    private List<String> images;
}
