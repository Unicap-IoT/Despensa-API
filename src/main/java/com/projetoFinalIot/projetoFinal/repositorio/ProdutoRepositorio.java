package com.projetoFinalIot.projetoFinal.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import com.projetoFinalIot.projetoFinal.entidades.Produto;

import java.util.List;

public interface ProdutoRepositorio extends JpaRepository<Produto, Integer> {

    List<Produto> findByNomeContains(String nome);
}
