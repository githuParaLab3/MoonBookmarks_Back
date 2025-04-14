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
public class ColecaoService {

    @Autowired
    private ColecaoRepository colecaoRepository;

    @Autowired
    private BookmarkRepository bookmarkRepository;

    public List<ColecaoDTO> listarTodas() {
        List<Colecao> colecoes = colecaoRepository.findAll();
        return colecoes.stream()
                       .map(ColecaoMapper::toDTO)
                       .collect(Collectors.toList());
    }

    public Optional<ColecaoDTO> buscarPorId(String id) {
        Optional<Colecao> colecao = colecaoRepository.findById(id);
        return colecao.map(ColecaoMapper::toDTO);
    }

    public ColecaoDTO salvar(ColecaoDTO colecaoDTO) {
        // Verificando se a coleção já existe com base no ID
        Optional<Colecao> colecaoOpt = colecaoRepository.findById(colecaoDTO.getId());
        
        if (colecaoOpt.isPresent()) {
            // Coleção existe, então vamos atualizar
            Colecao colecaoExistente = colecaoOpt.get();
            
            // Atualizando os dados da coleção existente
            colecaoExistente.setTitulo(colecaoDTO.getTitulo());
            colecaoExistente.setFoto(colecaoDTO.getFoto()); 
            colecaoExistente.setDescricao(colecaoDTO.getDescricao());
            // Adicione outros campos que você precisa atualizar
            
            // Salvando a coleção atualizada
            Colecao colecaoAtualizada = colecaoRepository.save(colecaoExistente);
            return ColecaoMapper.toDTO(colecaoAtualizada);
        } else {
            // Se a coleção não existir, criamos uma nova
            Colecao colecaoNova = ColecaoMapper.fromDTO(colecaoDTO, bookmarkRepository);
            Colecao colecaoCriada = colecaoRepository.save(colecaoNova);
            return ColecaoMapper.toDTO(colecaoCriada);
        }
    }


    public void deletar(String id) {
        colecaoRepository.deleteById(id);
    }

    public ColecaoDTO adicionarBookmarkNaColecao(String colecaoId, String bookmarkId) {
        Optional<Colecao> colecaoOpt = colecaoRepository.findById(colecaoId);
        Optional<Bookmark> bookmarkOpt = bookmarkRepository.findById(bookmarkId);

        if (colecaoOpt.isPresent() && bookmarkOpt.isPresent()) {
            Colecao colecao = colecaoOpt.get();
            Bookmark bookmark = bookmarkOpt.get();

            if (!colecao.getBookmarks().contains(bookmark)) {
                colecao.getBookmarks().add(bookmark);
            }

            if (!bookmark.getColecoes().contains(colecao)) {
                bookmark.getColecoes().add(colecao);
                bookmarkRepository.save(bookmark);
            }

            Colecao savedColecao = colecaoRepository.save(colecao);
            return ColecaoMapper.toDTO(savedColecao);
        }

        throw new RuntimeException("Coleção ou Bookmark não encontrado.");
    }

    public ColecaoDTO removerBookmarkDaColecao(String colecaoId, String bookmarkId) {
        Optional<Colecao> colecaoOpt = colecaoRepository.findById(colecaoId);
        Optional<Bookmark> bookmarkOpt = bookmarkRepository.findById(bookmarkId);

        if (colecaoOpt.isPresent() && bookmarkOpt.isPresent()) {
            Colecao colecao = colecaoOpt.get();
            Bookmark bookmark = bookmarkOpt.get();

            colecao.getBookmarks().remove(bookmark);
            bookmark.getColecoes().remove(colecao);

            bookmarkRepository.save(bookmark);
            Colecao savedColecao = colecaoRepository.save(colecao);
            return ColecaoMapper.toDTO(savedColecao);
        }

        throw new RuntimeException("Coleção ou Bookmark não encontrado.");
    }

    public List<BookmarkDTO> listarBookmarksDaColecao(String colecaoId) {
        Optional<Colecao> colecaoOpt = colecaoRepository.findById(colecaoId);
        if (colecaoOpt.isPresent()) {
            Colecao colecao = colecaoOpt.get();
            return colecao.getBookmarks().stream()
                          .map(BookmarkMapper::toDTO)
                          .collect(Collectors.toList());
        }
        throw new RuntimeException("Coleção não encontrada.");
    }
}
