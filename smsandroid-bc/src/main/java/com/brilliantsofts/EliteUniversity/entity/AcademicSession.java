package com.brilliantsofts.EliteUniversity.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "academic_sessions")
@Data
public class AcademicSession {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String sessionName;

    private LocalDate startDate;

    private LocalDate endDate;

    private boolean active;

    @OneToMany(mappedBy = "academicSession")
    private List<Student> students = new ArrayList<>();
}
