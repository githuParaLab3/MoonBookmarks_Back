package com.MoonBookmarks.MoonBookmarks_Back.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonBackReference;


@Entity
public class Bookmark {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @ManyToOne
    private Obra obra;

    @ManyToOne
    private Usuario usuario;

    @Enumerated(EnumType.STRING)
    private Status status;

    private float progresso;

    @ManyToMany(mappedBy = "bookmarks")
    @JsonBackReference 
    private List<Colecao> colecoes;

    private String comentario;

    public Bookmark() {}

    public Bookmark(
        String id,
        Obra obra,
        Usuario usuario,
        Status status,
        float progresso,
        List<Colecao> colecoes,
        String comentario
    ) {
        this.id = id;
        this.obra = obra;
        this.usuario = usuario;
        this.status = status;
        this.progresso = progresso;
        this.colecoes = colecoes;
        this.comentario = comentario;
    }

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

    public List<Colecao> getColecoes() {
        return colecoes;
    }

    public void setColecoes(List<Colecao> colecoes) {
        this.colecoes = colecoes;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }
}
