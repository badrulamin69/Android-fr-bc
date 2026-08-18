package com.brilliantsofts.EliteUniversity.dto.mapper;

import com.brilliantsofts.EliteUniversity.dto.request.ProgramRequest;
import com.brilliantsofts.EliteUniversity.dto.response.ProgramResponse;
import com.brilliantsofts.EliteUniversity.entity.Program;

public class ProgramMapper {
    public static Program toEntity(ProgramRequest request) {
        Program entity = new Program();
        entity.setName(request.getName());
        entity.setCode(request.getCode());
        entity.setDurationYears(request.getDurationYears());
        entity.setTotalCredits(request.getTotalCredits());
        return entity;
    }

    public static ProgramResponse toResponse(Program entity) {
        ProgramResponse response = new ProgramResponse();
        response.setId(entity.getId());
        response.setName(entity.getName());
        response.setCode(entity.getCode());
        response.setDurationYears(entity.getDurationYears());
        response.setTotalCredits(entity.getTotalCredits());
        if (entity.getDepartment() != null) {
            response.setDepartmentId(entity.getDepartment().getId());
            response.setDepartmentName(entity.getDepartment().getName());
        }
        return response;
    }
}
