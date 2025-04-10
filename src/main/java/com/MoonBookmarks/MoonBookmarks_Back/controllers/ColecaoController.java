package com.MoonBookmarks.MoonBookmarks_Back.controllers;

import java.util.List;
import java.util.Optional;

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
import com.MoonBookmarks.MoonBookmarks_Back.entities.Colecao;
import com.MoonBookmarks.MoonBookmarks_Back.services.BookmarkService;
import com.MoonBookmarks.MoonBookmarks_Back.services.ColecaoService;

@RestController
@RequestMapping("/colecoes")
public class ColecaoController {
    @Autowired
    private ColecaoService colecaoService;

    @Autowired
    private BookmarkService bookmarkService;

    @GetMapping
    public List<Colecao> listarColecoes() {
        return colecaoService.listarTodas();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Colecao> buscarColecao(@PathVariable String id) {
        return colecaoService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Colecao> criarColecao(@RequestBody Colecao colecao) {
        return ResponseEntity.status(201).body(colecaoService.salvar(colecao));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Colecao> atualizarColecao(@PathVariable String id, @RequestBody Colecao colecao) {
        if (!colecaoService.buscarPorId(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        colecao.setId(id);
        return ResponseEntity.ok(colecaoService.salvar(colecao));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarColecao(@PathVariable String id) {
        if (!colecaoService.buscarPorId(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        colecaoService.deletar(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{colecaoId}/bookmarks")
    public ResponseEntity<Colecao> adicionarBookmarksNaColecao(
            @PathVariable String colecaoId,
            @RequestBody String bookmarkId) {

        // Log para verificar se o bookmarkId e colecaoId estão chegando corretamente
        System.out.println("Coleção ID: " + colecaoId);
        System.out.println("Bookmark ID: " + bookmarkId);

        // Buscar a coleção pelo ID
        Optional<Colecao> colecaoOptional = colecaoService.buscarPorId(colecaoId);
        if (colecaoOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Colecao colecao = colecaoOptional.get();

        // Buscar o bookmark pelo ID
        Optional<Bookmark> bookmarkOptional = bookmarkService.buscarPorId(bookmarkId);
        if (bookmarkOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Bookmark bookmark = bookmarkOptional.get();

        // Adicionar o bookmark à coleção
        colecao.getBookmarks().add(bookmark);
        colecaoService.salvar(colecao);

        // Log para verificar a coleção após salvar
        System.out.println("Coleção após adicionar o bookmark: " + colecao);

        // Retornar a coleção atualizada
        return ResponseEntity.ok(colecao);
    }

}
