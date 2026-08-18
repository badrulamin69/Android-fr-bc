package com.brilliantsofts.EliteUniversity.dto.mapper;

import com.brilliantsofts.EliteUniversity.dto.request.DepartmentAllocationRequest;
import com.brilliantsofts.EliteUniversity.dto.response.DepartmentAllocationResponse;
import com.brilliantsofts.EliteUniversity.entity.DepartmentAllocation;
import java.time.LocalDateTime;

public class DepartmentAllocationMapper {
    public static DepartmentAllocation toEntity(DepartmentAllocationRequest request) {
        DepartmentAllocation entity = new DepartmentAllocation();
        entity.setRegistrationId(request.getRegistrationId());
        entity.setAllocatedProgramId(request.getAllocatedProgramId());
        entity.setAllocatedDepartmentId(request.getAllocatedDepartmentId());
        entity.setAllocatedBatchId(request.getAllocatedBatchId());
        entity.setAllocatedSectionId(request.getAllocatedSectionId());
        entity.setSemesterId(request.getSemesterId());
        entity.setAllocatedById(request.getAllocatedById());
        entity.setMeritRank(request.getMeritRank());
        entity.setTotalScore(request.getTotalScore());
        entity.setStatus(request.getStatus());
        entity.setRemarks(request.getRemarks());
        return entity;
    }

    public static DepartmentAllocationResponse toResponse(DepartmentAllocation entity) {
        DepartmentAllocationResponse response = new DepartmentAllocationResponse();
        response.setId(entity.getId());
        response.setAllocationNumber(entity.getAllocationNumber());
        response.setMeritRank(entity.getMeritRank());
        response.setTotalScore(entity.getTotalScore());
        response.setStatus(entity.getStatus());
        response.setAllocatedAt(entity.getAllocatedAt());
        response.setConfirmedAt(entity.getConfirmedAt());
        response.setRemarks(entity.getRemarks());
        response.setRegistrationId(entity.getRegistrationId());
        response.setAllocatedProgramId(entity.getAllocatedProgramId());
        response.setAllocatedDepartmentId(entity.getAllocatedDepartmentId());
        response.setAllocatedBatchId(entity.getAllocatedBatchId());
        response.setAllocatedSectionId(entity.getAllocatedSectionId());
        response.setSemesterId(entity.getSemesterId());
        response.setAllocatedById(entity.getAllocatedById());
        response.setCreatedAt(entity.getCreatedAt());
        response.setUpdatedAt(entity.getUpdatedAt());
        return response;
    }
}