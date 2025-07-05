package com.ncapas.actividadl3capasv.util;

import com.ncapas.actividadl3capasv.Domain.Entities.Admin;
import com.ncapas.actividadl3capasv.Domain.Entities.Role;
import com.ncapas.actividadl3capasv.repository.iAdminRepository;
import com.ncapas.actividadl3capasv.repository.iRoleRepository;
import com.ncapas.actividadl3capasv.util.AuthorizationUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

/**
 * Inicializador de datos para crear roles y administradores por defecto
 */
@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private iRoleRepository roleRepository;

    @Autowired
    private iAdminRepository adminRepository;

    @Autowired
    private AuthorizationUtil authorizationUtil;

    @Override
    public void run(String... args) throws Exception {
        initializeRoles();
        initializeDefaultAdmin();
    }

    private void initializeRoles() {
        // Crear roles por defecto si no existen
        List<Role> defaultRoles = Arrays.asList(
            createRoleIfNotExists("SUPER_ADMIN", "Super Administrador con todos los permisos", 1),
            createRoleIfNotExists("USER", "Usuario regular", 2)
        );

        roleRepository.saveAll(defaultRoles);
        System.out.println("✅ Roles inicializados correctamente");
    }

    private Role createRoleIfNotExists(String name, String description, int priority) {
        Role existingRole = roleRepository.findByName(name);
        if (existingRole == null) {
            Role role = new Role();
            role.setName(name);
            role.setDescription(description);
            role.setPriority(priority);
            role.setActive(true);
            return role;
        }
        return existingRole;
    }

    private void initializeDefaultAdmin() {
        // Crear super administrador por defecto si no existe
        if (!adminRepository.existsByUsername("superadmin")) {
            Admin superAdmin = new Admin();
            superAdmin.setUsername("superadmin");
            superAdmin.setPassword("admin123"); // En producción, esto debería estar hasheado
            superAdmin.setPermissions(authorizationUtil.getSuperAdminPermissions());
            superAdmin.setSuperAdmin(true);
            superAdmin.setCreatedBy("SYSTEM");
            
            adminRepository.save(superAdmin);
            System.out.println("✅ Super administrador creado: superadmin / admin123");
        }

        // Crear administrador de ejemplo si no existe
        if (!adminRepository.existsByUsername("admin")) {
            Admin admin = new Admin();
            admin.setUsername("admin");
            admin.setPassword("admin123");
            admin.setPermissions(authorizationUtil.getSuperAdminPermissions());
            admin.setSuperAdmin(false);
            admin.setCreatedBy("SYSTEM");
            
            adminRepository.save(admin);
            System.out.println("✅ Administrador creado: admin / admin123");
        }
    }
} 