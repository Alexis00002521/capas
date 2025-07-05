package com.ncapas.actividadl3capasv.service.impl;

import com.ncapas.actividadl3capasv.Domain.Entities.Admin;
import com.ncapas.actividadl3capasv.Domain.DTOs.AdminDTO;
import com.ncapas.actividadl3capasv.Domain.DTOs.CreateAdminDTO;
import com.ncapas.actividadl3capasv.repository.iAdminRepository;
import com.ncapas.actividadl3capasv.service.iAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class AdminServiceImpl implements iAdminService {

    @Autowired
    private iAdminRepository adminRepository;

    @Override
    public Admin createAdmin(CreateAdminDTO createAdminDTO) {
        // Validar que el username no exista
        if (adminRepository.existsByUsername(createAdminDTO.getUsername())) {
            throw new RuntimeException("El nombre de usuario ya existe");
        }
        
        Admin admin = new Admin();
        admin.setUsername(createAdminDTO.getUsername());
        admin.setPassword(createAdminDTO.getPassword());
        admin.setPermissions(createAdminDTO.getPermissions());
        admin.setSuperAdmin(createAdminDTO.isSuperAdmin());
        admin.setCreatedBy(createAdminDTO.getCreatedBy());
        
        return adminRepository.save(admin);
    }

    @Override
    public AdminDTO getAdminById(UUID id) {
        Admin admin = adminRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Administrador no encontrado con ID: " + id));
        return convertToDTO(admin);
    }

    @Override
    public List<AdminDTO> getAllAdmins() {
        return adminRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Admin updateAdmin(UUID id, CreateAdminDTO updateAdminDTO) {
        Admin existingAdmin = adminRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Administrador no encontrado con ID: " + id));
        
        // Actualizar campos si no son null
        if (updateAdminDTO.getUsername() != null && !updateAdminDTO.getUsername().equals(existingAdmin.getUsername())) {
            if (adminRepository.existsByUsername(updateAdminDTO.getUsername())) {
                throw new RuntimeException("El nombre de usuario ya existe");
            }
            existingAdmin.setUsername(updateAdminDTO.getUsername());
        }
        
        if (updateAdminDTO.getPassword() != null) {
            existingAdmin.setPassword(updateAdminDTO.getPassword());
        }
        
        if (updateAdminDTO.getPermissions() != null) {
            existingAdmin.setPermissions(updateAdminDTO.getPermissions());
        }
        
        existingAdmin.setSuperAdmin(updateAdminDTO.isSuperAdmin());
        
        return adminRepository.save(existingAdmin);
    }

    @Override
    public void deleteAdmin(UUID id) {
        if (!adminRepository.existsById(id)) {
            throw new RuntimeException("Administrador no encontrado con ID: " + id);
        }
        adminRepository.deleteById(id);
    }

    @Override
    public AdminDTO getAdminByUsername(String username) {
        Admin admin = adminRepository.findByUsername(username);
        return admin != null ? convertToDTO(admin) : null;
    }



    @Override
    public List<AdminDTO> getSuperAdmins() {
        return adminRepository.findByIsSuperAdmin(true).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<AdminDTO> getActiveAdmins() {
        return adminRepository.findAllActiveAdmins().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public boolean validateAdminCredentials(String username, String password) {
        Admin admin = adminRepository.findByUsername(username);
        return admin != null && admin.getPassword().equals(password);
    }

    @Override
    public boolean hasPermission(UUID adminId, String permission) {
        Admin admin = adminRepository.findById(adminId)
                .orElseThrow(() -> new RuntimeException("Administrador no encontrado"));
        
        // Lógica simple de verificación de permisos
        // En una implementación real, esto sería más complejo
        return admin.getPermissions() != null && admin.getPermissions().contains(permission);
    }

    @Override
    public void updateLastLogin(UUID adminId) {
        Admin admin = adminRepository.findById(adminId)
                .orElseThrow(() -> new RuntimeException("Administrador no encontrado"));
        admin.setLastLogin(LocalDateTime.now());
        adminRepository.save(admin);
    }

    @Override
    public boolean promoteToSuperAdmin(UUID adminId) {
        Admin admin = adminRepository.findById(adminId)
                .orElseThrow(() -> new RuntimeException("Administrador no encontrado"));
        admin.setSuperAdmin(true);
        adminRepository.save(admin);
        return true;
    }

    @Override
    public boolean demoteFromSuperAdmin(UUID adminId) {
        Admin admin = adminRepository.findById(adminId)
                .orElseThrow(() -> new RuntimeException("Administrador no encontrado"));
        admin.setSuperAdmin(false);
        adminRepository.save(admin);
        return true;
    }



    @Override
    public boolean updatePermissions(UUID adminId, String permissions) {
        Admin admin = adminRepository.findById(adminId)
                .orElseThrow(() -> new RuntimeException("Administrador no encontrado"));
        admin.setPermissions(permissions);
        adminRepository.save(admin);
        return true;
    }

    private AdminDTO convertToDTO(Admin admin) {
        return new AdminDTO(
                admin.getId(),
                admin.getUsername(),
                admin.getPermissions(),
                admin.getLastLogin(),
                admin.isSuperAdmin(),
                admin.getCreatedBy(),
                admin.getCreatedAt(),
                admin.getUpdatedAt()
        );
    }
} 