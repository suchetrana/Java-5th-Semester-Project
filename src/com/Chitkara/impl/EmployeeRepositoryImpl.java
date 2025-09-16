package com.Chitkara.impl;

import com.Chitkara.dto.EmployeeDTO;
import com.Chitkara.exceptions.InternalServiceException;
import com.Chitkara.repository.EmployeeRepository;
import com.Chitkara.util.MapperUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class EmployeeRepositoryImpl implements EmployeeRepository {
    private static final String DATABASE_DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/Employee_Database";
    private static final String DATABASE_USERNAME = "root";
    private static final String DATABASE_PASSWORD = "Suchet";

    @Override
    public void saveEmployee(EmployeeDTO employeeDTO) {
        String sql = "INSERT INTO EMPLOYEE_TABLE (first_name, last_name, gender, city, state, mobile_number, email_id, employee_date_of_birth, salary) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection connection = DriverManager.getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD);
             PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            Class.forName(DATABASE_DRIVER);
            ps.setString(1, employeeDTO.getFirstName());
            ps.setString(2, employeeDTO.getLastName());
            ps.setString(3, employeeDTO.getGender().toString());
            ps.setString(4, employeeDTO.getCity());
            ps.setString(5, employeeDTO.getState());
            ps.setString(6, employeeDTO.getMobileNumber());
            ps.setString(7, employeeDTO.getEmailId());
            ps.setDate(8, Date.valueOf(employeeDTO.getEmployeeBirthDate()));
            ps.setDouble(9, employeeDTO.getSalary());
            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                employeeDTO.setEmployeeId(rs.getInt(1));
            }
        } catch (ClassNotFoundException | SQLException exception) {
            exception.printStackTrace();
            throw new InternalServiceException(exception.getMessage());
        }
    }

    @Override
    public Optional<EmployeeDTO> findEmployee(int employeeId) {
        String sql = "SELECT * FROM EMPLOYEE_TABLE WHERE employee_id = ?";
        try (Connection connection = DriverManager.getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD);
             PreparedStatement ps = connection.prepareStatement(sql)) {
            Class.forName(DATABASE_DRIVER);
            ps.setInt(1, employeeId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return Optional.of(MapperUtil.convertOwnerResultSetToDto(rs));
            }
        } catch (ClassNotFoundException | SQLException exception) {
            exception.printStackTrace();
            throw new InternalServiceException(exception.getMessage());
        }
        return Optional.empty();
    }

    @Override
    public void updateEmployeeDetails(int employeeId, String email) {
        String sql = "UPDATE EMPLOYEE_TABLE SET email_id = ? WHERE employee_id = ?";
        try (Connection connection = DriverManager.getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD);
             PreparedStatement ps = connection.prepareStatement(sql)) {
            Class.forName(DATABASE_DRIVER);
            ps.setString(1, email);
            ps.setInt(2, employeeId);
            ps.executeUpdate();
        } catch (ClassNotFoundException | SQLException exception) {
            exception.printStackTrace();
            throw new InternalServiceException(exception.getMessage());
        }
    }

    @Override
    public void deleteEmployee(int employeeId) {
        String sql = "DELETE FROM EMPLOYEE_TABLE WHERE employee_id = ?";
        try (Connection connection = DriverManager.getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD);
             PreparedStatement ps = connection.prepareStatement(sql)) {
            Class.forName(DATABASE_DRIVER);
            ps.setInt(1, employeeId);
            ps.executeUpdate();
        } catch (ClassNotFoundException | SQLException exception) {
            exception.printStackTrace();
            throw new InternalServiceException(exception.getMessage());
        }
    }

    @Override
    public List<EmployeeDTO> findAllEmployee() {
        String sql = "SELECT * FROM EMPLOYEE_TABLE";
        List<EmployeeDTO> employees = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD);
             PreparedStatement ps = connection.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            Class.forName(DATABASE_DRIVER);
            while (rs.next()) {
                employees.add(MapperUtil.convertOwnerResultSetToDto(rs));
            }
        } catch (ClassNotFoundException | SQLException exception) {
            exception.printStackTrace();
            throw new InternalServiceException(exception.getMessage());
        }
        return employees;
    }
}