package com.Chitkara.services;

import com.Chitkara.dto.AdminDTO;
import com.Chitkara.exceptions.AdminNotFoundException;
import java.util.List;
import java.util.Optional;

public interface AdminService {
    Optional<AdminDTO> login(String username, String password) throws AdminNotFoundException;
    void createAdmin(AdminDTO admin);
    AdminDTO findAdminById(int adminId) throws AdminNotFoundException;
    AdminDTO findAdminByUsername(String username) throws AdminNotFoundException;
    void updateAdmin(AdminDTO admin) throws AdminNotFoundException;
    void deleteAdmin(int adminId) throws AdminNotFoundException;
    List<AdminDTO> findAllAdmins();
}