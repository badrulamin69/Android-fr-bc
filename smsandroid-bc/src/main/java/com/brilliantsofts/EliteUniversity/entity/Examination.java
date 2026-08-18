package com.brilliantsofts.EliteUniversity.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "examinations")
@Data
public class Examination {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String examinationName;

    private String semester;

    private Double totalMarks;

    private Double passMarks;

    private LocalDate examinationDate;

    @ManyToOne
    @JoinColumn(name = "course_id", nullable = false)
    private Course course;

    @OneToMany(mappedBy = "examination", cascade = CascadeType.ALL)
    private List<ExaminationResult> results = new ArrayList<>();
}
