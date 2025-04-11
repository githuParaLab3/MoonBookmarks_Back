package com.MoonBookmarks.MoonBookmarks_Back.services;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.MoonBookmarks.MoonBookmarks_Back.entities.Bookmark;
import com.MoonBookmarks.MoonBookmarks_Back.entities.Colecao;
import com.MoonBookmarks.MoonBookmarks_Back.repositories.BookmarkRepository;
import com.MoonBookmarks.MoonBookmarks_Back.repositories.ColecaoRepository;

@Service
public class ColecaoService {
    @Autowired
    private ColecaoRepository colecaoRepository;

    public List<Colecao> listarTodas() {
        return colecaoRepository.findAll();
    }

    public Optional<Colecao> buscarPorId(String id) {
        return colecaoRepository.findById(id);
    }

    public Colecao salvar(Colecao colecao) {
        return colecaoRepository.save(colecao);
    }

    public void deletar(String id) {
        colecaoRepository.deleteById(id);
    }

   
}
