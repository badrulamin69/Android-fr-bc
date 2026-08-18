package com.brilliantsofts.EliteUniversity.repository;

import com.brilliantsofts.EliteUniversity.entity.Employee;
import com.brilliantsofts.EliteUniversity.enums.EmployeeType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    Employee findByEmployeeId(String employeeId);
    List<Employee> findByDepartmentId(Long departmentId);
    List<Employee> findByEmployeeType(EmployeeType employeeType);
}
