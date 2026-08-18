package com.brilliantsofts.EliteUniversity.dto.mapper;

import com.brilliantsofts.EliteUniversity.dto.request.DiscountRequest;
import com.brilliantsofts.EliteUniversity.dto.response.DiscountResponse;
import com.brilliantsofts.EliteUniversity.entity.Discount;

public class DiscountMapper {
    public static Discount toEntity(DiscountRequest request) {
        Discount entity = new Discount();
        entity.setStudentId(request.getStudentId());
        entity.setFeeTypeId(request.getFeeTypeId());
        entity.setDiscountType(request.getDiscountType());
        entity.setDiscountValue(request.getDiscountValue());
        entity.setDescription(request.getDescription());
        entity.setValidFrom(request.getValidFrom());
        entity.setValidTo(request.getValidTo());
        entity.setIsActive(request.getIsActive());
        return entity;
    }

    public static DiscountResponse toResponse(Discount entity) {
        DiscountResponse response = new DiscountResponse();
        response.setId(entity.getId());
        response.setStudentId(entity.getStudentId());
        response.setFeeTypeId(entity.getFeeTypeId());
        response.setDiscountType(entity.getDiscountType());
        response.setDiscountValue(entity.getDiscountValue());
        response.setDescription(entity.getDescription());
        response.setValidFrom(entity.getValidFrom());
        response.setValidTo(entity.getValidTo());
        response.setIsActive(entity.getIsActive());
        response.setCreatedAt(entity.getCreatedAt());
        response.setUpdatedAt(entity.getUpdatedAt());
        return response;
    }
}
