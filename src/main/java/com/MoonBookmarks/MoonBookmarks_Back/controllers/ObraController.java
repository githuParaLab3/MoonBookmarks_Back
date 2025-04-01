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

import com.MoonBookmarks.MoonBookmarks_Back.entities.Obra;
import com.MoonBookmarks.MoonBookmarks_Back.services.ObraService;

@RestController
@RequestMapping("/obras")
public class ObraController {
    @Autowired
    private ObraService obraService;

    @GetMapping
    public List<Obra> listarObras() {
        return obraService.listarTodas();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Obra> buscarObra(@PathVariable String id) {
        return obraService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Obra> criarObra(@RequestBody Obra obra) {
        return ResponseEntity.status(201).body(obraService.salvar(obra));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Obra> atualizarObra(@PathVariable String id, @RequestBody Obra obra) {
        if (!obraService.buscarPorId(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        obra.setId(id);
        return ResponseEntity.ok(obraService.salvar(obra));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarObra(@PathVariable String id) {
        if (!obraService.buscarPorId(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        obraService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
