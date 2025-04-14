package com.MoonBookmarks.MoonBookmarks_Back.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.MoonBookmarks.MoonBookmarks_Back.dto.ColecaoDTO;
import com.MoonBookmarks.MoonBookmarks_Back.dto.BookmarkDTO;
import com.MoonBookmarks.MoonBookmarks_Back.services.ColecaoService;

import java.util.List;

@RestController
@RequestMapping("/colecoes")
public class ColecaoController {

    @Autowired
    private ColecaoService colecaoService;

    @GetMapping
    public ResponseEntity<List<ColecaoDTO>> listarColecoes() {
        List<ColecaoDTO> colecoes = colecaoService.listarTodas();
        return ResponseEntity.ok(colecoes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ColecaoDTO> buscarColecao(@PathVariable String id) {
        return colecaoService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<ColecaoDTO> criarColecao(@RequestBody ColecaoDTO colecaoDTO) {
        ColecaoDTO savedColecao = colecaoService.salvar(colecaoDTO);
        return ResponseEntity.status(201).body(savedColecao);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ColecaoDTO> atualizarColecao(@PathVariable String id, @RequestBody ColecaoDTO colecaoDTO) {
        // Atualizando a coleção com base no DTO recebido
        ColecaoDTO updatedColecao = colecaoService.salvar(colecaoDTO);
        return ResponseEntity.ok(updatedColecao);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarColecao(@PathVariable String id) {
        colecaoService.deletar(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{colecaoId}/bookmarks/{bookmarkId}")
    public ResponseEntity<ColecaoDTO> adicionarBookmark(@PathVariable String colecaoId, @PathVariable String bookmarkId) {
        // Adicionando bookmark à coleção
        ColecaoDTO updatedColecao = colecaoService.adicionarBookmarkNaColecao(colecaoId, bookmarkId);
        return ResponseEntity.ok(updatedColecao);
    }

    @DeleteMapping("/{colecaoId}/bookmarks/{bookmarkId}")
    public ResponseEntity<ColecaoDTO> removerBookmark(@PathVariable String colecaoId, @PathVariable String bookmarkId) {
        // Removendo bookmark da coleção
        ColecaoDTO updatedColecao = colecaoService.removerBookmarkDaColecao(colecaoId, bookmarkId);
        return ResponseEntity.ok(updatedColecao);
    }

    @GetMapping("/{colecaoId}/bookmarks")
    public ResponseEntity<List<BookmarkDTO>> listarBookmarks(@PathVariable String colecaoId) {
        List<BookmarkDTO> bookmarks = colecaoService.listarBookmarksDaColecao(colecaoId);
        return ResponseEntity.ok(bookmarks);
    }
}
