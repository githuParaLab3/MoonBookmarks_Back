package com.MoonBookmarks.MoonBookmarks_Back.mappers;

import com.MoonBookmarks.MoonBookmarks_Back.dto.BookmarkDTO;
import com.MoonBookmarks.MoonBookmarks_Back.entities.Bookmark;

import com.MoonBookmarks.MoonBookmarks_Back.repositories.ColecaoRepository;
import java.util.stream.Collectors;

public class BookmarkMapper {

  
    public static BookmarkDTO toDTO(Bookmark bookmark) {
        BookmarkDTO dto = new BookmarkDTO();
        dto.setId(bookmark.getId());
        dto.setObra(bookmark.getObra());
        dto.setUsuario(bookmark.getUsuario());
        dto.setStatus(bookmark.getStatus());
        dto.setProgresso(bookmark.getProgresso());

      
        dto.setColecaoIds(bookmark.getColecoes().stream()
                .map(colecao -> colecao.getId())  
                .collect(Collectors.toList()));

        dto.setComentario(bookmark.getComentario());
        return dto;
    }

 
    public static Bookmark fromDTO(BookmarkDTO dto, ColecaoRepository colecaoRepository) {
        Bookmark bookmark = new Bookmark();
        bookmark.setId(dto.getId());
        bookmark.setObra(dto.getObra());
        bookmark.setUsuario(dto.getUsuario());
        bookmark.setStatus(dto.getStatus());
        bookmark.setProgresso(dto.getProgresso());
        bookmark.setComentario(dto.getComentario());

        
        if (dto.getColecaoIds() != null) {
            bookmark.setColecoes(colecaoRepository.findAllById(dto.getColecaoIds()));  
        }

        return bookmark;
    }
}
