package com.brilliantsofts.EliteUniversity.dto.mapper;

import com.brilliantsofts.EliteUniversity.dto.request.MedicalInfoRequest;
import com.brilliantsofts.EliteUniversity.dto.response.MedicalInfoResponse;
import com.brilliantsofts.EliteUniversity.entity.MedicalInfo;

import java.util.UUID;

public class MedicalInfoMapper {
    public static MedicalInfo toEntity(MedicalInfoRequest request) {
        MedicalInfo entity = new MedicalInfo();
        entity.setUniqueCode(UUID.randomUUID().toString().substring(0, 8).toUpperCase());
        entity.setBloodGroup(request.getBloodGroup());
        entity.setHeight(request.getHeight());
        entity.setWeight(request.getWeight());
        entity.setAllergies(request.getAllergies());
        entity.setMedications(request.getMedications());
        entity.setConditions(request.getConditions());
        entity.setEmergencyContact(request.getEmergencyContact());
        entity.setEmergencyPhone(request.getEmergencyPhone());
        entity.setInsuranceProvider(request.getInsuranceProvider());
        entity.setInsuranceNumber(request.getInsuranceNumber());
        entity.setDoctorName(request.getDoctorName());
        entity.setDoctorPhone(request.getDoctorPhone());
        entity.setNotes(request.getNotes());
        return entity;
    }

    public static MedicalInfoResponse toResponse(MedicalInfo entity) {
        MedicalInfoResponse response = new MedicalInfoResponse();
        response.setId(entity.getId());
        response.setUniqueCode(entity.getUniqueCode());
        if (entity.getStudent() != null) {
            response.setStudentId(entity.getStudent().getId());
        }
        response.setBloodGroup(entity.getBloodGroup());
        response.setHeight(entity.getHeight());
        response.setWeight(entity.getWeight());
        response.setAllergies(entity.getAllergies());
        response.setMedications(entity.getMedications());
        response.setConditions(entity.getConditions());
        response.setEmergencyContact(entity.getEmergencyContact());
        response.setEmergencyPhone(entity.getEmergencyPhone());
        response.setInsuranceProvider(entity.getInsuranceProvider());
        response.setInsuranceNumber(entity.getInsuranceNumber());
        response.setDoctorName(entity.getDoctorName());
        response.setDoctorPhone(entity.getDoctorPhone());
        response.setNotes(entity.getNotes());
        return response;
    }
}
