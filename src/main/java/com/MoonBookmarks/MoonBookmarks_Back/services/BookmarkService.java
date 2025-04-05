package com.MoonBookmarks.MoonBookmarks_Back.services;

import com.MoonBookmarks.MoonBookmarks_Back.entities.Bookmark;
import com.MoonBookmarks.MoonBookmarks_Back.repositories.BookmarkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class BookmarkService {
    @Autowired
    private BookmarkRepository bookmarkRepository;

    public List<Bookmark> listarTodos() {
        return bookmarkRepository.findAll();
    }

    public Optional<Bookmark> buscarPorId(String id) {
        return bookmarkRepository.findById(id);
    }

    public Bookmark salvar(Bookmark bookmark) {
        return bookmarkRepository.save(bookmark);
    }

    public void deletar(String id) {
        bookmarkRepository.deleteById(id);
    }

    // 🚀 Novo método para buscar bookmarks por usuário
    public List<Bookmark> listarPorUsuario(String usuarioId) {
        return bookmarkRepository.findByUsuarioId(usuarioId);
    }
}
