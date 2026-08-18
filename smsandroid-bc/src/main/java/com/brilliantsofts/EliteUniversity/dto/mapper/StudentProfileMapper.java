package com.brilliantsofts.EliteUniversity.dto.mapper;

import com.brilliantsofts.EliteUniversity.dto.request.StudentProfileRequest;
import com.brilliantsofts.EliteUniversity.dto.response.StudentProfileResponse;
import com.brilliantsofts.EliteUniversity.entity.StudentProfile;

public class StudentProfileMapper {
    public static StudentProfile toEntity(StudentProfileRequest request) {
        StudentProfile entity = new StudentProfile();
        entity.setStudentId(request.getStudentId());
        entity.setAddress(request.getAddress());
        entity.setCity(request.getCity());
        entity.setState(request.getState());
        entity.setZipCode(request.getZipCode());
        entity.setNationality(request.getNationality());
        entity.setBloodGroup(request.getBloodGroup());
        entity.setEmergencyContact(request.getEmergencyContact());
        entity.setEmergencyContactName(request.getEmergencyContactName());
        entity.setMedicalInfo(request.getMedicalInfo());
        return entity;
    }

    public static StudentProfileResponse toResponse(StudentProfile entity) {
        if (entity == null) return null;
        StudentProfileResponse response = new StudentProfileResponse();
        response.setId(entity.getId());
        response.setUniqueCode(entity.getUniqueCode());
        response.setStudentId(entity.getStudentId());
        response.setAddress(entity.getAddress());
        response.setCity(entity.getCity());
        response.setState(entity.getState());
        response.setZipCode(entity.getZipCode());
        response.setNationality(entity.getNationality());
        response.setBloodGroup(entity.getBloodGroup());
        response.setEmergencyContact(entity.getEmergencyContact());
        response.setEmergencyContactName(entity.getEmergencyContactName());
        response.setMedicalInfo(entity.getMedicalInfo());
        response.setCreatedAt(entity.getCreatedAt());
        response.setUpdatedAt(entity.getUpdatedAt());
        return response;
    }
}
