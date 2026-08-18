package com.brilliantsofts.EliteUniversity.dto.mapper;

import com.brilliantsofts.EliteUniversity.dto.request.SystemSettingRequest;
import com.brilliantsofts.EliteUniversity.dto.response.SystemSettingResponse;
import com.brilliantsofts.EliteUniversity.entity.SystemSetting;

public class SystemSettingMapper {
    public static SystemSetting toEntity(SystemSettingRequest request) {
        SystemSetting entity = new SystemSetting();
        entity.setSettingKey(request.getSettingKey());
        entity.setSettingValue(request.getSettingValue());
        entity.setSettingModule(request.getSettingModule());
        entity.setDescription(request.getDescription());
        entity.setDataType(request.getDataType());
        entity.setPublic(request.isPublic());
        return entity;
    }

    public static SystemSettingResponse toResponse(SystemSetting entity) {
        SystemSettingResponse response = new SystemSettingResponse();
        response.setId(entity.getId());
        response.setSettingKey(entity.getSettingKey());
        response.setSettingValue(entity.getSettingValue());
        response.setSettingModule(entity.getSettingModule());
        response.setDescription(entity.getDescription());
        response.setDataType(entity.getDataType());
        response.setPublic(entity.isPublic());
        response.setCreatedAt(entity.getCreatedAt());
        response.setUpdatedAt(entity.getUpdatedAt());
        return response;
    }
}
