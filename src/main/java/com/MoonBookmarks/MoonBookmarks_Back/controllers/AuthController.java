package com.MoonBookmarks.MoonBookmarks_Back.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.MoonBookmarks.MoonBookmarks_Back.dto.AuthRequest;
import com.MoonBookmarks.MoonBookmarks_Back.entities.Usuario;
import com.MoonBookmarks.MoonBookmarks_Back.repositories.UsuarioRepository;
import com.MoonBookmarks.MoonBookmarks_Back.util.JwtUtil;
import com.MoonBookmarks.MoonBookmarks_Back.dto.AuthResponse;

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
    public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest request) {
        // Verifica se o email ou senha estão vazios
        if (request.getEmail() == null || request.getEmail().isEmpty() || request.getSenha() == null || request.getSenha().isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    
        Optional<Usuario> usuarioOpt = usuarioRepository.findByEmail(request.getEmail());
    
        // Verifica se o usuário existe e a senha corresponde
        if (usuarioOpt.isPresent() && passwordEncoder.matches(request.getSenha(), usuarioOpt.get().getSenha())) {
            // Gera o token JWT
            String token = jwtUtil.generateToken(request.getEmail());
    
            // Prepara a resposta com os dados do usuário e o token
            Usuario usuario = usuarioOpt.get();
            AuthResponse response = new AuthResponse();
            response.setToken(token);
            response.setUserId(usuario.getId().toString());
            response.setNome(usuario.getNome());
            response.setEmail(usuario.getEmail());
    
            return ResponseEntity.ok(response); // Retorna o AuthResponse com o token e o id do usuário
        }
    
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
    }


    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody Usuario usuario) {
        // Verifica se o email já está cadastrado
        if (usuarioRepository.findByEmail(usuario.getEmail()).isPresent()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Usuário já registrado com esse e-mail");
        }

        // Verifica se o email e a senha não estão vazios
        if (usuario.getEmail() == null || usuario.getEmail().isEmpty() || usuario.getSenha() == null || usuario.getSenha().isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Email e senha não podem ser vazios");
        }

        // Criptografa a senha
        usuario.setSenha(passwordEncoder.encode(usuario.getSenha()));

        // Salva o usuário no banco
        usuarioRepository.save(usuario);

        return ResponseEntity.status(HttpStatus.CREATED).body("Usuário registrado com sucesso!");
    }
}
