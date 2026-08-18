package com.brilliantsofts.EliteUniversity.dto.request;

import lombok.Data;

import java.time.LocalDate;

@Data
public class DiscountRequest {
    private Long studentId;
    private Long feeTypeId;
    private String discountType;
    private Double discountValue;
    private String description;
    private LocalDate validFrom;
    private LocalDate validTo;
    private Boolean isActive;
}
