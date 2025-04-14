package com.MoonBookmarks.MoonBookmarks_Back.mappers;

import com.MoonBookmarks.MoonBookmarks_Back.dto.ColecaoDTO;
import com.MoonBookmarks.MoonBookmarks_Back.entities.Colecao;
import com.MoonBookmarks.MoonBookmarks_Back.repositories.BookmarkRepository;
import java.util.stream.Collectors;

public class ColecaoMapper {

    // Mapeia a entidade Colecao para o DTO ColecaoDTO
    public static ColecaoDTO toDTO(Colecao colecao) {
        ColecaoDTO colecaoDTO = new ColecaoDTO();
        colecaoDTO.setId(colecao.getId());
        colecaoDTO.setTitulo(colecao.getTitulo());
        colecaoDTO.setDescricao(colecao.getDescricao());
        colecaoDTO.setFoto(colecao.getFoto());
        colecaoDTO.setUsuario(colecao.getUsuario());

        // Carregar apenas os IDs das bookmarks
        colecaoDTO.setBookmarkIds(colecao.getBookmarks().stream()
                                          .map(bookmark -> bookmark.getId()) // Pegando apenas o ID do bookmark
                                          .collect(Collectors.toList()));

        return colecaoDTO;
    }

    // Mapeia o DTO ColecaoDTO de volta para a entidade Colecao
    public static Colecao fromDTO(ColecaoDTO dto, BookmarkRepository bookmarkRepository) {
        Colecao colecao = new Colecao();
        colecao.setId(dto.getId());
        colecao.setTitulo(dto.getTitulo());
        colecao.setDescricao(dto.getDescricao());
        colecao.setFoto(dto.getFoto());
        colecao.setUsuario(dto.getUsuario());

        // Buscar as bookmarks no banco com base nos IDs
        if (dto.getBookmarkIds() != null) {
            colecao.setBookmarks(bookmarkRepository.findAllById(dto.getBookmarkIds()));  // Busca as bookmarks no banco usando os IDs
        }

        return colecao;
    }
}
