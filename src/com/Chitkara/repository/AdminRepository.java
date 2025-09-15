package com.Chitkara.repository;

import com.Chitkara.dto.AdminDTO;
import java.util.List;
import java.util.Optional;

public interface AdminRepository {
    Optional<AdminDTO> login(String username, String password);
    void createAdmin(AdminDTO admin);
    Optional<AdminDTO> findAdminById(int adminId);
    Optional<AdminDTO> findAdminByUsername(String username);
    void updateAdmin(AdminDTO admin);
    void deleteAdmin(int adminId);
    List<AdminDTO> findAllAdmins();
}