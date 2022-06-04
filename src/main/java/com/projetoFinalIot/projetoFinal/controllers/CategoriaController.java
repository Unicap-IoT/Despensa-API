package com.projetoFinalIot.projetoFinal.controllers;

import com.projetoFinalIot.projetoFinal.entidades.Categoria;
import com.projetoFinalIot.projetoFinal.services.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/categoria")
public class CategoriaController {

    @Autowired
    public CategoriaService categoriaService;

    @PostMapping
    public ResponseEntity<Categoria> save(@RequestBody Categoria categoria){
        return ResponseEntity.status(HttpStatus.CREATED).body(categoriaService.save(categoria));
    }

    @GetMapping
    public ResponseEntity<List<Categoria>> findAll(){
        return ResponseEntity.status(HttpStatus.OK).body(categoriaService.findAll());
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Categoria> findAll(@PathVariable Integer id){
        return ResponseEntity.status(HttpStatus.OK).body(categoriaService.findById(id));
    }
    
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id){
    	categoriaService.delete(id);
    	return ResponseEntity.noContent().build();
    }
    
    @PutMapping(value = "/{id}")
    public ResponseEntity<Void> update(@RequestBody Categoria cat, @PathVariable Integer id){
    	cat.setId(id);
    	categoriaService.update(cat);
    	return ResponseEntity.noContent().build();
    }
}
