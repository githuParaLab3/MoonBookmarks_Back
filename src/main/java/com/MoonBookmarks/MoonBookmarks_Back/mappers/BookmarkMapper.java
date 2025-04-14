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
        
        // Para o caso de criar a entidade, você precisaria fazer o contrário: recuperar as coleções pelo ID.
        // Ou, se você deseja manter as coleções diretamente na entidade, você teria que implementar essa lógica.
        
        bookmark.setColecoes(null); // ou implementa a lógica para associar as coleções a partir de dto
        bookmark.setComentario(dto.getComentario());
        return bookmark;
    }
}
