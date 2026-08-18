package com.brilliantsofts.EliteUniversity.dto.mapper;

import com.brilliantsofts.EliteUniversity.dto.request.ProgramSeatConfigRequest;
import com.brilliantsofts.EliteUniversity.dto.response.ProgramSeatConfigResponse;
import com.brilliantsofts.EliteUniversity.entity.ProgramSeatConfig;

public class ProgramSeatConfigMapper {
    public static ProgramSeatConfig toEntity(ProgramSeatConfigRequest request) {
        ProgramSeatConfig entity = new ProgramSeatConfig();
        entity.setConfigId(request.getConfigId());
        entity.setFacultyId(request.getFacultyId());
        entity.setDepartmentId(request.getDepartmentId());
        entity.setProgramId(request.getProgramId());
        entity.setShift(request.getShift());
        entity.setTotalSeats(request.getTotalSeats());
        entity.setGeneralSeats(request.getGeneralSeats());
        entity.setQuotaSeats(request.getQuotaSeats());
        entity.setReservedSeats(request.getReservedSeats());
        entity.setAllocatedSeats(request.getAllocatedSeats());
        entity.setWaitingSeats(request.getWaitingSeats());
        entity.setIsActive(request.getIsActive());
        return entity;
    }

    public static ProgramSeatConfigResponse toResponse(ProgramSeatConfig entity) {
        ProgramSeatConfigResponse response = new ProgramSeatConfigResponse();
        response.setId(entity.getId());
        response.setConfigId(entity.getConfigId());
        response.setFacultyId(entity.getFacultyId());
        response.setDepartmentId(entity.getDepartmentId());
        response.setProgramId(entity.getProgramId());
        response.setShift(entity.getShift());
        response.setTotalSeats(entity.getTotalSeats());
        response.setGeneralSeats(entity.getGeneralSeats());
        response.setQuotaSeats(entity.getQuotaSeats());
        response.setReservedSeats(entity.getReservedSeats());
        response.setAllocatedSeats(entity.getAllocatedSeats());
        response.setWaitingSeats(entity.getWaitingSeats());
        response.setIsActive(entity.getIsActive());
        response.setCreatedAt(entity.getCreatedAt());
        response.setUpdatedAt(entity.getUpdatedAt());
        return response;
    }
}
