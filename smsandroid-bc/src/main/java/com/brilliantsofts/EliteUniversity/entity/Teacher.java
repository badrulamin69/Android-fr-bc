package com.brilliantsofts.EliteUniversity.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "teachers")
@Data
public class Teacher {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String uniqueCode;

    @Column(unique = true, nullable = false)
    private String teacherCode;

    private String firstName;

    private String lastName;

    private String gender;

    private LocalDate dateOfBirth;

    private String bloodGroup;

    private String nationalId;

    private String passport;

    private String nationality;

    private String religion;

    private String maritalStatus;

    private String photo;

    @Column(unique = true)
    private String email;

    private String phone;

    private String emergencyContact;

    private String presentAddress;

    private String permanentAddress;

    private LocalDate joiningDate;

    private String employmentStatus;

    private String employmentType;

    private String designation;

    private Long departmentId;

    private Long facultyId;

    private String officeRoom;

    private String campus;

    private String highestDegree;

    private String university;

    private String specialization;

    private String experience;

    private String certifications;

    private String assignedCourses;

    private String sections;

    private String semester;

    private String creditLoad;

    private String googleScholar;

    private String orcid;

    private String salaryGrade;

    private BigDecimal basicSalary;

    private String bankInformation;

    private String taxId;

    private String status;

    private Long userId;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;
}
