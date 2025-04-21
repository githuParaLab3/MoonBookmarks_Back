package com.MoonBookmarks.MoonBookmarks_Back.dto;

import com.MoonBookmarks.MoonBookmarks_Back.entities.Usuario;
import java.util.List;

public class ColecaoDTO {
    private String id;
    private String titulo;
    private String descricao;
    private String foto;
    private Usuario usuario; 
    private List<String> bookmarkIds;  
    
 
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
    public List<String> getBookmarkIds() {
        return bookmarkIds;
    }
    public void setBookmarkIds(List<String> bookmarkIds) {
        this.bookmarkIds = bookmarkIds;
    }
}
