package com.ncapas.actividadl3capasv.controller;

import com.ncapas.actividadl3capasv.Domain.DTOs.AdminDTO;
import com.ncapas.actividadl3capasv.Domain.DTOs.CreateAdminDTO;
import com.ncapas.actividadl3capasv.Domain.Entities.Admin;
import com.ncapas.actividadl3capasv.service.iAdminService;
import com.ncapas.actividadl3capasv.util.ApiConstants;
import com.ncapas.actividadl3capasv.util.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(ApiConstants.ADMIN_BASE_PATH)
@CrossOrigin(origins = "*")
public class AdminController {

    @Autowired
    private iAdminService adminService;

    // Endpoints CRUD básicos
    @PostMapping(ApiConstants.ADMIN_CREATE)
    public ResponseEntity<ApiResponse<Admin>> createAdmin(@RequestBody CreateAdminDTO createAdminDTO) {
        try {
            Admin createdAdmin = adminService.createAdmin(createAdminDTO);
            ApiResponse<Admin> response = ApiResponse.success(
                "Administrador creado exitosamente", 
                createdAdmin
            ).withPath(ApiConstants.ADMIN_CREATE);
            
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (RuntimeException e) {
            ApiResponse<Admin> errorResponse = ApiResponse.<Admin>error(e.getMessage())
                .withPath(ApiConstants.ADMIN_CREATE);
            return ResponseEntity.badRequest().body(errorResponse);
        }
    }

    @GetMapping(ApiConstants.ADMIN_GET_BY_ID)
    public ResponseEntity<ApiResponse<AdminDTO>> getAdminById(@PathVariable UUID id) {
        try {
            AdminDTO admin = adminService.getAdminById(id);
            ApiResponse<AdminDTO> response = ApiResponse.success(
                "Administrador encontrado", 
                admin
            ).withPath(ApiConstants.ADMIN_GET_BY_ID.replace("{id}", id.toString()));
            
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            ApiResponse<AdminDTO> errorResponse = ApiResponse.<AdminDTO>error(e.getMessage())
                .withPath(ApiConstants.ADMIN_GET_BY_ID.replace("{id}", id.toString()));
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping(ApiConstants.ADMIN_GET_ALL)
    public ResponseEntity<ApiResponse<List<AdminDTO>>> getAllAdmins() {
        List<AdminDTO> admins = adminService.getAllAdmins();
        ApiResponse<List<AdminDTO>> response = ApiResponse.success(
            "Administradores obtenidos exitosamente", 
            admins
        ).withPath(ApiConstants.ADMIN_GET_ALL);
        
        return ResponseEntity.ok(response);
    }

    @PutMapping(ApiConstants.ADMIN_UPDATE)
    public ResponseEntity<ApiResponse<Admin>> updateAdmin(@PathVariable UUID id, @RequestBody CreateAdminDTO updateAdminDTO) {
        try {
            Admin updatedAdmin = adminService.updateAdmin(id, updateAdminDTO);
            ApiResponse<Admin> response = ApiResponse.success(
                "Administrador actualizado exitosamente", 
                updatedAdmin
            ).withPath(ApiConstants.ADMIN_UPDATE.replace("{id}", id.toString()));
            
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            ApiResponse<Admin> errorResponse = ApiResponse.<Admin>error(e.getMessage())
                .withPath(ApiConstants.ADMIN_UPDATE.replace("{id}", id.toString()));
            return ResponseEntity.badRequest().body(errorResponse);
        }
    }

    @DeleteMapping(ApiConstants.ADMIN_DELETE)
    public ResponseEntity<ApiResponse<Void>> deleteAdmin(@PathVariable UUID id) {
        try {
            adminService.deleteAdmin(id);
            ApiResponse<Void> response = ApiResponse.<Void>success("Administrador eliminado exitosamente")
                .withPath(ApiConstants.ADMIN_DELETE.replace("{id}", id.toString()));
            
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            ApiResponse<Void> errorResponse = ApiResponse.<Void>error(e.getMessage())
                .withPath(ApiConstants.ADMIN_DELETE.replace("{id}", id.toString()));
            return ResponseEntity.notFound().build();
        }
    }

    // Endpoints específicos de administradores
    @GetMapping(ApiConstants.ADMIN_GET_BY_USERNAME)
    public ResponseEntity<ApiResponse<AdminDTO>> getAdminByUsername(@PathVariable String username) {
        AdminDTO admin = adminService.getAdminByUsername(username);
        if (admin != null) {
            ApiResponse<AdminDTO> response = ApiResponse.success(
                "Administrador encontrado", 
                admin
            ).withPath(ApiConstants.ADMIN_GET_BY_USERNAME.replace("{username}", username));
            
            return ResponseEntity.ok(response);
        } else {
            ApiResponse<AdminDTO> errorResponse = ApiResponse.<AdminDTO>error("Administrador no encontrado")
                .withPath(ApiConstants.ADMIN_GET_BY_USERNAME.replace("{username}", username));
            return ResponseEntity.notFound().build();
        }
    }



    @GetMapping("/super")
    public ResponseEntity<ApiResponse<List<AdminDTO>>> getSuperAdmins() {
        List<AdminDTO> superAdmins = adminService.getSuperAdmins();
        ApiResponse<List<AdminDTO>> response = ApiResponse.success(
            "Super administradores obtenidos", 
            superAdmins
        ).withPath("/api/admins/super");
        
        return ResponseEntity.ok(response);
    }

    @GetMapping("/active")
    public ResponseEntity<ApiResponse<List<AdminDTO>>> getActiveAdmins() {
        List<AdminDTO> activeAdmins = adminService.getActiveAdmins();
        ApiResponse<List<AdminDTO>> response = ApiResponse.success(
            "Administradores activos obtenidos", 
            activeAdmins
        ).withPath("/api/admins/active");
        
        return ResponseEntity.ok(response);
    }

    // Endpoints de gestión de permisos y roles
    @PostMapping("/{id}/promote")
    public ResponseEntity<ApiResponse<Void>> promoteToSuperAdmin(@PathVariable UUID id) {
        try {
            adminService.promoteToSuperAdmin(id);
            ApiResponse<Void> response = ApiResponse.<Void>success("Administrador promovido a super admin")
                .withPath("/api/admins/" + id + "/promote");
            
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            ApiResponse<Void> errorResponse = ApiResponse.<Void>error(e.getMessage())
                .withPath("/api/admins/" + id + "/promote");
            return ResponseEntity.badRequest().body(errorResponse);
        }
    }

    @PostMapping("/{id}/demote")
    public ResponseEntity<ApiResponse<Void>> demoteFromSuperAdmin(@PathVariable UUID id) {
        try {
            adminService.demoteFromSuperAdmin(id);
            ApiResponse<Void> response = ApiResponse.<Void>success("Administrador degradado de super admin")
                .withPath("/api/admins/" + id + "/demote");
            
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            ApiResponse<Void> errorResponse = ApiResponse.<Void>error(e.getMessage())
                .withPath("/api/admins/" + id + "/demote");
            return ResponseEntity.badRequest().body(errorResponse);
        }
    }



    @PutMapping("/{id}/permissions")
    public ResponseEntity<ApiResponse<Void>> updatePermissions(
            @PathVariable UUID id, 
            @RequestBody String permissions) {
        try {
            adminService.updatePermissions(id, permissions);
            ApiResponse<Void> response = ApiResponse.<Void>success("Permisos actualizados exitosamente")
                .withPath("/api/admins/" + id + "/permissions");
            
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            ApiResponse<Void> errorResponse = ApiResponse.<Void>error(e.getMessage())
                .withPath("/api/admins/" + id + "/permissions");
            return ResponseEntity.badRequest().body(errorResponse);
        }
    }

    // Endpoint de autenticación
    @PostMapping("/login")
    public ResponseEntity<ApiResponse<AdminDTO>> login(@RequestBody CreateAdminDTO loginDTO) {
        if (adminService.validateAdminCredentials(loginDTO.getUsername(), loginDTO.getPassword())) {
            AdminDTO admin = adminService.getAdminByUsername(loginDTO.getUsername());
            adminService.updateLastLogin(admin.getId());
            
            ApiResponse<AdminDTO> response = ApiResponse.success(
                "Login exitoso", 
                admin
            ).withPath("/api/admins/login");
            
            return ResponseEntity.ok(response);
        } else {
            ApiResponse<AdminDTO> errorResponse = ApiResponse.<AdminDTO>error("Credenciales inválidas")
                .withPath("/api/admins/login");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorResponse);
        }
    }
} 