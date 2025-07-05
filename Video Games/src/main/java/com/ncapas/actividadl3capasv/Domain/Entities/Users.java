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
@Table(name = "Users")
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID )
    private UUID id;

    @Column
    private String username;

    @Column
    private String password;

    @Column
    private String roles;
}
