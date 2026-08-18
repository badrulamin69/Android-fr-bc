package com.brilliantsofts.EliteUniversity.dto.mapper;

import com.brilliantsofts.EliteUniversity.dto.request.CreditRuleRequest;
import com.brilliantsofts.EliteUniversity.dto.response.CreditRuleResponse;
import com.brilliantsofts.EliteUniversity.entity.CreditRule;

public class CreditRuleMapper {
    public static CreditRule toEntity(CreditRuleRequest request) {
        CreditRule entity = new CreditRule();
        entity.setProgramId(request.getProgramId());
        entity.setMinCreditsPerSemester(request.getMinCreditsPerSemester());
        entity.setMaxCreditsPerSemester(request.getMaxCreditsPerSemester());
        entity.setTotalRequiredCredits(request.getTotalRequiredCredits());
        entity.setMaxTransferCredits(request.getMaxTransferCredits());
        entity.setMaxElectiveCredits(request.getMaxElectiveCredits());
        entity.setDescription(request.getDescription());
        return entity;
    }

    public static CreditRuleResponse toResponse(CreditRule entity) {
        CreditRuleResponse response = new CreditRuleResponse();
        response.setId(entity.getId());
        response.setUniqueCode(entity.getUniqueCode());
        response.setProgramId(entity.getProgramId());
        response.setMinCreditsPerSemester(entity.getMinCreditsPerSemester());
        response.setMaxCreditsPerSemester(entity.getMaxCreditsPerSemester());
        response.setTotalRequiredCredits(entity.getTotalRequiredCredits());
        response.setMaxTransferCredits(entity.getMaxTransferCredits());
        response.setMaxElectiveCredits(entity.getMaxElectiveCredits());
        response.setDescription(entity.getDescription());
        response.setCreatedAt(entity.getCreatedAt());
        response.setUpdatedAt(entity.getUpdatedAt());
        return response;
    }
}
