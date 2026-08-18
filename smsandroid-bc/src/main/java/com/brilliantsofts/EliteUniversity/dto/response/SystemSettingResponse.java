package com.brilliantsofts.EliteUniversity.dto.response;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class SystemSettingResponse {
    private Long id;
    private String settingKey;
    private String settingValue;
    private String settingModule;
    private String description;
    private String dataType;
    private boolean isPublic;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
