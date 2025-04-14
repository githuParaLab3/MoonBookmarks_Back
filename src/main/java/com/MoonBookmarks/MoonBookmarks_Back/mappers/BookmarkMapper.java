package com.MoonBookmarks.MoonBookmarks_Back.mappers;


import com.MoonBookmarks.MoonBookmarks_Back.dto.BookmarkDTO;
import com.MoonBookmarks.MoonBookmarks_Back.entities.Bookmark;
import java.util.stream.Collectors;

public class BookmarkMapper {

    public static BookmarkDTO toDTO(Bookmark bookmark) {
        BookmarkDTO dto = new BookmarkDTO();
        dto.setId(bookmark.getId());
        dto.setObra(bookmark.getObra());
        dto.setUsuario(bookmark.getUsuario());
        dto.setStatus(bookmark.getStatus());
        dto.setProgresso(bookmark.getProgresso());
        
        // Agora você carrega as coleções completas no DTO
        dto.setColecoes(bookmark.getColecoes().stream()
                .map(ColecaoMapper::toDTO)  // Aqui você usa o mapper de Colecao para mapear para DTO completo
                .collect(Collectors.toList()));
        
        dto.setComentario(bookmark.getComentario());
        return dto;
    }

    public static Bookmark fromDTO(BookmarkDTO dto) {
        Bookmark bookmark = new Bookmark();
        bookmark.setId(dto.getId());
        bookmark.setObra(dto.getObra());
        bookmark.setUsuario(dto.getUsuario());
        bookmark.setStatus(dto.getStatus());
        bookmark.setProgresso(dto.getProgresso());
        bookmark.setComentario(dto.getComentario());
        
        // Lógica para associar as coleções
        // Se você tiver IDs das coleções no DTO, precisa buscar as coleções no banco e associá-las ao Bookmark
        bookmark.setColecoes(null); // ou implemente a lógica de associar as coleções aqui
        return bookmark;
    }

}
