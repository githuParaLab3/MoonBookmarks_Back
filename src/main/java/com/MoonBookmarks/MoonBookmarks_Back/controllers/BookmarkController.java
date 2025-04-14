package com.MoonBookmarks.MoonBookmarks_Back.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.MoonBookmarks.MoonBookmarks_Back.entities.Bookmark;
import com.MoonBookmarks.MoonBookmarks_Back.services.BookmarkService;
import com.MoonBookmarks.MoonBookmarks_Back.entities.Colecao;
@RestController
@RequestMapping("/bookmarks")
public class BookmarkController {

    @Autowired
    private BookmarkService bookmarkService;

    @GetMapping
    public List<Bookmark> listarBookmarks() {
        return bookmarkService.listarTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Bookmark> buscarBookmark(@PathVariable String id) {
        return bookmarkService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Bookmark> criarBookmark(@RequestBody Bookmark bookmark) {
        return ResponseEntity.status(201).body(bookmarkService.salvar(bookmark));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Bookmark> atualizarBookmark(@PathVariable String id, @RequestBody Bookmark bookmark) {
        if (!bookmarkService.buscarPorId(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        bookmark.setId(id);
        return ResponseEntity.ok(bookmarkService.salvar(bookmark));
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
    public ResponseEntity<List<Bookmark>> listarBookmarksPorUsuario(@PathVariable String usuarioId) {
        List<Bookmark> bookmarks = bookmarkService.listarPorUsuario(usuarioId);
        return ResponseEntity.ok(bookmarks);
    }
    
    @PostMapping("/{bookmarkId}/colecoes/{colecaoId}")
       public ResponseEntity<Bookmark> adicionarColecao(@PathVariable String bookmarkId, @PathVariable String colecaoId) {
           return ResponseEntity.ok(bookmarkService.adicionarColecaoAoBookmark(bookmarkId, colecaoId));
       }
   
       @DeleteMapping("/{bookmarkId}/colecoes/{colecaoId}")
       public ResponseEntity<Bookmark> removerColecao(@PathVariable String bookmarkId, @PathVariable String colecaoId) {
           return ResponseEntity.ok(bookmarkService.removerColecaoDoBookmark(bookmarkId, colecaoId));
       }
   
       @GetMapping("/{bookmarkId}/colecoes")
       public ResponseEntity<List<Colecao>> listarColecoes(@PathVariable String bookmarkId) {
           return ResponseEntity.ok(bookmarkService.listarColecoesDoBookmark(bookmarkId));
       }
    
    
    
}
