package com.projetoFinalIot.projetoFinal.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projetoFinalIot.projetoFinal.entidades.Produto;
import com.projetoFinalIot.projetoFinal.entidades.ProdutoAux;
import com.projetoFinalIot.projetoFinal.entidades.constantes.ProdutoConst;
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
				
		Produto prod = findById(produto.getId());
		if(!prod.getNome().equalsIgnoreCase(produto.getNome())) {
			validarNome(produto);
		}
		prod.setNome(produto.getNome());
		prod.setCategoria(produto.getCategoria());
		prod.setDataValidade(produto.getDataValidade());
		prod.setQuantidade(produto.getQuantidade());
		validarData(prod);
		validarQuantidade(prod);
		produtoRepositorio.save(prod);	
		
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
	
	public List<String> verificarValidade() {
		List<Produto> lista = findAll();
		List<String> listaStr = new ArrayList<>();
		for(Produto prod : lista) {
			if(alarmeValidade(prod.getDataValidade())) {
				listaStr.add("O produto " + prod.getNome() + " está perto de vencer!");
				System.out.println("Opa to aqui!   verificarValidade    fdgdff");
			}
		}
		
		return listaStr;
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
	
	private void validarData(Produto prod) {
		long dataValidade = prod.getDataValidade().getTime();
		long dataAtual = dataParalMills();
		if(dataValidade < dataAtual) {
			throw new UnauthorizedException("Data de validade expirada!");
		}
	}

	private Long dataParalMills() {
		return new Date().getTime();
	}
	
	private long descontoDataValidadeMills(Date data) {
		return (data.getTime() - (ProdutoConst.DIASVALIDADE * 3600 * 1000 * 24));
	}
	
	private boolean alarmeValidade(Date dataValidade) {
		long dataAlarme = descontoDataValidadeMills(dataValidade);
		long dataAtual = dataParalMills();
		return (dataAtual >= dataAlarme) ? true : false;
		
	}
}
