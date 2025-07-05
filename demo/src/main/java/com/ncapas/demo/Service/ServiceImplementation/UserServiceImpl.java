package com.ncapas.demo.Service.ServiceImplementation;

import com.ncapas.demo.Domain.Entity.User;
import com.ncapas.demo.Repository.UserRepository;
import com.ncapas.demo.Service.iUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements iUserService {
    @Autowired
    private UserRepository userRepository;

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Override
    public User register(String username, String password, String rol) {
        if (userRepository.findByUsername(username) != null) {
            throw new RuntimeException("El usuario ya existe");
        }
        String hashed = passwordEncoder.encode(password);
        User user = User.builder().username(username).password(hashed).rol(rol).build();
        return userRepository.save(user);
    }

    @Override
    public User authenticate(String username, String password) {
        User user = userRepository.findByUsername(username);
        if (user == null || !passwordEncoder.matches(password, user.getPassword())) {
            throw new RuntimeException("Credenciales inválidas");
        }
        return user;
    }
} 