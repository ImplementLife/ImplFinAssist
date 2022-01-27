package com.ImplLife.entity.dto.db;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter

@Entity
@Table(name = "fa_comment")
@Builder(toBuilder = true)
public class Comment {
    //region Fields
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String value;
    private Date date;
    //endregion
}
