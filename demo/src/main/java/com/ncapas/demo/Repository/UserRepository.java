package com.ncapas.demo.Repository;

import com.ncapas.demo.Domain.Entity.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends iGenericRepository<User, Long> {
    User findByUsername(String username);
} 