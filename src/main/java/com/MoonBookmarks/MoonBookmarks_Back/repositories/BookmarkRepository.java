package com.MoonBookmarks.MoonBookmarks_Back.repositories;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.MoonBookmarks.MoonBookmarks_Back.entities.Bookmark;

@Repository
public interface BookmarkRepository extends JpaRepository<Bookmark, String> {
    List<Bookmark> findByUsuarioId(String usuarioId);
 }
