package com.ncapas.demo.Controller;

import com.ncapas.demo.Domain.Entity.User;
import com.ncapas.demo.Service.iUserService;
import com.ncapas.demo.Util.JwtUtil;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    private iUserService userService;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody AuthRequest request) {
        try {
            userService.register(request.getUsername(), request.getPassword(), request.getRol());
            return ResponseEntity.ok("Registrado correctamente");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthRequest request) {
        try {
            User user = userService.authenticate(request.getUsername(), request.getPassword());
            String token = jwtUtil.generateToken(user.getUsername());
            return ResponseEntity.ok(new JwtResponse(token));
        } catch (Exception e) {
            return ResponseEntity.status(401).body(e.getMessage());
        }
    }

    @Data
    public static class AuthRequest {
        private String username;
        private String password;
        private String rol; // Nuevo campo para el rol
    }

    @Data
    public static class JwtResponse {
        private final String token;
    }
} 