package com.brilliantsofts.EliteUniversity.dto.response;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class LoginSessionResponse {
    private Long id;
    private String uniqueCode;
    private Long userId;
    private String username;
    private String sessionToken;
    private String ipAddress;
    private String browser;
    private String operatingSystem;
    private String deviceType;
    private LocalDateTime loginTime;
    private LocalDateTime lastActivityTime;
    private LocalDateTime logoutTime;
    private boolean isActive;
    private boolean expired;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
