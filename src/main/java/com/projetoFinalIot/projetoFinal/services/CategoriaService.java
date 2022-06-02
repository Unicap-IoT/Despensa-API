package com.projetoFinalIot.projetoFinal.services;

import com.projetoFinalIot.projetoFinal.entidades.Categoria;
import com.projetoFinalIot.projetoFinal.repositorio.CategoriaRepositorio;
import com.projetoFinalIot.projetoFinal.services.exception.DataIntegretyException;
import com.projetoFinalIot.projetoFinal.services.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepositorio categoriaRepositorio;
    public Categoria save(Categoria categoria) {
        categoria.setNome(categoria.getNome().toUpperCase());
        if(categoriaRepositorio.findByNome(categoria.getNome()).isPresent()){
            throw new DataIntegretyException("Categoria "+categoria.getNome()+" já cadastrada");
        }
        return categoriaRepositorio.save(categoria);
    }

    public List<Categoria> findAll() {
        return categoriaRepositorio.findAll();
    }

    public Categoria findById(Integer id) {
        return categoriaRepositorio.findById(id).orElseThrow(
                ()-> new ObjectNotFoundException("Categoria não encontrada, id: "+id));
    }
}
