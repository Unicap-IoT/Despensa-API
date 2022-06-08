package com.projetoFinalIot.projetoFinal.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.projetoFinalIot.projetoFinal.entidades.Categoria;
import com.projetoFinalIot.projetoFinal.entidades.Produto;
import com.projetoFinalIot.projetoFinal.services.CategoriaService;

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
    public ResponseEntity<Categoria> update(@RequestBody Categoria categoria, @PathVariable Integer id){
    	categoria.setId(id);
    	return ResponseEntity.status(HttpStatus.OK).body(categoriaService.update(categoria));
    }
    
    @GetMapping(value = "/{id}/produto")
    public ResponseEntity<List<Produto>> findProdutos(@PathVariable Integer id){
    	Categoria cat = categoriaService.findById(id);
        return ResponseEntity.status(HttpStatus.OK).body(cat.getProdutos());
    }
}
