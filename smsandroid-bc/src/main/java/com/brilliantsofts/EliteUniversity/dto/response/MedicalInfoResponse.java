package com.brilliantsofts.EliteUniversity.dto.response;

import lombok.Data;

@Data
public class MedicalInfoResponse {
    private Long id;
    private String uniqueCode;
    private Long studentId;
    private String bloodGroup;
    private Double height;
    private Double weight;
    private String allergies;
    private String medications;
    private String conditions;
    private String emergencyContact;
    private String emergencyPhone;
    private String insuranceProvider;
    private String insuranceNumber;
    private String doctorName;
    private String doctorPhone;
    private String notes;
}
