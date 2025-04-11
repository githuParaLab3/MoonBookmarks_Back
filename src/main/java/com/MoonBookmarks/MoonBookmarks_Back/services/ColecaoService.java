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

    @Autowired
    private ColecaoRepository colecaoRepo;

    @Autowired
    private BookmarkRepository bookmarkRepo;

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

    public Colecao adicionarBookmarkNaColecao(String colecaoId, String bookmarkId) {
        Optional<Colecao> colecaoOpt = colecaoRepo.findById(colecaoId);
        Optional<Bookmark> bookmarkOpt = bookmarkRepo.findById(bookmarkId);

        if (colecaoOpt.isPresent() && bookmarkOpt.isPresent()) {
            Colecao colecao = colecaoOpt.get();
            Bookmark bookmark = bookmarkOpt.get();

            if (!colecao.getBookmarks().contains(bookmark)) {
                colecao.getBookmarks().add(bookmark);
            }

            if (!bookmark.getColecoes().contains(colecao)) {
                bookmark.getColecoes().add(colecao);
                bookmarkRepo.save(bookmark); // salva dos dois lados
            }

            return colecaoRepo.save(colecao);
        }

        throw new RuntimeException("Coleção ou Bookmark não encontrado.");
    }

    public Colecao removerBookmarkDaColecao(String colecaoId, String bookmarkId) {
        Optional<Colecao> colecaoOpt = colecaoRepo.findById(colecaoId);
        Optional<Bookmark> bookmarkOpt = bookmarkRepo.findById(bookmarkId);

        if (colecaoOpt.isPresent() && bookmarkOpt.isPresent()) {
            Colecao colecao = colecaoOpt.get();
            Bookmark bookmark = bookmarkOpt.get();

            colecao.getBookmarks().remove(bookmark);
            bookmark.getColecoes().remove(colecao);

            bookmarkRepo.save(bookmark);
            return colecaoRepo.save(colecao);
        }

        throw new RuntimeException("Coleção ou Bookmark não encontrado.");
    }

    public List<Bookmark> listarBookmarksDaColecao(String colecaoId) {
        Optional<Colecao> colecaoOpt = colecaoRepo.findById(colecaoId);
        if (colecaoOpt.isPresent()) {
            return colecaoOpt.get().getBookmarks();
        }
        throw new RuntimeException("Coleção não encontrada.");
    }
   
}
