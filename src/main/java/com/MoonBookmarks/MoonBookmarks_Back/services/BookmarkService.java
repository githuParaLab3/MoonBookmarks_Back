package com.MoonBookmarks.MoonBookmarks_Back.services;

import com.MoonBookmarks.MoonBookmarks_Back.entities.Bookmark;
import com.MoonBookmarks.MoonBookmarks_Back.entities.Colecao;
import com.MoonBookmarks.MoonBookmarks_Back.repositories.BookmarkRepository;
import com.MoonBookmarks.MoonBookmarks_Back.repositories.ColecaoRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookmarkService {

    @Autowired
    private BookmarkRepository bookmarkRepository;

    @Autowired
    private ColecaoRepository colecaoRepository;

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

    public Bookmark adicionarColecaoAoBookmark(
        String bookmarkId,
        String colecaoId
    ) {
        Optional<Bookmark> bookmarkOpt = bookmarkRepository.findById(
            bookmarkId
        );
        Optional<Colecao> colecaoOpt = colecaoRepository.findById(colecaoId);

        if (bookmarkOpt.isPresent() && colecaoOpt.isPresent()) {
            Bookmark bookmark = bookmarkOpt.get();
            Colecao colecao = colecaoOpt.get();

            if (!bookmark.getColecoes().contains(colecao)) {
                bookmark.getColecoes().add(colecao);
            }

            if (!colecao.getBookmarks().contains(bookmark)) {
                colecao.getBookmarks().add(bookmark);
            }

            colecaoRepository.save(colecao); // Salvar colecao
            return bookmarkRepository.save(bookmark); // Salvar bookmark
        }

        throw new RuntimeException("Bookmark ou Coleção não encontrado.");
    }

    // Método para remover uma coleção do bookmark
    public Bookmark removerColecaoDoBookmark(
        String bookmarkId,
        String colecaoId
    ) {
        Optional<Bookmark> bookmarkOpt = bookmarkRepository.findById(
            bookmarkId
        );
        Optional<Colecao> colecaoOpt = colecaoRepository.findById(colecaoId);

        if (bookmarkOpt.isPresent() && colecaoOpt.isPresent()) {
            Bookmark bookmark = bookmarkOpt.get();
            Colecao colecao = colecaoOpt.get();

            bookmark.getColecoes().remove(colecao);
            colecao.getBookmarks().remove(bookmark);

            colecaoRepository.save(colecao); // Salvar colecao
            return bookmarkRepository.save(bookmark); // Salvar bookmark
        }

        throw new RuntimeException("Bookmark ou Coleção não encontrado.");
    }

    // Método para listar as coleções de um bookmark
    public List<Colecao> listarColecoesDoBookmark(String bookmarkId) {
        Optional<Bookmark> bookmarkOpt = bookmarkRepository.findById(
            bookmarkId
        );
        if (bookmarkOpt.isPresent()) {
            return bookmarkOpt.get().getColecoes();
        }

        throw new RuntimeException("Bookmark não encontrado.");
    }
}
