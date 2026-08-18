package com.brilliantsofts.EliteUniversity.dto.request;

import lombok.Data;

@Data
public class SystemSettingRequest {
    private String settingKey;
    private String settingValue;
    private String settingModule;
    private String description;
    private String dataType;
    private boolean isPublic;
}
