package com.brilliantsofts.EliteUniversity.dto.request;

import lombok.Data;

@Data
public class ClassroomRequest {
    private Long buildingId;
    private String roomNumber;
    private Integer floor;
    private Integer capacity;
    private String roomType;
    private boolean isLab;
    private boolean isSmartClassroom;
    private boolean hasProjector;
    private boolean hasWhiteboard;
    private boolean hasWifi;
    private String equipment;
    private boolean isAvailable;
    private boolean isActive;
    private String remarks;
}
