package com.Chitkara.impl;

import com.Chitkara.dto.EmployeeDTO;
import com.Chitkara.enums.Gender;
import com.Chitkara.repository.EmployeeRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class EmployeeRepositoryImpl implements EmployeeRepository {

    private static List<EmployeeDTO> employeeDTOList;

    static {
        employeeDTOList = new ArrayList<>();
        EmployeeDTO employeeDTO = new EmployeeDTO();
        employeeDTO.setId(1);
        employeeDTO.setFirstName("Suchet");
        employeeDTO.setLastName("Rana");
        employeeDTO.setGender(Gender.M);
        employeeDTO.setCity("Bangana");
        employeeDTO.setState("Himachal Pradesh");
        employeeDTO.setMobileNumber("9876543210");
        employeeDTO.setEmailId("employee1@gmail.com");

        employeeDTOList.add(employeeDTO);
    }
    @Override
    public void saveOwner(EmployeeDTO employeeDTO) {
        employeeDTOList.add(employeeDTO);
    }

    @Override
    public Optional<EmployeeDTO> findOwner(int employeeId) {
         return employeeDTOList.stream().filter(employee -> employee.getId() == employeeId).findFirst();
    }

    @Override
    public void updateEmployeeDetails(int employeeId, String email) {
        employeeDTOList.stream().filter(employee -> employee.getId() == employeeId).findFirst().ifPresent(employee -> employee.setEmailId(email));
    }

    @Override
    public void deleteEmployee(int employeeId) {
        employeeDTOList.removeIf(employee -> employee.getId() == employeeId);
    }

    @Override
    public List<EmployeeDTO> findAllEmployee() {
        return employeeDTOList;
    }

    @Override
    public Optional<EmployeeDTO> findEmployee(int id) {
        return Optional.empty();
    }
}