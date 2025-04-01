package com.MoonBookmarks.MoonBookmarks_Back.services;

import com.MoonBookmarks.MoonBookmarks_Back.entities.Obra;
import com.MoonBookmarks.MoonBookmarks_Back.repositories.ObraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ObraService {
    @Autowired
    private ObraRepository obraRepository;

    public List<Obra> listarTodas() {
        return obraRepository.findAll();
    }

    public Optional<Obra> buscarPorId(String id) {
        return obraRepository.findById(id);
    }

    public Obra salvar(Obra obra) {
        return obraRepository.save(obra);
    }

    public void deletar(String id) {
        obraRepository.deleteById(id);
    }
}
