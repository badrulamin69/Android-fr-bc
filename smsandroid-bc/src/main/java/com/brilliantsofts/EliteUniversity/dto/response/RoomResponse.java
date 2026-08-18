package com.brilliantsofts.EliteUniversity.dto.response;

import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class RoomResponse {
    private Long id;
    private String uniqueCode;
    private String roomNumber;
    private Integer floor;
    private Integer capacity;
    private Integer currentOccupancy;
    private String roomType;
    private BigDecimal monthlyRent;
    private boolean isAvailable;
    private Long hostelId;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
