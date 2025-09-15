package com.Chitkara.util;

import com.Chitkara.dto.EmployeeDTO;
import com.Chitkara.enums.Gender;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MapperUtil {
    private MapperUtil() {}

    public static EmployeeDTO convertOwnerResultSetToDto(ResultSet resultSet) throws SQLException {
        EmployeeDTO employeeDTO = new EmployeeDTO();
        employeeDTO.setId(resultSet.getInt("id"));
        employeeDTO.setFirstName(resultSet.getString("first_name"));
        employeeDTO.setLastName(resultSet.getString("last_name"));
        employeeDTO.setGender(Gender.valueOf(resultSet.getString("gender")));
        employeeDTO.setCity(resultSet.getString("city"));
        employeeDTO.setState(resultSet.getString("state"));
        employeeDTO.setMobileNumber(resultSet.getString("mobile_number"));
        employeeDTO.setEmailId(resultSet.getString("email_id"));
        employeeDTO.setEmployeeId(resultSet.getInt("employee_id"));
        employeeDTO.setEmployeeBirthDate(resultSet.getDate("employee_date_of_birth").toLocalDate());
        employeeDTO.setSalary(resultSet.getDouble("salary"));
        return employeeDTO;
    }
}