package com.MoonBookmarks.MoonBookmarks_Back.dto;

public class AuthResponse {
    private String token;
    private String userId;
    private String nome;
    private String email;

    // Construtor sem parâmetros (se não quiser usar um construtor com parâmetros)
    public AuthResponse() {}

    // Getters e setters
    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
