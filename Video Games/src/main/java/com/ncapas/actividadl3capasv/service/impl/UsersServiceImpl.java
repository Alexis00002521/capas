package com.ncapas.actividadl3capasv.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ncapas.actividadl3capasv.Domain.Entities.Users;
import com.ncapas.actividadl3capasv.repository.iUsersRepository;
import com.ncapas.actividadl3capasv.service.iUsersService;

import java.util.List;
import java.util.UUID;
import org.springframework.security.crypto.password.PasswordEncoder;

@Service
public class UsersServiceImpl implements iUsersService {

    @Autowired
    private iUsersRepository usersRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public Users createUser(Users user) {
        // Validar que el username no exista
        if (usersRepository.existsByUsername(user.getUsername())) {
            throw new RuntimeException("El nombre de usuario ya existe");
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return usersRepository.save(user);
    }

    @Override
    public Users getUserById(UUID id) {
        return usersRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con ID: " + id));
    }

    @Override
    public List<Users> getAllUsers() {
        return usersRepository.findAll();
    }

    @Override
    public Users updateUser(UUID id, Users user) {
        Users existingUser = getUserById(id);
        
        // Actualizar campos si no son null
        if (user.getUsername() != null && !user.getUsername().equals(existingUser.getUsername())) {
            if (usersRepository.existsByUsername(user.getUsername())) {
                throw new RuntimeException("El nombre de usuario ya existe");
            }
            existingUser.setUsername(user.getUsername());
        }
        
        if (user.getPassword() != null) {
            existingUser.setPassword(passwordEncoder.encode(user.getPassword()));
        }
        
        if (user.getRoles() != null) {
            existingUser.setRoles(user.getRoles());
        }
        
        return usersRepository.save(existingUser);
    }

    @Override
    public void deleteUser(UUID id) {
        if (!usersRepository.existsById(id)) {
            throw new RuntimeException("Usuario no encontrado con ID: " + id);
        }
        usersRepository.deleteById(id);
    }

    @Override
    public Users getUserByUsername(String username) {
        return usersRepository.findByUsername(username);
    }
} 