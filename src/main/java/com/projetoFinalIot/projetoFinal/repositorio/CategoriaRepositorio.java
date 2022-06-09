package com.projetoFinalIot.projetoFinal.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import com.projetoFinalIot.projetoFinal.entidades.Categoria;

import java.util.List;
import java.util.Optional;

public interface CategoriaRepositorio extends JpaRepository<Categoria, Integer> {

    public Optional<Categoria> findByNome(String nome);

    public List<Categoria> findByNomeContains(String nome);
}
