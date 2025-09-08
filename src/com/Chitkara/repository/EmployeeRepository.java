package com.Chitkara.repository;

import com.Chitkara.dto.EmployeeDTO;

import java.util.List;
import java.util.Optional;

public interface EmployeeRepository {
    void saveOwner(EmployeeDTO employeeDTO);

    Optional<EmployeeDTO> findOwner(int ownerId);

    void updateEmployeeDetails(int employeeId, String email);

    void deleteEmployee(int employeeId);

    List<EmployeeDTO> findAllEmployee();

    Optional<EmployeeDTO> findEmployee(int id);


}
