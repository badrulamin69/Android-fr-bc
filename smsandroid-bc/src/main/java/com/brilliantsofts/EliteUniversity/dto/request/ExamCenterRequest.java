package com.brilliantsofts.EliteUniversity.dto.request;

import lombok.Data;

@Data
public class ExamCenterRequest {
    private String name;
    private String code;
    private String address;
    private String city;
    private Integer totalCapacity;
    private String contactPerson;
    private String contactPhone;
    private boolean active;
}
