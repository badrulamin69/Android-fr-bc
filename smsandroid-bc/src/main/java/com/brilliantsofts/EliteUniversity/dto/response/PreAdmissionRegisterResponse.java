package com.brilliantsofts.EliteUniversity.dto.response;

import lombok.Data;

@Data
public class PreAdmissionRegisterResponse {
    private Long id;
    private String registrationNumber;
    private String trackingNumber;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String loginEmail;
    private String tempPassword;
    private boolean passwordProvided;
    private String status;
}
