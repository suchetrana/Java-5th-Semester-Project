package com.Chitkara.impl;

import com.Chitkara.dto.EmployeeDTO;
import com.Chitkara.exceptions.DuplicateEmployeeException;
import com.Chitkara.exceptions.EmployeeNotFoundException;
import com.Chitkara.repository.EmployeeRepository;
import com.Chitkara.services.EmployeeService;

import java.util.List;
import java.util.Optional;

public class EmployeeServiceImpl implements EmployeeService {
    private EmployeeRepository employeeRepository = null ;
    private static final String EMPLOYEE_ALREADY_EXISTS = "Employee already exists with employeeId ";
    private static final String EMPLOYEE_NOT_FOUND = "Can't find Employee with employeeId ";

    public EmployeeServiceImpl() {
        this.employeeRepository = new EmployeeRepositoryImpl();
    }

    @Override
    public void saveEmployee(EmployeeDTO employeeDTO) throws DuplicateEmployeeException {
        Optional<EmployeeDTO> existingEmployeeDTO = employeeRepository.findEmployee(employeeDTO.getEmployeeId());
        if (existingEmployeeDTO.isPresent()) {
            throw new DuplicateEmployeeException(EMPLOYEE_ALREADY_EXISTS + employeeDTO.getEmployeeId());
        } else {
            employeeRepository.saveEmployee(employeeDTO);
        }
    }

    @Override
    public EmployeeDTO findEmployee(int employeeId) throws EmployeeNotFoundException {
        Optional<EmployeeDTO> employeeDTO = employeeRepository.findEmployee(employeeId);
        if (employeeDTO.isEmpty()) {
            throw new EmployeeNotFoundException(EMPLOYEE_NOT_FOUND + employeeId);
        } else {
            return employeeDTO.get();
        }
    }

    @Override
    public void updateEmployeeDetails(int employeeId, String email) throws EmployeeNotFoundException {
        Optional<EmployeeDTO> ownerDTO = employeeRepository.findEmployee(employeeId);
        if (ownerDTO.isEmpty()) {
            throw new EmployeeNotFoundException(EMPLOYEE_NOT_FOUND + employeeId);
        } else {
            employeeRepository.updateEmployeeDetails(employeeId, email);
        }
    }

    @Override
    public void deleteEmployee(int employeeId) throws EmployeeNotFoundException {
        Optional<EmployeeDTO> employeeDTO = employeeRepository.findEmployee(employeeId);
        if (employeeDTO.isEmpty()) {
            throw new EmployeeNotFoundException(EMPLOYEE_NOT_FOUND + employeeId);
        } else {
            employeeRepository.deleteEmployee(employeeId);
        }
    }

    @Override
    public List<EmployeeDTO> findAllEmployee() {
        return employeeRepository.findAllEmployee();
    }
}