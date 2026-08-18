package com.brilliantsofts.EliteUniversity.dto.response;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class GuardianResponse {
    private Long id;
    private String uniqueCode;
    private Long studentId;
    private String firstName;
    private String lastName;
    private String relationship;
    private String email;
    private String phone;
    private String occupation;
    private String address;
    private Boolean isPrimary;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
