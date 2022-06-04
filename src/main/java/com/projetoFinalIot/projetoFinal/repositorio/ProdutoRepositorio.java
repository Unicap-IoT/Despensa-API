package com.projetoFinalIot.projetoFinal.repositorio;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.projetoFinalIot.projetoFinal.entidades.Produto;

public interface ProdutoRepositorio extends JpaRepository<Produto, Integer> {

    List<Produto> findByNomeContains(String nome);
    public Optional<Produto> findByNome(String nome);
}
