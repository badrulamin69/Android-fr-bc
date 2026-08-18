package com.brilliantsofts.EliteUniversity.dto.mapper;

import com.brilliantsofts.EliteUniversity.dto.request.InvoiceItemRequest;
import com.brilliantsofts.EliteUniversity.dto.response.InvoiceItemResponse;
import com.brilliantsofts.EliteUniversity.entity.InvoiceItem;

public class InvoiceItemMapper {
    public static InvoiceItem toEntity(InvoiceItemRequest request) {
        InvoiceItem entity = new InvoiceItem();
        entity.setFeeTypeId(request.getFeeTypeId());
        entity.setDescription(request.getDescription());
        entity.setAmount(request.getAmount());
        entity.setDiscountAmount(request.getDiscountAmount());
        entity.setNetAmount(request.getNetAmount());
        return entity;
    }

    public static InvoiceItemResponse toResponse(InvoiceItem entity) {
        InvoiceItemResponse response = new InvoiceItemResponse();
        response.setId(entity.getId());
        response.setInvoiceId(entity.getInvoiceId());
        response.setFeeTypeId(entity.getFeeTypeId());
        response.setDescription(entity.getDescription());
        response.setAmount(entity.getAmount());
        response.setDiscountAmount(entity.getDiscountAmount());
        response.setNetAmount(entity.getNetAmount());
        response.setCreatedAt(entity.getCreatedAt());
        response.setUpdatedAt(entity.getUpdatedAt());
        return response;
    }
}
