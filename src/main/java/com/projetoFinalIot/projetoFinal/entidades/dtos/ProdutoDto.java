package com.projetoFinalIot.projetoFinal.entidades.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.projetoFinalIot.projetoFinal.entidades.Categoria;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;

public class ProdutoDto {
    @NotEmpty
    private String nome;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private Date dataValidade;
    @NotNull
    private Integer quantidade;
    @NotNull
    private Categoria categoria;
}
