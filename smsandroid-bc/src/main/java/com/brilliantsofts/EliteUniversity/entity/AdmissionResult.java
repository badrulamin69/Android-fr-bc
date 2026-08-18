package com.brilliantsofts.EliteUniversity.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Table(name = "admission_results")
@Data
public class AdmissionResult {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double admissionScore;

    private Integer meritPosition;

    private String resultStatus;

    private LocalDate resultDate;

    @OneToOne
    @JoinColumn(name = "applicant_id", unique = true)
    private Applicant applicant;

    @ManyToOne
    @JoinColumn(name = "program_id")
    private Program program;
}
