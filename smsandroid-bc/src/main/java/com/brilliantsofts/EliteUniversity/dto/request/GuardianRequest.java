package com.brilliantsofts.EliteUniversity.dto.request;

import lombok.Data;

@Data
public class GuardianRequest {
    private Long studentId;
    private String firstName;
    private String lastName;
    private String relationship;
    private String email;
    private String phone;
    private String occupation;
    private String address;
    private Boolean isPrimary;
}
