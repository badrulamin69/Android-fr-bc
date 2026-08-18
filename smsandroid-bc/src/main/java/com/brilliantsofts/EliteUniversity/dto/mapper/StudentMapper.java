package com.brilliantsofts.EliteUniversity.dto.mapper;

import com.brilliantsofts.EliteUniversity.dto.request.StudentRequest;
import com.brilliantsofts.EliteUniversity.dto.response.StudentResponse;
import com.brilliantsofts.EliteUniversity.entity.Student;

public class StudentMapper {
    public static Student toEntity(StudentRequest request) {
        if (request == null) return null;
        Student entity = new Student();
        entity.setStudentId(request.getStudentId());
        entity.setFullName(request.getFullName());
        entity.setPhone(request.getPhone());
        entity.setAdmissionDate(request.getAdmissionDate());
        return entity;
    }

    public static StudentResponse toResponse(Student entity) {
        if (entity == null) return null;
        StudentResponse response = new StudentResponse();
        response.setId(entity.getId());
        response.setStudentId(entity.getStudentId());
        response.setFullName(entity.getFullName());
        response.setPhone(entity.getPhone());
        response.setAdmissionDate(entity.getAdmissionDate());

        response.setUniqueCode(entity.getStudentId());
        response.setStudentCode(entity.getStudentId());
        response.setStatus("ACTIVE");

        if (entity.getFullName() != null) {
            String[] parts = entity.getFullName().trim().split("\\s+", 2);
            response.setFirstName(parts[0]);
            response.setLastName(parts.length > 1 ? parts[1] : "");
        }

        if (entity.getUser() != null) {
            response.setUserId(entity.getUser().getId());
            response.setEmail(entity.getUser().getEmail());
        }
        if (entity.getApplicant() != null) {
            response.setApplicantId(entity.getApplicant().getId());
            if (response.getEmail() == null && entity.getApplicant().getUser() != null) {
                response.setEmail(entity.getApplicant().getUser().getEmail());
            }
        }
        if (entity.getProgram() != null) {
            response.setProgramId(entity.getProgram().getId());
            response.setProgramName(entity.getProgram().getName());
            if (entity.getProgram().getDepartment() != null) {
                response.setDepartmentName(entity.getProgram().getDepartment().getName());
            }
        }
        if (entity.getAcademicSession() != null) {
            response.setAcademicSessionId(entity.getAcademicSession().getId());
            response.setSessionName(entity.getAcademicSession().getSessionName());
        }
        return response;
    }
}
