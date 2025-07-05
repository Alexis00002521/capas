package com.ncapas.actividadl3capasv.repository;

import com.ncapas.actividadl3capasv.Domain.Entities.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface iAdminRepository extends JpaRepository<Admin, UUID> {
    
    Admin findByUsername(String username);
    
    boolean existsByUsername(String username);
    
    List<Admin> findByIsSuperAdmin(boolean isSuperAdmin);
    
    @Query("SELECT a FROM Admin a")
    List<Admin> findAllActiveAdmins();
} 