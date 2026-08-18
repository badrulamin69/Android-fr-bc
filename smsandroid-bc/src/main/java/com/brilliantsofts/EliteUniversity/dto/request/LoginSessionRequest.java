package com.brilliantsofts.EliteUniversity.dto.request;

import lombok.Data;

@Data
public class LoginSessionRequest {
    private Long userId;
    private String sessionToken;
    private String ipAddress;
    private String browser;
    private String operatingSystem;
    private String deviceType;
}
