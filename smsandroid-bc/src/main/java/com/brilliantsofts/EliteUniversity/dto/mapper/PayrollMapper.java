package com.brilliantsofts.EliteUniversity.dto.mapper;

import com.brilliantsofts.EliteUniversity.dto.request.PayrollRequest;
import com.brilliantsofts.EliteUniversity.dto.response.PayrollResponse;
import com.brilliantsofts.EliteUniversity.entity.Payroll;

public class PayrollMapper {
    public static Payroll toEntity(PayrollRequest request) {
        Payroll entity = new Payroll();
        entity.setEmployeeId(request.getEmployeeId());
        entity.setPayPeriodStart(request.getPayPeriodStart());
        entity.setPayPeriodEnd(request.getPayPeriodEnd());
        entity.setBasicSalary(request.getBasicSalary());
        entity.setAllowances(request.getAllowances());
        entity.setDeductions(request.getDeductions());
        entity.setNetSalary(request.getNetSalary());
        entity.setPaymentDate(request.getPaymentDate());
        entity.setStatus(request.getStatus());
        return entity;
    }

    public static PayrollResponse toResponse(Payroll entity) {
        PayrollResponse response = new PayrollResponse();
        response.setId(entity.getId());
        response.setUniqueCode(entity.getUniqueCode());
        response.setEmployeeId(entity.getEmployeeId());
        response.setPayPeriodStart(entity.getPayPeriodStart());
        response.setPayPeriodEnd(entity.getPayPeriodEnd());
        response.setBasicSalary(entity.getBasicSalary());
        response.setAllowances(entity.getAllowances());
        response.setDeductions(entity.getDeductions());
        response.setNetSalary(entity.getNetSalary());
        response.setPaymentDate(entity.getPaymentDate());
        response.setStatus(entity.getStatus());
        response.setCreatedAt(entity.getCreatedAt());
        response.setUpdatedAt(entity.getUpdatedAt());
        return response;
    }
}