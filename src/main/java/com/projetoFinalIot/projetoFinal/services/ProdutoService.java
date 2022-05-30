package com.projetoFinalIot.projetoFinal.services;

import com.projetoFinalIot.projetoFinal.entidades.Produto;
import com.projetoFinalIot.projetoFinal.repositorio.ProdutoRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepositorio produtoRepositorio;
    public Produto save(Produto produto) {
        return produtoRepositorio.save(produto);
    }

    public List<Produto> findAll() {
        return produtoRepositorio.findAll();
    }
}
