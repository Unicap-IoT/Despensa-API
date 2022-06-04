package com.projetoFinalIot.projetoFinal.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projetoFinalIot.projetoFinal.entidades.Produto;
import com.projetoFinalIot.projetoFinal.repositorio.ProdutoRepositorio;
import com.projetoFinalIot.projetoFinal.services.exception.DataIntegretyException;
import com.projetoFinalIot.projetoFinal.services.exception.ObjectNotFoundException;


//# indica acréscimo na quantidade
//* indica decréscimo na quantidade
@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepositorio produtoRepositorio;
    public Produto save(Produto produto) {
    	produto.setNome(produto.getNome().toUpperCase());
        validarNome(produto);
        return produtoRepositorio.save(produto);
    }

    public List<Produto> findAll() {
        return produtoRepositorio.findAll();
    }

    public List<Produto> findByNomeStartsWith(String nome) {
        nome = nome.toUpperCase();
        if(produtoRepositorio.findByNomeContains(nome).isEmpty()) {
        	throw new ObjectNotFoundException("Não há itens correspondentes a este nome!");
        }
        return produtoRepositorio.findByNomeContains(nome); 
    }
    
    public Produto findById(Integer id) {
        return produtoRepositorio.findById(id).orElseThrow(
                ()-> new ObjectNotFoundException("Produto não encontrada, id: "+id));
    }
    
    public void delete(Integer id) {
    	findById(id);
    	produtoRepositorio.deleteById(id);
    }

	public void update(Produto produto, String indicador, Integer quantidade) {
		
		validarQuantidade(quantidade, produto);
		Produto prod = findById(produto.getId());
		prod.setNome(produto.getNome());
		validarNome(prod);
		prod.setCategoria(produto.getCategoria());
		prod.setDataValidade(produto.getDataValidade());
		
		adicionarOuSbubtrairQuantidade(prod, produto, indicador, quantidade);
		/*if(prod.getQuantidade() == produto.getQuantidade()) {
			prod.setQuantidade(produto.getQuantidade());
		}else if(indicador == '#') {
			prod.setQuantidade(produto.getQuantidade() + prod.getQuantidade());
		}else if(indicador == '*'){
			prod.setQuantidade(prod.getQuantidade() - produto.getQuantidade());
			validarQuantidade(prod);
		}*/
		produtoRepositorio.save(prod);	
		
	}
	
	private void validarNome(Produto produto) {
		if(produtoRepositorio.findByNome(produto.getNome()).isPresent()){
            throw new DataIntegretyException("Produto "+produto.getNome()+" já cadastrada");
        }
	}
	
	private void validarQuantidade(Integer quantidade, Produto produto) {
		
		if(quantidade < 0){
            throw new DataIntegretyException("Quantidade do produto "+produto.getNome()+
            		" deve ser um número natural!");
        }
	}
	
	private void adicionarOuSbubtrairQuantidade(Produto prod, Produto produto, String indicador, int quantidade) {
				
		if(indicador.equals("#")) {
			prod.setQuantidade(quantidade + prod.getQuantidade());
		}else if(indicador.equals("*")){
			prod.setQuantidade(prod.getQuantidade() - quantidade);
			validarQuantidade(prod.getQuantidade(), prod);
		}else {
			prod.setQuantidade(produto.getQuantidade());
		}
	}
}
