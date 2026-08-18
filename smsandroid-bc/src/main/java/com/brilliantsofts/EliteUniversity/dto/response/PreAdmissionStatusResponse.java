package com.brilliantsofts.EliteUniversity.dto.response;

import lombok.Data;

@Data
public class PreAdmissionStatusResponse {
    private String registrationNumber;
    private String firstName;
    private String lastName;
    private String email;
    private String status;
}
