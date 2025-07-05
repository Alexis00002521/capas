package com.ncapas.actividadl3capasv.Domain.DTOs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateAdminDTO {
    private String username;
    private String password;
    private String permissions;
    private boolean isSuperAdmin;
    private String createdBy;
} 