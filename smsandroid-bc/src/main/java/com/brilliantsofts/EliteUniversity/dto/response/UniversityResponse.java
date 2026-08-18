package com.brilliantsofts.EliteUniversity.dto.response;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class UniversityResponse {
    private Long id;
    private String uniqueCode;
    private String name;
    private String code;
    private String address;
    private String phone;
    private String email;
    private String website;
    private String logoUrl;
    private Integer establishedYear;
    private String motto;
    private String description;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
