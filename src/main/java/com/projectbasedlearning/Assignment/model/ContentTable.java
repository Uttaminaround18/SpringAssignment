package com.projectbasedlearning.Assignment.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "content_table")
public class ContentTable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "my_key")
    String myKey;

    @Column(name = "my_value")
    String myValue;
}
