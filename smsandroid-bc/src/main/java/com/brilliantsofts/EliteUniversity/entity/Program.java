package com.brilliantsofts.EliteUniversity.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "programs")
@Data
public class Program {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(unique = true)
    private String code;

    private Integer durationYears;

    private Integer totalCredits;

    @ManyToOne
    @JoinColumn(name = "department_id", nullable = false)
    private Department department;

    @OneToMany(mappedBy = "program")
    private List<Student> students = new ArrayList<>();

    @OneToMany(mappedBy = "program")
    private List<Applicant> applicants = new ArrayList<>();

    @OneToMany(mappedBy = "program")
    private List<AdmissionResult> admissionResults = new ArrayList<>();

    @OneToMany(mappedBy = "program")
    private List<Course> courses = new ArrayList<>();
}
