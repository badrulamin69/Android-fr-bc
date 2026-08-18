package com.brilliantsofts.EliteUniversity.dto.response;

import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class CampusResponse {
    private Long id;
    private String uniqueCode;
    private String name;
    private String code;
    private String address;
    private String phone;
    private String email;
    private String campusType;
    private BigDecimal latitude;
    private BigDecimal longitude;
    private boolean isActive;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
