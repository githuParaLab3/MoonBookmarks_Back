package com.MoonBookmarks.MoonBookmarks_Back.dto;

import com.MoonBookmarks.MoonBookmarks_Back.entities.Obra;
import com.MoonBookmarks.MoonBookmarks_Back.entities.Status;
import com.MoonBookmarks.MoonBookmarks_Back.entities.Usuario;
import java.util.List;

public class BookmarkDTO {

    private String id;
    private Obra obra;
    private Usuario usuario;
    private Status status;
    private float progresso;
    private List<String> colecaoIds; // Usando apenas IDs das coleções
    private String comentario;
    
    // Getters e setters
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public Obra getObra() {
        return obra;
    }
    public void setObra(Obra obra) {
        this.obra = obra;
    }
    public Usuario getUsuario() {
        return usuario;
    }
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    public Status getStatus() {
        return status;
    }
    public void setStatus(Status status) {
        this.status = status;
    }
    public float getProgresso() {
        return progresso;
    }
    public void setProgresso(float progresso) {
        this.progresso = progresso;
    }
    public List<String> getColecaoIds() {
        return colecaoIds;
    }
    public void setColecaoIds(List<String> colecaoIds) {
        this.colecaoIds = colecaoIds;
    }
    public String getComentario() {
        return comentario;
    }
    public void setComentario(String comentario) {
        this.comentario = comentario;
    }
}
