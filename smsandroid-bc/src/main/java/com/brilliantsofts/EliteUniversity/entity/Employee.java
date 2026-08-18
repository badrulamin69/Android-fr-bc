package com.brilliantsofts.EliteUniversity.entity;

import com.brilliantsofts.EliteUniversity.enums.EmployeeType;
import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "employees")
@Data
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String employeeId;

    private String fullName;

    private String phone;

    private String designation;

    @Enumerated(EnumType.STRING)
    private EmployeeType employeeType;

    @OneToOne
    @JoinColumn(name = "user_id", unique = true)
    private User user;

    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;

    @OneToMany(mappedBy = "teacher")
    private List<LiveClass> liveClasses = new ArrayList<>();
}
