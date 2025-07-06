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

import org.springframework.security.access.prepost.PreAuthorize;

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
    @PreAuthorize("hasRole('SUPER_ADMIN') or hasRole('ADMIN') or hasAuthority('USER_CREATE')")
    public ResponseEntity<?> createUser(@RequestBody Users user) {
        Users createdUser = usersService.createUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
    }

    @GetMapping(ApiConstants.USERS_GET_BY_ID)
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<?> getUserById(@PathVariable UUID id) {
        Users user = usersService.getUserById(id);
        return ResponseEntity.ok(user);
    }

    @GetMapping(ApiConstants.USERS_GET_ALL)
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<?> getAllUsers() {
        List<Users> users = usersService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    @PutMapping(ApiConstants.USERS_UPDATE)
    @PreAuthorize("hasRole('SUPER_ADMIN') or hasRole('ADMIN') or hasAuthority('USER_UPDATE')")
    public ResponseEntity<?> updateUser(@PathVariable UUID id, @RequestBody Users user) {
        Users updatedUser = usersService.updateUser(id, user);
        return ResponseEntity.ok(updatedUser);
    }

    @DeleteMapping(ApiConstants.USERS_DELETE)
    @PreAuthorize("hasRole('SUPER_ADMIN') or hasRole('ADMIN') or hasAuthority('USER_DELETE')")
    public ResponseEntity<?> deleteUser(@PathVariable UUID id) {
        usersService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping(ApiConstants.USERS_GET_BY_USERNAME)
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<?> getUserByUsername(@PathVariable String username) {
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