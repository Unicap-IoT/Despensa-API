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

    public ProdutoDto(){}

    public ProdutoDto(String nome, Date dataValidade, Integer quantidade, Categoria categoria) {
        this.nome = nome;
        this.dataValidade = dataValidade;
        this.quantidade = quantidade;
        this.categoria = categoria;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Date getDataValidade() {
        return dataValidade;
    }

    public void setDataValidade(Date dataValidade) {
        this.dataValidade = dataValidade;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }
}
