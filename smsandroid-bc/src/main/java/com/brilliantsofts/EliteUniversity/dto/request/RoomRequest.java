package com.brilliantsofts.EliteUniversity.dto.request;

import lombok.Data;
import java.math.BigDecimal;

@Data
public class RoomRequest {
    private String roomNumber;
    private Integer floor;
    private Integer capacity;
    private Integer currentOccupancy;
    private String roomType;
    private BigDecimal monthlyRent;
    private boolean isAvailable;
    private Long hostelId;
}
