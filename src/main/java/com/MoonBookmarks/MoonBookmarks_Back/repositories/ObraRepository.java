package com.MoonBookmarks.MoonBookmarks_Back.repositories;


import com.MoonBookmarks.MoonBookmarks_Back.entities.Obra;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ObraRepository extends JpaRepository<Obra, String> { }
