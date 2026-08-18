package com.brilliantsofts.EliteUniversity.dto.mapper;

import com.brilliantsofts.EliteUniversity.dto.request.SeatAllocationConfigRequest;
import com.brilliantsofts.EliteUniversity.dto.response.SeatAllocationConfigResponse;
import com.brilliantsofts.EliteUniversity.entity.SeatAllocationConfig;

public class SeatAllocationConfigMapper {
    public static SeatAllocationConfig toEntity(SeatAllocationConfigRequest request) {
        SeatAllocationConfig entity = new SeatAllocationConfig();
        entity.setSessionId(request.getSessionId());
        entity.setAcademicYear(request.getAcademicYear());
        entity.setAllocationRound(request.getAllocationRound());
        entity.setAutoAllocation(request.getAutoAllocation());
        entity.setManualAllocation(request.getManualAllocation());
        entity.setAllocationStartDate(request.getAllocationStartDate());
        entity.setAllocationEndDate(request.getAllocationEndDate());
        entity.setAcceptDeadlineHours(request.getAcceptDeadlineHours());
        entity.setLockAfterPublish(request.getLockAfterPublish());
        entity.setEnableQuota(request.getEnableQuota());
        entity.setEnableReservedSeats(request.getEnableReservedSeats());
        entity.setStatus(request.getStatus());
        entity.setRemarks(request.getRemarks());
        return entity;
    }

    public static SeatAllocationConfigResponse toResponse(SeatAllocationConfig entity) {
        SeatAllocationConfigResponse response = new SeatAllocationConfigResponse();
        response.setId(entity.getId());
        response.setSessionId(entity.getSessionId());
        response.setAcademicYear(entity.getAcademicYear());
        response.setAllocationRound(entity.getAllocationRound());
        response.setAutoAllocation(entity.getAutoAllocation());
        response.setManualAllocation(entity.getManualAllocation());
        response.setAllocationStartDate(entity.getAllocationStartDate());
        response.setAllocationEndDate(entity.getAllocationEndDate());
        response.setAcceptDeadlineHours(entity.getAcceptDeadlineHours());
        response.setLockAfterPublish(entity.getLockAfterPublish());
        response.setEnableQuota(entity.getEnableQuota());
        response.setEnableReservedSeats(entity.getEnableReservedSeats());
        response.setStatus(entity.getStatus());
        response.setRemarks(entity.getRemarks());
        response.setCreatedAt(entity.getCreatedAt());
        response.setUpdatedAt(entity.getUpdatedAt());
        return response;
    }
}
