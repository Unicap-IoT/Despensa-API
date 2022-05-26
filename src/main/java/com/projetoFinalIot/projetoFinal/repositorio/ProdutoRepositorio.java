package com.projetoFinalIot.projetoFinal.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import com.projetoFinalIot.projetoFinal.entidades.Produto;

public interface ProdutoRepositorio extends JpaRepository<Produto, Integer> {

}
