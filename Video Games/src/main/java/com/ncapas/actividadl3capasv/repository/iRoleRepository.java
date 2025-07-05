package com.ncapas.actividadl3capasv.repository;

import com.ncapas.actividadl3capasv.Domain.Entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface iRoleRepository extends JpaRepository<Role, UUID> {
    
    Role findByName(String name);
    
    boolean existsByName(String name);
    
    List<Role> findByIsActive(boolean isActive);
    
    List<Role> findByPriorityOrderByPriorityAsc(int priority);
} 