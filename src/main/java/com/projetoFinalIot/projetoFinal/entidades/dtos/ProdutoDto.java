package com.projetoFinalIot.projetoFinal.entidades.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.projetoFinalIot.projetoFinal.entidades.Categoria;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;

public class ProdutoDto {
    @NotEmpty(message = "Informe o nome do produto")
    private String nome;

    @Future(message = "A data inválida para validade")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private Date dataValidade;
    @NotNull(message = "Informe a quantidade do produto")
    @Range(min = 0, message = "A quantidade não pode ser menor que zero")
    private Integer quantidade;
    @NotNull(message = "Informe a categoria do produto")
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
