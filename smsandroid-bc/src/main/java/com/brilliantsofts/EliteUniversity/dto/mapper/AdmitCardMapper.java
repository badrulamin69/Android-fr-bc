package com.brilliantsofts.EliteUniversity.dto.mapper;

import com.brilliantsofts.EliteUniversity.dto.request.AdmitCardRequest;
import com.brilliantsofts.EliteUniversity.dto.response.AdmitCardResponse;
import com.brilliantsofts.EliteUniversity.entity.AdmitCard;

public class AdmitCardMapper {
    public static AdmitCard toEntity(AdmitCardRequest request) {
        AdmitCard entity = new AdmitCard();
        entity.setRegistrationId(request.getRegistrationId());
        entity.setTestId(request.getTestId());
        entity.setAdmitCardNumber(request.getAdmitCardNumber());
        entity.setRollNumber(request.getRollNumber());
        entity.setSeatNumber(request.getSeatNumber());
        entity.setCenterName(request.getCenterName());
        entity.setBuildingName(request.getBuildingName());
        entity.setRoomName(request.getRoomName());
        entity.setQrCode(request.getQrCode());
        entity.setIssuedAt(request.getIssuedAt());
        entity.setStatus(request.getStatus());
        return entity;
    }

    public static AdmitCardResponse toResponse(AdmitCard entity) {
        AdmitCardResponse response = new AdmitCardResponse();
        response.setId(entity.getId());
        response.setRegistrationId(entity.getRegistrationId());
        response.setTestId(entity.getTestId());
        response.setAdmitCardNumber(entity.getAdmitCardNumber());
        response.setRollNumber(entity.getRollNumber());
        response.setSeatNumber(entity.getSeatNumber());
        response.setCenterName(entity.getCenterName());
        response.setBuildingName(entity.getBuildingName());
        response.setRoomName(entity.getRoomName());
        response.setQrCode(entity.getQrCode());
        response.setIssuedAt(entity.getIssuedAt());
        response.setStatus(entity.getStatus());
        response.setCreatedAt(entity.getCreatedAt());
        response.setUpdatedAt(entity.getUpdatedAt());
        return response;
    }
}
