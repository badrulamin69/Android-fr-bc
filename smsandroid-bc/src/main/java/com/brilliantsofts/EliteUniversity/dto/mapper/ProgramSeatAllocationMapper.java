package com.brilliantsofts.EliteUniversity.dto.mapper;

import com.brilliantsofts.EliteUniversity.dto.request.ProgramSeatAllocationRequest;
import com.brilliantsofts.EliteUniversity.dto.response.ProgramSeatAllocationResponse;
import com.brilliantsofts.EliteUniversity.entity.ProgramSeatAllocation;

public class ProgramSeatAllocationMapper {
    public static ProgramSeatAllocation toEntity(ProgramSeatAllocationRequest request) {
        ProgramSeatAllocation entity = new ProgramSeatAllocation();
        entity.setConfigId(request.getConfigId());
        entity.setAllocationRound(request.getAllocationRound());
        entity.setChoiceNumber(request.getChoiceNumber());
        entity.setAllocatedFacultyId(request.getAllocatedFacultyId());
        entity.setShift(request.getShift());
        entity.setMeritRank(request.getMeritRank());
        entity.setTotalScore(request.getTotalScore());
        entity.setStatus(request.getStatus());
        entity.setRegistrationId(request.getRegistrationId());
        entity.setAllocatedProgramId(request.getAllocatedProgramId());
        entity.setAllocatedDepartmentId(request.getAllocatedDepartmentId());
        entity.setWaiting(request.isWaiting());
        entity.setWaitingRank(request.getWaitingRank());
        entity.setRemarks(request.getRemarks());
        return entity;
    }

    public static ProgramSeatAllocationResponse toResponse(ProgramSeatAllocation entity) {
        ProgramSeatAllocationResponse response = new ProgramSeatAllocationResponse();
        response.setId(entity.getId());
        response.setAllocationNumber(entity.getAllocationNumber());
        response.setConfigId(entity.getConfigId());
        response.setAllocationRound(entity.getAllocationRound());
        response.setChoiceNumber(entity.getChoiceNumber());
        response.setAllocatedFacultyId(entity.getAllocatedFacultyId());
        response.setShift(entity.getShift());
        response.setMeritRank(entity.getMeritRank());
        response.setTotalScore(entity.getTotalScore());
        response.setStatus(entity.getStatus());
        response.setAllocatedAt(entity.getAllocatedAt());
        response.setAcceptedAt(entity.getAcceptedAt());
        response.setDeclinedAt(entity.getDeclinedAt());
        response.setDeadline(entity.getDeadline());
        response.setConfirmedAt(entity.getConfirmedAt());
        response.setWaiting(entity.isWaiting());
        response.setWaitingRank(entity.getWaitingRank());
        response.setRemarks(entity.getRemarks());
        response.setRegistrationId(entity.getRegistrationId());
        response.setAllocatedProgramId(entity.getAllocatedProgramId());
        response.setAllocatedDepartmentId(entity.getAllocatedDepartmentId());
        response.setCreatedAt(entity.getCreatedAt());
        response.setUpdatedAt(entity.getUpdatedAt());
        return response;
    }
}
