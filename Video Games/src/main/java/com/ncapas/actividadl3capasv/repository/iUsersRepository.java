package com.ncapas.actividadl3capasv.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ncapas.actividadl3capasv.Domain.Entities.Users;

import java.util.UUID;

@Repository
public interface iUsersRepository extends JpaRepository<Users, UUID> {
    Users findByUsername(String username);
    boolean existsByUsername(String username);
} 