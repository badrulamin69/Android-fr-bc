package com.brilliantsofts.EliteUniversity.dto.request;

import lombok.Data;

@Data
public class FeeTypeRequest {
    private String name;
    private String code;
    private String category;
    private String description;
    private Double defaultAmount;
    private Boolean isActive;
}
