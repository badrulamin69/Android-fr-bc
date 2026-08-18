package com.brilliantsofts.EliteUniversity.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "books")
@Data
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String isbn;

    private String author;

    private Integer quantity;

    private Integer availableQuantity;


    @OneToMany(mappedBy = "book")
    private List<BookIssue> issues = new ArrayList<>();
}
