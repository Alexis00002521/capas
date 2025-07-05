package com.ncapas.actividadl3capasv.service;

import com.ncapas.actividadl3capasv.Domain.Entities.Admin;
import com.ncapas.actividadl3capasv.Domain.DTOs.AdminDTO;
import com.ncapas.actividadl3capasv.Domain.DTOs.CreateAdminDTO;

import java.util.List;
import java.util.UUID;

public interface iAdminService {
    
    // Operaciones CRUD básicas
    Admin createAdmin(CreateAdminDTO createAdminDTO);
    AdminDTO getAdminById(UUID id);
    List<AdminDTO> getAllAdmins();
    Admin updateAdmin(UUID id, CreateAdminDTO updateAdminDTO);
    void deleteAdmin(UUID id);
    
    // Operaciones específicas de admin
    AdminDTO getAdminByUsername(String username);
    List<AdminDTO> getSuperAdmins();
    List<AdminDTO> getActiveAdmins();
    
    // Operaciones de autenticación y autorización
    boolean validateAdminCredentials(String username, String password);
    boolean hasPermission(UUID adminId, String permission);
    void updateLastLogin(UUID adminId);
    
    // Operaciones de gestión
    boolean promoteToSuperAdmin(UUID adminId);
    boolean demoteFromSuperAdmin(UUID adminId);
    boolean updatePermissions(UUID adminId, String permissions);
} 