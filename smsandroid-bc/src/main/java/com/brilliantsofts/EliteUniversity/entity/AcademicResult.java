package com.brilliantsofts.EliteUniversity.entity;

import com.brilliantsofts.EliteUniversity.enums.AcademicExamType;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "academic_results")
@Data
public class AcademicResult {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private AcademicExamType examType;

    private String board;

    private String institutionName;

    private String rollNumber;

    private String registrationNumber;

    private Integer passingYear;

    private Double gpa;

    private String resultDocument;

    @ManyToOne
    @JoinColumn(name = "applicant_id", nullable = false)
    private Applicant applicant;
}
