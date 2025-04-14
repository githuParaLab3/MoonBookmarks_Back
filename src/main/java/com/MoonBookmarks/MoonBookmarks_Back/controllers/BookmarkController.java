package com.MoonBookmarks.MoonBookmarks_Back.controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.MoonBookmarks.MoonBookmarks_Back.dto.BookmarkDTO;
import com.MoonBookmarks.MoonBookmarks_Back.entities.Colecao;
import com.MoonBookmarks.MoonBookmarks_Back.services.BookmarkService;
import com.MoonBookmarks.MoonBookmarks_Back.mappers.BookmarkMapper;
import com.MoonBookmarks.MoonBookmarks_Back.entities.Bookmark;

@RestController
@RequestMapping("/bookmarks")
public class BookmarkController {

    @Autowired
    private BookmarkService bookmarkService;

    @GetMapping
    public List<BookmarkDTO> listarBookmarks() {
        return bookmarkService.listarTodos().stream()
                .map(BookmarkMapper::toDTO) // Converte o Bookmark para DTO
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookmarkDTO> buscarBookmark(@PathVariable String id) {
        return bookmarkService.buscarPorId(id)
                .map(bookmark -> ResponseEntity.ok(BookmarkMapper.toDTO(bookmark))) // Converte o Bookmark para DTO
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<BookmarkDTO> criarBookmark(@RequestBody BookmarkDTO bookmarkDTO) {
        Bookmark bookmark = BookmarkMapper.fromDTO(bookmarkDTO); // Converte o DTO para entidade
        return ResponseEntity.status(201).body(BookmarkMapper.toDTO(bookmarkService.salvar(bookmark)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<BookmarkDTO> atualizarBookmark(@PathVariable String id, @RequestBody BookmarkDTO bookmarkDTO) {
        if (!bookmarkService.buscarPorId(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        Bookmark bookmark = BookmarkMapper.fromDTO(bookmarkDTO); // Converte o DTO para entidade
        bookmark.setId(id);
        return ResponseEntity.ok(BookmarkMapper.toDTO(bookmarkService.salvar(bookmark)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarBookmark(@PathVariable String id) {
        if (!bookmarkService.buscarPorId(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        bookmarkService.deletar(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<List<BookmarkDTO>> listarBookmarksPorUsuario(@PathVariable String usuarioId) {
        List<BookmarkDTO> bookmarks = bookmarkService.listarPorUsuario(usuarioId).stream()
                .map(BookmarkMapper::toDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(bookmarks);
    }

    @PostMapping("/{bookmarkId}/colecoes/{colecaoId}")
    public ResponseEntity<BookmarkDTO> adicionarColecao(@PathVariable String bookmarkId, @PathVariable String colecaoId) {
        Bookmark bookmark = bookmarkService.adicionarColecaoAoBookmark(bookmarkId, colecaoId);
        return ResponseEntity.ok(BookmarkMapper.toDTO(bookmark));
    }

    @DeleteMapping("/{bookmarkId}/colecoes/{colecaoId}")
    public ResponseEntity<BookmarkDTO> removerColecao(@PathVariable String bookmarkId, @PathVariable String colecaoId) {
        Bookmark bookmark = bookmarkService.removerColecaoDoBookmark(bookmarkId, colecaoId);
        return ResponseEntity.ok(BookmarkMapper.toDTO(bookmark));
    }

    @GetMapping("/{bookmarkId}/colecoes")
    public ResponseEntity<List<Colecao>> listarColecoes(@PathVariable String bookmarkId) {
        return ResponseEntity.ok(bookmarkService.listarColecoesDoBookmark(bookmarkId));
    }
}
