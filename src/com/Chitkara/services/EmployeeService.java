package com.Chitkara.services;

import com.Chitkara.dto.EmployeeDTO;

import java.util.List;

public interface EmployeeService {
    void saveEmployee(EmployeeDTO employeeDTO);

    EmployeeDTO findEmployee(int employeeId);
    
    void updateEmployeeDetails(int employeeId, String email);
    
    void deleteEmployee(int employeeId);
    
    List<EmployeeDTO> findAllEmployee();
}
