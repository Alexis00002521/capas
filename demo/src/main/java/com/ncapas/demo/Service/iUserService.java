package com.ncapas.demo.Service;

import com.ncapas.demo.Domain.Entity.User;

public interface iUserService {
    User register(String username, String password);
    User authenticate(String username, String password);
} 