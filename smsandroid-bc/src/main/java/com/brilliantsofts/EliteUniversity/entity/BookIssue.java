package com.brilliantsofts.EliteUniversity.entity;

import com.brilliantsofts.EliteUniversity.enums.BookIssueStatus;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Table(name = "book_issues")
@Data
public class BookIssue {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate issueDate;

    private LocalDate dueDate;

    private LocalDate returnDate;

    private Double fine;

    @Enumerated(EnumType.STRING)
    private BookIssueStatus status;

    @ManyToOne
    @JoinColumn(name = "book_id", nullable = false)
    private Book book;

    @ManyToOne
    @JoinColumn(name = "student_id", nullable = false)
    private Student student;
}
