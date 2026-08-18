package com.brilliantsofts.EliteUniversity.dto.response;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class FeeTypeResponse {
    private Long id;
    private String name;
    private String code;
    private String category;
    private String description;
    private Double defaultAmount;
    private Boolean isActive;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
