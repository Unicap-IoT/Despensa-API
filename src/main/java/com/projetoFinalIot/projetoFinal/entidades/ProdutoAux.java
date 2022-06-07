package com.projetoFinalIot.projetoFinal.entidades;

import java.io.Serializable;

public class ProdutoAux implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer id;
	private Integer qtd;
	private String indicador;
	
	public ProdutoAux() {}

	public ProdutoAux(Integer id, Integer qtd, String indicador) {
		super();
		this.id = id;
		this.qtd = qtd;
		this.indicador = indicador;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getQtd() {
		return qtd;
	}

	public void setQtd(Integer qtd) {
		this.qtd = qtd;
	}

	public String getIndicador() {
		return indicador;
	}

	public void setIndicador(String indicador) {
		this.indicador = indicador;
	}

	@Override
	public String toString() {
		return "ProdutoAux [id=" + id + ", qtd=" + qtd + ", indicador=" + indicador + "]";
	}
	
	
}
