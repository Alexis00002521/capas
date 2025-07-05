package com.ncapas.actividadl3capasv.Domain.Entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "AdminPermissions")
public class AdminPermission {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private String permissionName; // Ej: USER_MANAGEMENT, VIDEOGAME_APPROVAL, etc.

    @Column
    private String description;

    @Column
    private String resource; // Recurso al que aplica el permiso

    @Column
    private String action; // Acción permitida: CREATE, READ, UPDATE, DELETE

    @Column
    private boolean isActive = true;

    @Column
    private int priority; // Prioridad del permiso
} 