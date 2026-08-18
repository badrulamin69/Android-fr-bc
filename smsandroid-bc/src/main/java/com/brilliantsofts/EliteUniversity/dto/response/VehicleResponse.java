package com.brilliantsofts.EliteUniversity.dto.response;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class VehicleResponse {
    private Long id;
    private String uniqueCode;
    private String vehicleNumber;
    private String vehicleType;
    private Integer capacity;
    private String driverName;
    private String driverPhone;
    private Boolean isActive;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
