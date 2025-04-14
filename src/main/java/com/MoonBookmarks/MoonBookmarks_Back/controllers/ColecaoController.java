package com.MoonBookmarks.MoonBookmarks_Back.controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.MoonBookmarks.MoonBookmarks_Back.dto.ColecaoDTO;
import com.MoonBookmarks.MoonBookmarks_Back.entities.Bookmark;
import com.MoonBookmarks.MoonBookmarks_Back.services.ColecaoService;
import com.MoonBookmarks.MoonBookmarks_Back.mappers.ColecaoMapper;
import com.MoonBookmarks.MoonBookmarks_Back.entities.Colecao;

@RestController
@RequestMapping("/colecoes")
public class ColecaoController {

    @Autowired
    private ColecaoService colecaoService;

    @GetMapping
    public List<ColecaoDTO> listarColecoes() {
        return colecaoService.listarTodas().stream()
                .map(ColecaoMapper::toDTO) // Converte a Coleção para DTO
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ColecaoDTO> buscarColecao(@PathVariable String id) {
        return colecaoService.buscarPorId(id)
                .map(colecao -> ResponseEntity.ok(ColecaoMapper.toDTO(colecao))) // Converte a Coleção para DTO
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<ColecaoDTO> criarColecao(@RequestBody ColecaoDTO colecaoDTO) {
        Colecao colecao = ColecaoMapper.fromDTO(colecaoDTO); // Converte o DTO para entidade
        return ResponseEntity.status(201).body(ColecaoMapper.toDTO(colecaoService.salvar(colecao)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ColecaoDTO> atualizarColecao(@PathVariable String id, @RequestBody ColecaoDTO colecaoDTO) {
        if (!colecaoService.buscarPorId(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        Colecao colecao = ColecaoMapper.fromDTO(colecaoDTO); // Converte o DTO para entidade
        colecao.setId(id);
        return ResponseEntity.ok(ColecaoMapper.toDTO(colecaoService.salvar(colecao)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarColecao(@PathVariable String id) {
        if (!colecaoService.buscarPorId(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        colecaoService.deletar(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{colecaoId}/bookmarks/{bookmarkId}")
    public ColecaoDTO adicionarBookmark(@PathVariable String colecaoId, @PathVariable String bookmarkId) {
        Colecao colecao = colecaoService.adicionarBookmarkNaColecao(colecaoId, bookmarkId);
        return ColecaoMapper.toDTO(colecao); // Retorna o DTO da coleção
    }

    @DeleteMapping("/{colecaoId}/bookmarks/{bookmarkId}")
    public ColecaoDTO removerBookmark(@PathVariable String colecaoId, @PathVariable String bookmarkId) {
        Colecao colecao = colecaoService.removerBookmarkDaColecao(colecaoId, bookmarkId);
        return ColecaoMapper.toDTO(colecao); // Retorna o DTO da coleção
    }

    @GetMapping("/{colecaoId}/bookmarks")
    public ResponseEntity<List<Bookmark>> listarBookmarks(@PathVariable String colecaoId) {
        List<Bookmark> bookmarks = colecaoService.listarBookmarksDaColecao(colecaoId);
        if (bookmarks.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(bookmarks);
    }
}
