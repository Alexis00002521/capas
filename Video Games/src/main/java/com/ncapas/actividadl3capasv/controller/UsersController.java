package com.ncapas.actividadl3capasv.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ncapas.actividadl3capasv.Domain.Entities.Users;
import com.ncapas.actividadl3capasv.service.iUsersService;
import com.ncapas.actividadl3capasv.util.AuthorizationUtil;
import com.ncapas.actividadl3capasv.util.ApiResponse;
import com.ncapas.actividadl3capasv.util.ApiConstants;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(ApiConstants.USERS_BASE_PATH)
@CrossOrigin(origins = "*")
public class UsersController {

    @Autowired
    private iUsersService usersService;

    @Autowired
    private AuthorizationUtil authorizationUtil;

    @PostMapping(ApiConstants.USERS_CREATE)
    public ResponseEntity<?> createUser(@RequestBody Users user, @RequestParam(required = false) String currentUser) {
        try {
            // Verificar autorización - solo super admin puede crear usuarios
            if (!authorizationUtil.canCreateUsers(getCurrentUser(currentUser))) {
                return ResponseEntity.status(HttpStatus.FORBIDDEN)
                    .body(new ApiResponse(false, "Acceso denegado. Solo super administradores pueden crear usuarios."));
            }
            
            Users createdUser = usersService.createUser(user);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest()
                .body(new ApiResponse(false, "Error al crear usuario: " + e.getMessage()));
        }
    }

    @GetMapping(ApiConstants.USERS_GET_BY_ID)
    public ResponseEntity<?> getUserById(@PathVariable UUID id, @RequestParam(required = false) String currentUser) {
        try {
            // Verificar autorización - solo super admin puede ver usuarios específicos
            if (!authorizationUtil.canReadUsers(getCurrentUser(currentUser))) {
                return ResponseEntity.status(HttpStatus.FORBIDDEN)
                    .body(new ApiResponse(false, "Acceso denegado. Solo super administradores pueden ver usuarios."));
            }
            
            Users user = usersService.getUserById(id);
            return ResponseEntity.ok(user);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new ApiResponse(false, "Usuario no encontrado"));
        }
    }

    @GetMapping(ApiConstants.USERS_GET_ALL)
    public ResponseEntity<?> getAllUsers(@RequestParam(required = false) String currentUser) {
        // Verificar autorización - solo super admin puede ver todos los usuarios
        if (!authorizationUtil.canReadUsers(getCurrentUser(currentUser))) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN)
                .body(new ApiResponse(false, "Acceso denegado. Solo super administradores pueden ver usuarios."));
        }
        
        List<Users> users = usersService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    @PutMapping(ApiConstants.USERS_UPDATE)
    public ResponseEntity<?> updateUser(@PathVariable UUID id, @RequestBody Users user, @RequestParam(required = false) String currentUser) {
        try {
            // Verificar autorización - solo super admin puede actualizar usuarios
            if (!authorizationUtil.canUpdateUsers(getCurrentUser(currentUser))) {
                return ResponseEntity.status(HttpStatus.FORBIDDEN)
                    .body(new ApiResponse(false, "Acceso denegado. Solo super administradores pueden actualizar usuarios."));
            }
            
            Users updatedUser = usersService.updateUser(id, user);
            return ResponseEntity.ok(updatedUser);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest()
                .body(new ApiResponse(false, "Error al actualizar usuario: " + e.getMessage()));
        }
    }

    @DeleteMapping(ApiConstants.USERS_DELETE)
    public ResponseEntity<?> deleteUser(@PathVariable UUID id, @RequestParam(required = false) String currentUser) {
        try {
            // Verificar autorización - solo super admin puede eliminar usuarios
            if (!authorizationUtil.canDeleteUsers(getCurrentUser(currentUser))) {
                return ResponseEntity.status(HttpStatus.FORBIDDEN)
                    .body(new ApiResponse(false, "Acceso denegado. Solo super administradores pueden eliminar usuarios."));
            }
            
            usersService.deleteUser(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new ApiResponse(false, "Usuario no encontrado"));
        }
    }

    @GetMapping(ApiConstants.USERS_GET_BY_USERNAME)
    public ResponseEntity<?> getUserByUsername(@PathVariable String username, @RequestParam(required = false) String currentUser) {
        // Verificar autorización - solo super admin puede buscar usuarios por username
        if (!authorizationUtil.canReadUsers(getCurrentUser(currentUser))) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN)
                .body(new ApiResponse(false, "Acceso denegado. Solo super administradores pueden buscar usuarios."));
        }
        
        Users user = usersService.getUserByUsername(username);
        if (user != null) {
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new ApiResponse(false, "Usuario no encontrado"));
        }
    }

    /**
     * Método auxiliar para obtener el usuario actual (simulado)
     * En una implementación real, esto vendría del contexto de seguridad
     */
    private Users getCurrentUser(String username) {
        if (username == null || username.isEmpty()) {
            return null;
        }
        
        try {
            return usersService.getUserByUsername(username);
        } catch (Exception e) {
            return null;
        }
    }
} 