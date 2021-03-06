package com.projetoFinalIot.projetoFinal.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projetoFinalIot.projetoFinal.entidades.Categoria;
import com.projetoFinalIot.projetoFinal.repositorio.CategoriaRepositorio;
import com.projetoFinalIot.projetoFinal.services.exception.DataIntegretyException;
import com.projetoFinalIot.projetoFinal.services.exception.ObjectNotFoundException;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepositorio categoriaRepositorio;
    public Categoria save(Categoria categoria) {
        categoria.setNome(categoria.getNome().toUpperCase());
        validarNome(categoria);
        return categoriaRepositorio.save(categoria);
    }

    public List<Categoria> findAll() {
        return categoriaRepositorio.findAll();
    }

    public Categoria findById(Integer id) {
        return categoriaRepositorio.findById(id).orElseThrow(
                ()-> new ObjectNotFoundException("Categoria não encontrada, id: "+id));
    }
    
    public void delete(Integer id) {
    	findById(id);
    	categoriaRepositorio.deleteById(id);
    }
    
    public Categoria update(Categoria categoria) {
        categoria.setNome(categoria.getNome().toUpperCase());
        Categoria categoriaAux = findById(categoria.getId());
        if(!categoria.getNome().equalsIgnoreCase(categoriaAux.getNome())){
            validarNome(categoria);
        }
    	return categoriaRepositorio.save(categoria);
    }
    
    private void validarNome(Categoria cat) {
    	if(categoriaRepositorio.findByNome(cat.getNome()).isPresent()) {
    		throw new DataIntegretyException("Categoria "+cat.getNome()+" já cadastrada");
    	}
    }

    public List<Categoria> findByNomeContains(String nome) {
        return categoriaRepositorio.findByNomeContains(nome);
    }
}
