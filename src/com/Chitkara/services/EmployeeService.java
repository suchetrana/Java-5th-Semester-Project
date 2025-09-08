package com.Chitkara.services;

import com.Chitkara.dto.EmployeeDTO;
import com.Chitkara.exceptions.DuplicateEmployeeException;
import com.Chitkara.exceptions.EmployeeNotFoundException;

import java.util.List;

public interface EmployeeService {
    void saveEmployee(EmployeeDTO employeeDTO) throws DuplicateEmployeeException;

    EmployeeDTO findEmployee(int employeeId) throws EmployeeNotFoundException;
    
    void updateEmployeeDetails(int employeeId, String email) throws EmployeeNotFoundException;
    
    void deleteEmployee(int employeeId) throws EmployeeNotFoundException;
    
    List<EmployeeDTO> findAllEmployee();
}
