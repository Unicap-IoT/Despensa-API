package com.projetoFinalIot.projetoFinal.entidades.dtos;

import javax.validation.constraints.NotEmpty;

public class CategoriaDto {
    @NotEmpty(message = "Informe o nome da categoria")
    String nome;

    public CategoriaDto(){}

    public CategoriaDto(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
