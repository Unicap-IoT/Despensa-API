package com.projetoFinalIot.projetoFinal.services;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projetoFinalIot.projetoFinal.entidades.Produto;
import com.projetoFinalIot.projetoFinal.repositorio.ProdutoRepositorio;
import com.projetoFinalIot.projetoFinal.services.exception.DataIntegretyException;
import com.projetoFinalIot.projetoFinal.services.exception.ObjectNotFoundException;
import com.projetoFinalIot.projetoFinal.services.exception.UnauthorizedException;


//# indica acréscimo na quantidade
//* indica decréscimo na quantidade
@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepositorio produtoRepositorio;
    public Produto save(Produto produto) {
    	produto.setNome(produto.getNome().toUpperCase());
        validarNome(produto);
        validarQuantidade(produto);
        validarData(produto);
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

	public void update(Produto produto) {
		
		validarNome(produto);
		validarData(produto);
		validarQuantidade(produto);
		
		Produto prod = findById(produto.getId());
		prod.setNome(produto.getNome());
		prod.setCategoria(produto.getCategoria());
		prod.setDataValidade(produto.getDataValidade());
		prod.setQuantidade(produto.getQuantidade());
		produtoRepositorio.save(prod);	
		
	}
	
	private void validarNome(Produto produto) {
		
		if(produtoRepositorio.findByNome(produto.getNome()).isPresent()){
            throw new DataIntegretyException("Produto "+produto.getNome()+" já cadastrada");
        }
	}
	
	private void validarQuantidade(Produto produto) {
		
		if(produto.getQuantidade() < 0){
            throw new UnauthorizedException("Quantidade do produto "+produto.getNome()+
            		" deve ser um número natural!");
        }
	}
	
	/*private void adicionarOuSbubtrairQuantidade(Produto prod, Produto produto, String indicador, int quantidade) {
				
		if(indicador.equals("#")) {
			prod.setQuantidade(quantidade + prod.getQuantidade());
		}else if(indicador.equals("*")){
			prod.setQuantidade(prod.getQuantidade() - quantidade);
			validarQuantidade(prod);
		}else {
			prod.setQuantidade(produto.getQuantidade());
		}
	}*/
	
	private void validarData(Produto prod) {
		long dataValidade = prod.getDataValidade().getTime();
		long dataAtual = new Date().getTime();
		if(dataValidade < dataAtual) {
			throw new UnauthorizedException("Data de validade expirada!");
		}
	}
}
