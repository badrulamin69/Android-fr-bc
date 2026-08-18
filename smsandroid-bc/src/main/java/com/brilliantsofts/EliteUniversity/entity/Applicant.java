package com.brilliantsofts.EliteUniversity.entity;

import com.brilliantsofts.EliteUniversity.enums.ApplicationLevel;
import com.brilliantsofts.EliteUniversity.enums.ApplicationStatus;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "applicants")
@Data
public class Applicant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String applicationNumber;

    private String fullName;

    private String phone;

    private String address;

    @Enumerated(EnumType.STRING)
    private ApplicationLevel applicationLevel;

    @Enumerated(EnumType.STRING)
    private ApplicationStatus status;

    @OneToOne
    @JoinColumn(name = "user_id", unique = true)
    private User user;

    @ManyToOne
    @JoinColumn(name = "program_id")
    private Program program;

    @OneToMany(mappedBy = "applicant", cascade = CascadeType.ALL)
    private List<AcademicResult> academicResults = new ArrayList<>();

    @OneToOne(mappedBy = "applicant", cascade = CascadeType.ALL)
    private AdmissionResult admissionResult;

    @OneToMany(mappedBy = "applicant", cascade = CascadeType.ALL)
    private List<Payment> payments = new ArrayList<>();

    @OneToOne(mappedBy = "applicant")
    private Student student;
}
