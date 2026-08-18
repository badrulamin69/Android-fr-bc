package com.brilliantsofts.EliteUniversity.dto.response;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class SectionResponse {
    private Long id;
    private String uniqueCode;
    private String name;
    private String code;
    private Long batchId;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
