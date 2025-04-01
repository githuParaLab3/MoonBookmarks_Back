package com.MoonBookmarks.MoonBookmarks_Back.repositories;


import com.MoonBookmarks.MoonBookmarks_Back.entities.Bookmark;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookmarkRepository extends JpaRepository<Bookmark, String> { }
