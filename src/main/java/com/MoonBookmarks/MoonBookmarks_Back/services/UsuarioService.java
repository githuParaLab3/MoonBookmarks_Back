package com.MoonBookmarks.MoonBookmarks_Back.services;


import com.MoonBookmarks.MoonBookmarks_Back.entities.Usuario;
import com.MoonBookmarks.MoonBookmarks_Back.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {
    @Autowired
    private UsuarioRepository usuarioRepository;

    public List<Usuario> listarTodos() {
        return usuarioRepository.findAll();
    }

    public Optional<Usuario> buscarPorId(String id) {
        return usuarioRepository.findById(id);
    }

    public Usuario salvar(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    public void deletar(String id) {
        usuarioRepository.deleteById(id);
    }
}
