package com.MoonBookmarks.MoonBookmarks_Back.services;

import com.MoonBookmarks.MoonBookmarks_Back.dto.BookmarkDTO;
import com.MoonBookmarks.MoonBookmarks_Back.dto.ColecaoDTO;
import com.MoonBookmarks.MoonBookmarks_Back.entities.Bookmark;
import com.MoonBookmarks.MoonBookmarks_Back.entities.Colecao;
import com.MoonBookmarks.MoonBookmarks_Back.mappers.BookmarkMapper;
import com.MoonBookmarks.MoonBookmarks_Back.mappers.ColecaoMapper;
import com.MoonBookmarks.MoonBookmarks_Back.repositories.BookmarkRepository;
import com.MoonBookmarks.MoonBookmarks_Back.repositories.ColecaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BookmarkService {

    @Autowired
    private BookmarkRepository bookmarkRepository;

    @Autowired
    private ColecaoRepository colecaoRepository;

    public List<BookmarkDTO> listarTodos() {
        List<Bookmark> bookmarks = bookmarkRepository.findAll();
        return bookmarks.stream()
                        .map(BookmarkMapper::toDTO)
                        .collect(Collectors.toList());
    }

    public Optional<BookmarkDTO> buscarPorId(String id) {
        Optional<Bookmark> bookmark = bookmarkRepository.findById(id);
        return bookmark.map(BookmarkMapper::toDTO);
    }

    public BookmarkDTO salvar(BookmarkDTO bookmarkDTO) {
        Bookmark bookmark = BookmarkMapper.fromDTO(bookmarkDTO, colecaoRepository);  // Passando o repositório de coleções
        Bookmark savedBookmark = bookmarkRepository.save(bookmark);
        return BookmarkMapper.toDTO(savedBookmark);
    }

    public void deletar(String id) {
        bookmarkRepository.deleteById(id);
    }

    public List<BookmarkDTO> listarPorUsuario(String usuarioId) {
        List<Bookmark> bookmarks = bookmarkRepository.findByUsuarioId(usuarioId);
        return bookmarks.stream()
                        .map(BookmarkMapper::toDTO)
                        .collect(Collectors.toList());
    }

    public BookmarkDTO adicionarColecaoAoBookmark(String bookmarkId, String colecaoId) {
        Optional<Bookmark> bookmarkOpt = bookmarkRepository.findById(bookmarkId);
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

            colecaoRepository.save(colecao);
            Bookmark savedBookmark = bookmarkRepository.save(bookmark);
            return BookmarkMapper.toDTO(savedBookmark);
        }

        throw new RuntimeException("Bookmark ou Coleção não encontrado.");
    }

    public BookmarkDTO removerColecaoDoBookmark(String bookmarkId, String colecaoId) {
        Optional<Bookmark> bookmarkOpt = bookmarkRepository.findById(bookmarkId);
        Optional<Colecao> colecaoOpt = colecaoRepository.findById(colecaoId);

        if (bookmarkOpt.isPresent() && colecaoOpt.isPresent()) {
            Bookmark bookmark = bookmarkOpt.get();
            Colecao colecao = colecaoOpt.get();

            bookmark.getColecoes().remove(colecao);
            colecao.getBookmarks().remove(bookmark);

            colecaoRepository.save(colecao);
            Bookmark savedBookmark = bookmarkRepository.save(bookmark);
            return BookmarkMapper.toDTO(savedBookmark);
        }

        throw new RuntimeException("Bookmark ou Coleção não encontrado.");
    }

    public List<ColecaoDTO> listarColecoesDoBookmark(String bookmarkId) {
        Optional<Bookmark> bookmarkOpt = bookmarkRepository.findById(bookmarkId);
        if (bookmarkOpt.isPresent()) {
            return bookmarkOpt.get().getColecoes().stream()
                               .map(ColecaoMapper::toDTO)
                               .collect(Collectors.toList());
        }

        throw new RuntimeException("Bookmark não encontrado.");
    }
}
