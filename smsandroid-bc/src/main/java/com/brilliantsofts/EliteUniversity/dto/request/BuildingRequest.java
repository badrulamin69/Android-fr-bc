package com.brilliantsofts.EliteUniversity.dto.request;

import lombok.Data;

@Data
public class BuildingRequest {
    private String name;
    private String code;
    private String description;
    private String address;
    private Integer totalFloors;
    private Integer totalRooms;
    private String contactPerson;
    private String contactPhone;
    private boolean isActive;
}
