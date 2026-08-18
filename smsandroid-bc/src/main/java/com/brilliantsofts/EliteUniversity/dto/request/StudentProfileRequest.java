package com.brilliantsofts.EliteUniversity.dto.request;

import lombok.Data;

@Data
public class StudentProfileRequest {
    private Long studentId;
    private String address;
    private String city;
    private String state;
    private String zipCode;
    private String nationality;
    private String bloodGroup;
    private String emergencyContact;
    private String emergencyContactName;
    private String medicalInfo;
}
