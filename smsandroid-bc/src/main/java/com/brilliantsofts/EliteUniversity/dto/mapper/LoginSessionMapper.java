package com.brilliantsofts.EliteUniversity.dto.mapper;

import com.brilliantsofts.EliteUniversity.dto.response.LoginSessionResponse;
import com.brilliantsofts.EliteUniversity.entity.LoginSession;

public class LoginSessionMapper {
    public static LoginSessionResponse toResponse(LoginSession entity) {
        LoginSessionResponse response = new LoginSessionResponse();
        response.setId(entity.getId());
        response.setUniqueCode(entity.getUniqueCode());
        if (entity.getUser() != null) {
            response.setUserId(entity.getUser().getId());
            response.setUsername(entity.getUser().getUsername());
        }
        response.setSessionToken(entity.getSessionToken());
        response.setIpAddress(entity.getIpAddress());
        response.setBrowser(entity.getBrowser());
        response.setOperatingSystem(entity.getOperatingSystem());
        response.setDeviceType(entity.getDeviceType());
        response.setLoginTime(entity.getLoginTime());
        response.setLastActivityTime(entity.getLastActivityTime());
        response.setLogoutTime(entity.getLogoutTime());
        response.setActive(entity.isActive());
        response.setExpired(entity.isExpired());
        response.setCreatedAt(entity.getCreatedAt());
        response.setUpdatedAt(entity.getUpdatedAt());
        return response;
    }
}
