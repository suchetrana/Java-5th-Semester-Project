package com.Chitkara.impl;

import com.Chitkara.dto.AdminDTO;
import com.Chitkara.repository.AdminRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AdminRepositoryImpl implements AdminRepository {
    private static final String DATABASE_DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/Employee_Database";
    private static final String DATABASE_USERNAME = "root";
    private static final String DATABASE_PASSWORD = "Suchet";

    @Override
    public Optional<AdminDTO> login(String username, String password) {
        String sql = "SELECT * FROM ADMIN_TABLE WHERE username = ? AND password = ?";
        try (Connection connection = DriverManager.getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD);
             PreparedStatement ps = connection.prepareStatement(sql)) {
            Class.forName(DATABASE_DRIVER);
            ps.setString(1, username);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                AdminDTO admin = new AdminDTO();
                admin.setAdminId(rs.getInt("admin_id"));
                admin.setUsername(rs.getString("username"));
                admin.setPassword(rs.getString("password"));
                return Optional.of(admin);
            }
        } catch (ClassNotFoundException | SQLException exception) {
            exception.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public void createAdmin(AdminDTO admin) {
        String sql = "INSERT INTO ADMIN_TABLE (username, password) VALUES (?, ?)";
        try (Connection connection = DriverManager.getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD);
             PreparedStatement ps = connection.prepareStatement(sql)) {
            Class.forName(DATABASE_DRIVER);
            ps.setString(1, admin.getUsername());
            ps.setString(2, admin.getPassword());
            ps.executeUpdate();
        } catch (ClassNotFoundException | SQLException exception) {
            exception.printStackTrace();
        }
    }

    @Override
    public Optional<AdminDTO> findAdminById(int adminId) {
        String sql = "SELECT * FROM ADMIN_TABLE WHERE admin_id = ?";
        try (Connection connection = DriverManager.getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD);
             PreparedStatement ps = connection.prepareStatement(sql)) {
            Class.forName(DATABASE_DRIVER);
            ps.setInt(1, adminId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                AdminDTO admin = new AdminDTO();
                admin.setAdminId(rs.getInt("admin_id"));
                admin.setUsername(rs.getString("username"));
                admin.setPassword(rs.getString("password"));
                return Optional.of(admin);
            }
        } catch (ClassNotFoundException | SQLException exception) {
            exception.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public Optional<AdminDTO> findAdminByUsername(String username) {
        String sql = "SELECT * FROM ADMIN_TABLE WHERE username = ?";
        try (Connection connection = DriverManager.getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD);
             PreparedStatement ps = connection.prepareStatement(sql)) {
            Class.forName(DATABASE_DRIVER);
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                AdminDTO admin = new AdminDTO();
                admin.setAdminId(rs.getInt("admin_id"));
                admin.setUsername(rs.getString("username"));
                admin.setPassword(rs.getString("password"));
                return Optional.of(admin);
            }
        } catch (ClassNotFoundException | SQLException exception) {
            exception.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public void updateAdmin(AdminDTO admin) {
        String sql = "UPDATE ADMIN_TABLE SET username = ?, password = ? WHERE admin_id = ?";
        try (Connection connection = DriverManager.getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD);
             PreparedStatement ps = connection.prepareStatement(sql)) {
            Class.forName(DATABASE_DRIVER);
            ps.setString(1, admin.getUsername());
            ps.setString(2, admin.getPassword());
            ps.setInt(3, admin.getAdminId());
            ps.executeUpdate();
        } catch (ClassNotFoundException | SQLException exception) {
            exception.printStackTrace();
        }
    }

    @Override
    public void deleteAdmin(int adminId) {
        String sql = "DELETE FROM ADMIN_TABLE WHERE admin_id = ?";
        try (Connection connection = DriverManager.getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD);
             PreparedStatement ps = connection.prepareStatement(sql)) {
            Class.forName(DATABASE_DRIVER);
            ps.setInt(1, adminId);
            ps.executeUpdate();
        } catch (ClassNotFoundException | SQLException exception) {
            exception.printStackTrace();
        }
    }
    @Override
    public List<AdminDTO> findAllAdmins() {
        List<AdminDTO> admins = new ArrayList<>();
        String sql = "SELECT * FROM ADMIN_TABLE";
        try (Connection connection = DriverManager.getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD);
             PreparedStatement ps = connection.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            Class.forName(DATABASE_DRIVER);
            while (rs.next()) {
                AdminDTO admin = new AdminDTO();
                admin.setAdminId(rs.getInt("admin_id"));
                admin.setUsername(rs.getString("username"));
                admin.setPassword(rs.getString("password"));
                admins.add(admin);
            }
        } catch (ClassNotFoundException | SQLException exception) {
            exception.printStackTrace();
        }
        return admins;
    }
}