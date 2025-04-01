package com.MoonBookmarks.MoonBookmarks_Back.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.MoonBookmarks.MoonBookmarks_Back.entities.Colecao;

@Repository
public interface ColecaoRepository extends JpaRepository<Colecao, String> { }