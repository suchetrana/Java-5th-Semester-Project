package com.Chitkara.impl;

import com.Chitkara.dto.EmployeeDTO;
import com.Chitkara.exceptions.DuplicateOwnerException;
import com.Chitkara.repository.EmployeeRepository;
import com.Chitkara.services.EmployeeService;

import java.util.Optional;

public class EmployeeServiceImpl implements EmployeeService {
    private EmployeeRepository employeeRepository;
    private static final String EMPLOYEE_ALREADY_EXISTS = "Employee already exists with employeeId ";
    private static final String EMPLOYEE_NOT_FOUND = "Can't find Employee with employeeId ";

    @Override
    public void saveEmployee(EmployeeDTO employeeDTO) throws DuplicateOwnerException{
        Optional<EmployeeDTO> existingOwnerDTO = employeeRepository.findEmployee(EmployeeDTO.getId());
        if (existingOwnerDTO.isPresent()) {
            throw new DuplicateOwnerException(EMPLOYEE_ALREADY_EXISTS + EmployeeDTO.getId());
        } else {
            employeeRepository.saveEmployee(employeeDTO);
        }
    }

    @Override
    public EmployeeDTO findEmployee(int employeeId) {
        return null;
    }

    @Override
    public void updateEmployeeDetails(int employeeId, String email) {

    }

    @Override
    public void deleteEmployee(int employeeId) {

    }
}
