package com.MoonBookmarks.MoonBookmarks_Back.mappers;

import com.MoonBookmarks.MoonBookmarks_Back.dto.ColecaoDTO;
import com.MoonBookmarks.MoonBookmarks_Back.entities.Colecao;

import java.util.stream.Collectors;

public class ColecaoMapper {

    // Mapeia a entidade Colecao para o DTO ColecaoDTO
    public static ColecaoDTO toDTO(Colecao colecao) {
        ColecaoDTO dto = new ColecaoDTO();
        dto.setId(colecao.getId());
        dto.setTitulo(colecao.getTitulo());
        dto.setDescricao(colecao.getDescricao());
        dto.setFoto(colecao.getFoto());
        dto.setUsuario(colecao.getUsuario());
        
        // Mapeia os Bookmarks para DTOs completos
        dto.setBookmarks(colecao.getBookmarks().stream()
                .map(BookmarkMapper::toDTO) // Mapeando cada Bookmark para o seu DTO completo
                .collect(Collectors.toList()));
        
        return dto;
    }

    // Mapeia o DTO ColecaoDTO de volta para a entidade Colecao
    public static Colecao fromDTO(ColecaoDTO dto) {
        Colecao colecao = new Colecao();
        colecao.setId(dto.getId());
        colecao.setTitulo(dto.getTitulo());
        colecao.setDescricao(dto.getDescricao());
        colecao.setFoto(dto.getFoto());
        colecao.setUsuario(dto.getUsuario()); // Usuario já está no DTO

        // Para as coleções de Bookmarks, você pode fazer a lógica inversa, 
        // se necessário, para vincular os Bookmarks de volta à Coleção.
        // Aqui estamos apenas pegando os Bookmarks do DTO e mapeando de volta
        colecao.setBookmarks(dto.getBookmarks().stream()
                .map(BookmarkMapper::fromDTO) // Mapeando cada BookmarkDTO de volta para a entidade Bookmark
                .collect(Collectors.toList()));
        
        return colecao;
    }
}
