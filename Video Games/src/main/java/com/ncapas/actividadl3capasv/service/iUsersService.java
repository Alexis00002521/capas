package com.ncapas.actividadl3capasv.service;

import com.ncapas.actividadl3capasv.Domain.Entities.Users;

import java.util.List;
import java.util.UUID;

public interface iUsersService {
    Users createUser(Users user);
    Users getUserById(UUID id);
    List<Users> getAllUsers();
    Users updateUser(UUID id, Users user);
    void deleteUser(UUID id);
    Users getUserByUsername(String username);
} 