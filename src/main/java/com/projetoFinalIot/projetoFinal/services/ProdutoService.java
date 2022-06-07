package com.projetoFinalIot.projetoFinal.services;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projetoFinalIot.projetoFinal.entidades.Produto;
import com.projetoFinalIot.projetoFinal.entidades.ProdutoAux;
import com.projetoFinalIot.projetoFinal.repositorio.ProdutoRepositorio;
import com.projetoFinalIot.projetoFinal.services.exception.DataIntegretyException;
import com.projetoFinalIot.projetoFinal.services.exception.ObjectNotFoundException;
import com.projetoFinalIot.projetoFinal.services.exception.UnauthorizedException;


//# indica acréscimo na quantidade - encoded %23
//* indica decréscimo na quantidade 
@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepositorio produtoRepositorio;
	@Autowired
	private CategoriaService categoriaService;
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

	public Produto update(Produto produto) {
		findById(produto.getId());
		if(!produto.getNome().equalsIgnoreCase(produto.getNome())) {
			validarNome(produto);
		}
		produto.setCategoria(categoriaService.findById(produto.getCategoria().getId()));
		validarData(produto);
		validarQuantidade(produto);
		return produtoRepositorio.save(produto);
	}
	
	public void updateQuantidade(ProdutoAux prodAux) {
		Produto prod = findById(prodAux.getId());
		if(prodAux.getIndicador().equalsIgnoreCase("#")) {
			prod.setQuantidade(prod.getQuantidade() + prodAux.getQtd());
		}else if(prodAux.getIndicador().equalsIgnoreCase("*")) {
			prod.setQuantidade(prod.getQuantidade() - prodAux.getQtd());
			validarQuantidade(prod);
		}else {
			throw new DataIntegretyException("O indicador da operação não está presente!");
		}
		produtoRepositorio.save(prod);
	} 
	
	private void validarNome(Produto produto) {
		if(produtoRepositorio.findByNome(produto.getNome()).isPresent()){
            throw new DataIntegretyException("Produto "+produto.getNome()+" já cadastrada");
        }
	}
	
	private void validarQuantidade(Produto produto) {
		if(produto.getQuantidade() < 0){
            throw new DataIntegretyException(
					"Quantidade do produto "+produto.getNome()+ " deve ser um número natural!");
        }
	}
	
	private void validarData(Produto prod) {
		long dataValidade = prod.getDataValidade().getTime();
		long dataAtual = new Date().getTime();
		if(dataValidade < dataAtual) {
			throw new UnauthorizedException("Data de validade expirada!");
		}
	}

	

}
