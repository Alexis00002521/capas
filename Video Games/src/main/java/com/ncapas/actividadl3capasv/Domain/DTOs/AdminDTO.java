package com.ncapas.actividadl3capasv.Domain.DTOs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdminDTO {
    private UUID id;
    private String username;
    private String permissions;
    private LocalDateTime lastLogin;
    private boolean isSuperAdmin;
    private String createdBy;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    
    // No incluimos password por seguridad
} 