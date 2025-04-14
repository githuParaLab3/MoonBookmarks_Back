package com.MoonBookmarks.MoonBookmarks_Back.entities;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;

import java.util.List;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;

import jakarta.persistence.Basic;
import jakarta.persistence.FetchType;
import java.util.ArrayList;

@Entity
public class Colecao {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private String titulo;
    private String descricao;

    @Lob
    @Basic(fetch=FetchType.EAGER)
    @Column(columnDefinition = "TEXT")
    private String foto;

    @ManyToOne
    private Usuario usuario;

    @ManyToMany
    @JoinTable(
        name = "colecao_bookmark",
        joinColumns = @JoinColumn(name = "colecao_id"),
        inverseJoinColumns = @JoinColumn(name = "bookmark_id")
    )
   
    private List<Bookmark> bookmarks  = new ArrayList<>();

    public Colecao() {}

    public Colecao(
        String id,
        String titulo,
        String descricao,
        String foto,
        Usuario usuario,
        List<Bookmark> bookmarks
    ) {
        this.id = id;
        this.titulo = titulo;
        this.descricao = descricao;
        this.foto = foto;
        this.usuario = usuario;
        this.bookmarks = bookmarks;
    }

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

    public List<Bookmark> getBookmarks() {
        return bookmarks;
    }

    public void setBookmarks(List<Bookmark> bookmarks) {
        this.bookmarks = bookmarks;
    }
}
