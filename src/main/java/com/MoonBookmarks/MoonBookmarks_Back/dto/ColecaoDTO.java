package com.MoonBookmarks.MoonBookmarks_Back.dto;
import com.MoonBookmarks.MoonBookmarks_Back.entities.Usuario;
import java.util.ArrayList;
import java.util.List;

public class ColecaoDTO {
    private String id;
    private String titulo;
    private String descricao;
    private String foto;
    private Usuario usuario; // Incluindo o usuário diretamente
    private List<BookmarkDTO> bookmarks = new ArrayList<>();  // Inicializando com uma lista vazia
 
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getTitulo() {
        return titulo;
    }
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
    public String getDescricao() {
        return descricao;
    }
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    public String getFoto() {
        return foto;
    }
    public void setFoto(String foto) {
        this.foto = foto;
    }
    public Usuario getUsuario() {
        return usuario;
    }
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    public List<BookmarkDTO> getBookmarks() {
        return bookmarks;
    }
    public void setBookmarks(List<BookmarkDTO> bookmarks) {
        this.bookmarks = bookmarks != null ? bookmarks : new ArrayList<>();  // Garantindo que não seja null
    }
}
