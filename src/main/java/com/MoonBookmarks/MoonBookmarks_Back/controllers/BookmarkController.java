package com.MoonBookmarks.MoonBookmarks_Back.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.MoonBookmarks.MoonBookmarks_Back.dto.BookmarkDTO;
import com.MoonBookmarks.MoonBookmarks_Back.dto.ColecaoDTO;
import com.MoonBookmarks.MoonBookmarks_Back.services.BookmarkService;
import java.util.List;

@RestController
@RequestMapping("/bookmarks")
public class BookmarkController {

    @Autowired
    private BookmarkService bookmarkService;

    @GetMapping
    public ResponseEntity<List<BookmarkDTO>> listarBookmarks() {
        List<BookmarkDTO> bookmarks = bookmarkService.listarTodos();
        return ResponseEntity.ok(bookmarks);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookmarkDTO> buscarBookmark(@PathVariable String id) {
        return bookmarkService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<BookmarkDTO> criarBookmark(@RequestBody BookmarkDTO bookmarkDTO) {
        BookmarkDTO savedBookmark = bookmarkService.salvar(bookmarkDTO);
        return ResponseEntity.status(201).body(savedBookmark);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BookmarkDTO> atualizarBookmark(@PathVariable String id, @RequestBody BookmarkDTO bookmarkDTO) {
        // Atualizando bookmark com base no DTO recebido
        BookmarkDTO updatedBookmark = bookmarkService.salvar(bookmarkDTO);
        return ResponseEntity.ok(updatedBookmark);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarBookmark(@PathVariable String id) {
        bookmarkService.deletar(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{bookmarkId}/colecoes/{colecaoId}")
    public ResponseEntity<BookmarkDTO> adicionarColecao(@PathVariable String bookmarkId, @PathVariable String colecaoId) {
        // Adicionando coleção ao bookmark
        BookmarkDTO updatedBookmark = bookmarkService.adicionarColecaoAoBookmark(bookmarkId, colecaoId);
        return ResponseEntity.ok(updatedBookmark);
    }

    @DeleteMapping("/{bookmarkId}/colecoes/{colecaoId}")
    public ResponseEntity<BookmarkDTO> removerColecao(@PathVariable String bookmarkId, @PathVariable String colecaoId) {
        // Removendo coleção do bookmark
        BookmarkDTO updatedBookmark = bookmarkService.removerColecaoDoBookmark(bookmarkId, colecaoId);
        return ResponseEntity.ok(updatedBookmark);
    }

    @GetMapping("/{bookmarkId}/colecoes")
    public ResponseEntity<List<ColecaoDTO>> listarColecoes(@PathVariable String bookmarkId) {
        // Listando coleções associadas a um bookmark
        List<ColecaoDTO> colecoes = bookmarkService.listarColecoesDoBookmark(bookmarkId);
        return ResponseEntity.ok(colecoes);
    }
}
