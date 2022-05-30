package com.projetoFinalIot.projetoFinal.services;

import com.projetoFinalIot.projetoFinal.entidades.Categoria;
import com.projetoFinalIot.projetoFinal.repositorio.CategoriaRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepositorio categoriaRepositorio;
    public Categoria save(Categoria categoria) {
        return categoriaRepositorio.save(categoria);
    }

    public List<Categoria> findAll() {
        return categoriaRepositorio.findAll();
    }
}
