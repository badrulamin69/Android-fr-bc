package com.brilliantsofts.EliteUniversity.dto.request;

import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class TeacherRequest {
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

    // Login & Role fields
    private String username;
    private String password;
    private String role;
}
