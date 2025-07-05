package com.ncapas.actividadl3capasv.Domain.Entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Admins")
public class Admin {
    
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(unique = true, nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column
    private String roles = "ADMIN";
    
    @Column
    private String permissions; // Permisos específicos del admin (JSON)
    
    @Column
    private LocalDateTime lastLogin;
    
    @Column
    private boolean isSuperAdmin = false; // Indica si es super administrador
    
    @Column
    private String createdBy; // Quién creó este admin
    
    @Column
    private LocalDateTime createdAt;
    
    @Column
    private LocalDateTime updatedAt;
    
    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
        setRoles("ADMIN");
    }
    
    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }
} 