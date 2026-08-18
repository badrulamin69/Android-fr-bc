package com.brilliantsofts.EliteUniversity.dto.response;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class ExamCenterResponse {
    private Long id;
    private String name;
    private String code;
    private String address;
    private String city;
    private Integer totalCapacity;
    private String contactPerson;
    private String contactPhone;
    private boolean active;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
