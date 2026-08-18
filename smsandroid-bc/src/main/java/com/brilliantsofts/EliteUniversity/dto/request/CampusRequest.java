package com.brilliantsofts.EliteUniversity.dto.request;

import lombok.Data;
import java.math.BigDecimal;

@Data
public class CampusRequest {
    private String name;
    private String code;
    private String address;
    private String phone;
    private String email;
    private String campusType;
    private BigDecimal latitude;
    private BigDecimal longitude;
    private boolean isActive;
}
