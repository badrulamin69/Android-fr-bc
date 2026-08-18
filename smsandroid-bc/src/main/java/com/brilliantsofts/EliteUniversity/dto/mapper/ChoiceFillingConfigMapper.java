package com.brilliantsofts.EliteUniversity.dto.mapper;

import com.brilliantsofts.EliteUniversity.dto.request.ChoiceFillingConfigRequest;
import com.brilliantsofts.EliteUniversity.dto.response.ChoiceFillingConfigResponse;
import com.brilliantsofts.EliteUniversity.entity.ChoiceFillingConfig;

public class ChoiceFillingConfigMapper {
    public static ChoiceFillingConfig toEntity(ChoiceFillingConfigRequest request) {
        ChoiceFillingConfig entity = new ChoiceFillingConfig();
        entity.setSessionId(request.getSessionId());
        entity.setChoiceStartDate(request.getChoiceStartDate());
        entity.setChoiceEndDate(request.getChoiceEndDate());
        entity.setMaxChoices(request.getMaxChoices());
        entity.setMinChoices(request.getMinChoices());
        entity.setAllowEditingBeforeDeadline(request.getAllowEditingBeforeDeadline());
        entity.setAutoLockAfterDeadline(request.getAutoLockAfterDeadline());
        entity.setIncludeWaitingList(request.getIncludeWaitingList());
        entity.setStatus(request.getStatus());
        entity.setRemarks(request.getRemarks());
        entity.setIsActive(request.getIsActive());
        return entity;
    }

    public static ChoiceFillingConfigResponse toResponse(ChoiceFillingConfig entity) {
        ChoiceFillingConfigResponse response = new ChoiceFillingConfigResponse();
        response.setId(entity.getId());
        response.setSessionId(entity.getSessionId());
        response.setChoiceStartDate(entity.getChoiceStartDate());
        response.setChoiceEndDate(entity.getChoiceEndDate());
        response.setMaxChoices(entity.getMaxChoices());
        response.setMinChoices(entity.getMinChoices());
        response.setAllowEditingBeforeDeadline(entity.getAllowEditingBeforeDeadline());
        response.setAutoLockAfterDeadline(entity.getAutoLockAfterDeadline());
        response.setIncludeWaitingList(entity.getIncludeWaitingList());
        response.setStatus(entity.getStatus());
        response.setRemarks(entity.getRemarks());
        response.setIsActive(entity.getIsActive());
        response.setCreatedAt(entity.getCreatedAt());
        response.setUpdatedAt(entity.getUpdatedAt());
        return response;
    }
}
