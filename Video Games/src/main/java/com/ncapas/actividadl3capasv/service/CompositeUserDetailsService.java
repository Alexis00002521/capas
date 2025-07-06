package com.ncapas.actividadl3capasv.service;

import com.ncapas.actividadl3capasv.Domain.Entities.Admin;
import com.ncapas.actividadl3capasv.repository.iAdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class CompositeUserDetailsService implements UserDetailsService {
    private final iAdminRepository adminRepository;

    @Autowired
    public CompositeUserDetailsService(iAdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Admin admin = adminRepository.findByUsername(username);
        if (admin != null) {
            List<GrantedAuthority> authorities = new ArrayList<>();
            // Si es super admin, asigna el rol
            if (admin.isSuperAdmin()) {
                authorities.add(new SimpleGrantedAuthority("ROLE_SUPER_ADMIN"));
            } else {
                authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
            }
            // También puedes agregar permisos específicos si los tienes
            if (admin.getPermissions() != null) {
                Arrays.stream(admin.getPermissions().split(","))
                        .map(String::trim)
                        .filter(p -> !p.isEmpty())
                        .forEach(p -> authorities.add(new SimpleGrantedAuthority(p)));
            }
            return new User(admin.getUsername(), admin.getPassword(), authorities);
        }
        // Si no está en la base de datos, lanza excepción
        throw new UsernameNotFoundException("Usuario no encontrado: " + username);
    }
} 