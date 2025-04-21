package com.MoonBookmarks.MoonBookmarks_Back.mappers;

import com.MoonBookmarks.MoonBookmarks_Back.dto.ColecaoDTO;
import com.MoonBookmarks.MoonBookmarks_Back.entities.Colecao;
import com.MoonBookmarks.MoonBookmarks_Back.repositories.BookmarkRepository;
import java.util.stream.Collectors;

public class ColecaoMapper {

    public static ColecaoDTO toDTO(Colecao colecao) {
        ColecaoDTO colecaoDTO = new ColecaoDTO();
        colecaoDTO.setId(colecao.getId());
        colecaoDTO.setTitulo(colecao.getTitulo());
        colecaoDTO.setDescricao(colecao.getDescricao());
        colecaoDTO.setFoto(colecao.getFoto());
        colecaoDTO.setUsuario(colecao.getUsuario());

        colecaoDTO.setBookmarkIds(
            colecao
                .getBookmarks()
                .stream()
                .map(bookmark -> bookmark.getId())
                .collect(Collectors.toList())
        );

        return colecaoDTO;
    }

    public static Colecao fromDTO(
        ColecaoDTO dto,
        BookmarkRepository bookmarkRepository
    ) {
        Colecao colecao = new Colecao();
        colecao.setId(dto.getId());
        colecao.setTitulo(dto.getTitulo());
        colecao.setDescricao(dto.getDescricao());
        colecao.setFoto(dto.getFoto());
        colecao.setUsuario(dto.getUsuario());

        if (dto.getBookmarkIds() != null) {
            colecao.setBookmarks(
                bookmarkRepository.findAllById(dto.getBookmarkIds())
            );
        }

        return colecao;
    }
}
