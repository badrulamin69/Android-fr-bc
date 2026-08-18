package com.brilliantsofts.EliteUniversity.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "teacher_promotions")
@Data
public class TeacherPromotion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String uniqueCode;

    private Long teacherId;

    private String previousDesignation;

    private String newDesignation;

    private String previousDepartment;

    private String newDepartment;

    private String previousSalaryGrade;

    private String newSalaryGrade;

    private BigDecimal previousSalary;

    private BigDecimal newSalary;

    private LocalDate promotionDate;

    private String reason;

    private Long approvedBy;

    private String approvedByName;

    private String status;

    private String remarks;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;
}
