package com.projetoFinalIot.projetoFinal.services;

import com.projetoFinalIot.projetoFinal.entidades.Categoria;
import com.projetoFinalIot.projetoFinal.entidades.Produto;
import com.projetoFinalIot.projetoFinal.repositorio.CategoriaRepositorio;
import com.projetoFinalIot.projetoFinal.repositorio.ProdutoRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;

@Service
public class DevProfileService {

    @Autowired
    private CategoriaRepositorio categoriaRepositorio;

    @Autowired
    private ProdutoRepositorio produtoRepositorio;
    public void dbInstance(){
        try {
            Categoria categoria1 = new Categoria();
            categoria1.setNome("CARNES");
            categoria1 = categoriaRepositorio.save(categoria1);

            Categoria categoria2 = new Categoria();
            categoria2.setNome("BEBIDAS");
            categoria2 = categoriaRepositorio.save(categoria2);

            Produto produto1 = new Produto();
            produto1.setNome("CARNE B");
            produto1.setQuantidade(10);
            produto1.setDataValidade(new SimpleDateFormat("dd/MM/yyyy").parse("12/12/2023"));
            produto1.setCategoria(categoria1);
            produto1 = produtoRepositorio.save(produto1);

            Produto produto2 = new Produto();
            produto2.setNome("LEITE");
            produto2.setQuantidade(15);
            produto2.setDataValidade(new SimpleDateFormat("dd/MM/yyyy").parse("13/10/2024"));
            produto2.setCategoria(categoria2);
            produto2 = produtoRepositorio.save(produto2);

            Produto produto3 = new Produto();
            produto3.setNome("LEITE DESNATADO");
            produto3.setQuantidade(15);
            produto3.setDataValidade(new SimpleDateFormat("dd/MM/yyyy").parse("13/10/2024"));
            produto3.setCategoria(categoria2);
            produto3 = produtoRepositorio.save(produto3);

            Produto produto4 = new Produto();
            produto4.setNome("CARNE A");
            produto4.setQuantidade(15);
            produto4.setDataValidade(new SimpleDateFormat("dd/MM/yyyy").parse("13/10/2024"));
            produto4.setCategoria(categoria1);
            produto4 = produtoRepositorio.save(produto4);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }
}
