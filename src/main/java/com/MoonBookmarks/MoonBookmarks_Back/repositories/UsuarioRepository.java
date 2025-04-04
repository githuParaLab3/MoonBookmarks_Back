package com.MoonBookmarks.MoonBookmarks_Back.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.MoonBookmarks.MoonBookmarks_Back.entities.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, String> {
    Optional<Usuario> findByEmail(String email);
 }

