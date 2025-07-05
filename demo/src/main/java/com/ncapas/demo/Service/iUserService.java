package com.ncapas.demo.Service;

import com.ncapas.demo.Domain.Entity.User;

public interface iUserService {
    User register(String username, String password, String rol);
    User authenticate(String username, String password);
} 