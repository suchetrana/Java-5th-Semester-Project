package com.Chitkara.impl;

import com.Chitkara.dto.AdminDTO;
import com.Chitkara.exceptions.AdminNotFoundException;
import com.Chitkara.repository.AdminRepository;
import com.Chitkara.services.AdminService;

import java.util.List;
import java.util.Optional;

public class AdminServiceImpl implements AdminService {
    private AdminRepository adminRepository;

    public AdminServiceImpl() {
        this.adminRepository = new AdminRepositoryImpl();
    }

    @Override
    public Optional<AdminDTO> login(String username, String password) throws AdminNotFoundException {
        Optional<AdminDTO> admin = adminRepository.login(username, password);
        if (admin.isEmpty()) {
            throw new AdminNotFoundException("Admin not found for username: " + username);
        }
        return admin;
    }

    @Override
    public void createAdmin(AdminDTO admin) {
        adminRepository.createAdmin(admin);
    }

    @Override
    public AdminDTO findAdminById(int adminId) throws AdminNotFoundException {
        Optional<AdminDTO> admin = adminRepository.findAdminById(adminId);
        if (admin.isEmpty()) {
            throw new AdminNotFoundException("Admin not found for id: " + adminId);
        }
        return admin.get();
    }

    @Override
    public AdminDTO findAdminByUsername(String username) throws AdminNotFoundException {
        Optional<AdminDTO> admin = adminRepository.findAdminByUsername(username);
        if (admin.isEmpty()) {
            throw new AdminNotFoundException("Admin not found for username: " + username);
        }
        return admin.get();
    }

    @Override
    public void updateAdmin(AdminDTO admin) throws AdminNotFoundException {
        if (adminRepository.findAdminById(admin.getAdminId()).isEmpty()) {
            throw new AdminNotFoundException("Admin not found for id: " + admin.getAdminId());
        }
        adminRepository.updateAdmin(admin);
    }

    @Override
    public void deleteAdmin(int adminId) throws AdminNotFoundException {
        if (adminRepository.findAdminById(adminId).isEmpty()) {
            throw new AdminNotFoundException("Admin not found for id: " + adminId);
        }
        adminRepository.deleteAdmin(adminId);
    }
    @Override
    public List<AdminDTO> findAllAdmins() {
        return adminRepository.findAllAdmins();
    }
}