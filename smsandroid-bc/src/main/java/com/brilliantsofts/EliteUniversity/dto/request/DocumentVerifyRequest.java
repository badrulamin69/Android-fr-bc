package com.brilliantsofts.EliteUniversity.dto.request;

import lombok.Data;

@Data
public class DocumentVerifyRequest {
    private Boolean verified;
    private String remarks;
}
