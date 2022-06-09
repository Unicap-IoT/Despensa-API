package com.projetoFinalIot.projetoFinal.controllers;

import java.util.List;

import com.projetoFinalIot.projetoFinal.entidades.dtos.ProdutoDto;
import org.springframework.beans.BeanUtils;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.projetoFinalIot.projetoFinal.entidades.Produto;
import com.projetoFinalIot.projetoFinal.entidades.ProdutoAux;
import com.projetoFinalIot.projetoFinal.services.ProdutoService;

import javax.validation.Valid;

@CrossOrigin("*")
@RestController
@RequestMapping("/produto")
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;

    @PostMapping
    public ResponseEntity<Produto> save(@Valid @RequestBody ProdutoDto produtoDto){
        Produto produto = new Produto();
        BeanUtils.copyProperties(produtoDto, produto);
        return ResponseEntity.status(HttpStatus.CREATED).body(produtoService.save(produto));
    }
    
    @GetMapping(value = "/{id}")
    public ResponseEntity<Produto> findById(@PathVariable Integer id){
        return ResponseEntity.status(HttpStatus.OK).body(produtoService.findById(id));
    }

    @GetMapping
    public ResponseEntity<List<Produto>> findAll(){
        return ResponseEntity.status(HttpStatus.OK).body(produtoService.findAll());
    }

    @GetMapping(value = "/nome/{nome}")
    public ResponseEntity<List<Produto>> findByNome(@PathVariable String nome){
        return ResponseEntity.status(HttpStatus.OK).body(produtoService.findByNomeStartsWith(nome));
    }
    
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id){
    	produtoService.delete(id);
    	return ResponseEntity.noContent().build();
    }
    
    @PutMapping(value = {"/{id}"})
    public ResponseEntity<Produto> update(@RequestBody Produto produto, @PathVariable Integer id){
    	produto.setId(id);
    	return ResponseEntity.status(HttpStatus.OK).body(produtoService.update(produto));
    }
    
    @PutMapping(value = {"/{id}/quantidade/"})
    public ResponseEntity<?> updateQuantidade(@PathVariable Integer id, @RequestParam Integer qtd,
    		@RequestParam String ind){
    	ProdutoAux prodAux = new ProdutoAux(id, qtd, ind);
    	Produto produto = produtoService.updateQuantidade(prodAux);
    	String resultado = produtoService.alarmeQuantidade(produto);
    	return (resultado.equalsIgnoreCase("ok")) ? ResponseEntity.status(HttpStatus.OK).body(produto):
    		ResponseEntity.status(HttpStatus.OK).body(resultado);
    }
    
    @GetMapping(value = "/validade")
    public ResponseEntity<List<String>> vencimentoValidade(){
    	return ResponseEntity.status(HttpStatus.OK).body(produtoService.verificarValidade());
    }
}
