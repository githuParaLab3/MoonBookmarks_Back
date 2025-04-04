package com.MoonBookmarks.MoonBookmarks_Back.controllers;

import com.MoonBookmarks.MoonBookmarks_Back.dto.AuthRequest;
import com.MoonBookmarks.MoonBookmarks_Back.entities.Usuario;
import com.MoonBookmarks.MoonBookmarks_Back.repositories.UsuarioRepository;
import com.MoonBookmarks.MoonBookmarks_Back.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/login")
    public String login(@RequestBody AuthRequest request) {
        Optional<Usuario> usuarioOpt = usuarioRepository.findByEmail(request.getEmail());

        if (usuarioOpt.isPresent() && passwordEncoder.matches(request.getSenha(), usuarioOpt.get().getSenha())) {
            return jwtUtil.generateToken(request.getEmail());
        }

        throw new RuntimeException("Credenciais inválidas");
    }
}
