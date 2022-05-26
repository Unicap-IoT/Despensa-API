package com.projetoFinalIot.projetoFinal.entidades;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="tb_produto")
public class Produto implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="produto_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer produto_id;
	
	@Column(name="nome")
	private String nome;
	
	@Column(name="dataValidade")
	private Date dataValidade;
	
	@ManyToOne
	@JoinColumn(name = "categoria_id")
	private Categoria categoria;
	
	@Column(name="quantidade")
	private Integer quantidade;
	
	public Produto() {}

	public Produto(Integer id, String nome, Date dataVaidade, Categoria categoria, Integer quantidade) {
		super();
		this.produto_id = id;
		this.nome = nome;
		this.dataValidade = dataVaidade;
		this.categoria = categoria;
		this.quantidade = quantidade;
	}

	public Integer getId() {
		return produto_id;
	}

	public void setId(Integer id) {
		this.produto_id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Date getDataVaidade() {
		return dataValidade;
	}

	public void setDataVaidade(Date dataVaidade) {
		this.dataValidade = dataVaidade;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((produto_id == null) ? 0 : produto_id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Produto other = (Produto) obj;
		if (produto_id == null) {
			if (other.produto_id != null)
				return false;
		} else if (!produto_id.equals(other.produto_id))
			return false;
		return true;
	}
	
	
}
