package br.edu.infnet.elberthapi.model.service;

import java.util.List;

import org.springframework.stereotype.Service;

import br.edu.infnet.elberthapi.exceptions.ProdutoInvalidoException;
import br.edu.infnet.elberthapi.exceptions.ProdutoNaoEncontratoException;
import br.edu.infnet.elberthapi.model.domain.Produto;
import br.edu.infnet.elberthapi.model.domain.TipoProduto;
import br.edu.infnet.elberthapi.model.repository.ProdutoRepository;

@Service
public class ProdutoService implements CrudService<Produto, Integer> {
	
	private final ProdutoRepository produtoRepository;	

	public ProdutoService(ProdutoRepository produtoRepository) {
		this.produtoRepository = produtoRepository;
	}


	public List<Produto> obterPorTipo(TipoProduto tipoProduto){
		return produtoRepository.findByTipoProduto(tipoProduto);
	}
	
	public List<Produto> obterPorTipoEvalor(TipoProduto tipoProduto, Double valor){
		return produtoRepository.findByTipoProdutoAndValorGreaterThan(tipoProduto, valor);
	}

	public List<Produto> obterPorTipoEvalorOrdenado(TipoProduto tipoProduto, Double valor){
		return produtoRepository.findByTipoProdutoAndValorGreaterThanOrderByValorAsc(tipoProduto, valor);
	}

	private void validar(Produto produto) {
		
		
		
		if(produto == null) {
			throw new IllegalArgumentException("O produto não pode estar nulo!!");
		}		
		if(produto.getDescricao() == null || produto.getDescricao().trim().isEmpty()) {
			throw new ProdutoInvalidoException("A descrição do produto é uma informação obrigatória!!!");
		}			
		if(produto.getValor() == null || produto.getValor() <= 0) {
			throw new ProdutoInvalidoException("O valor do produto deve ser positivo e é obrigatório!!!");
		}			
		if(produto.getTipoProduto() == null) {
			throw new ProdutoInvalidoException("O tipo do produto é uma informação obrigatória!!!");
		}
	}
	
	@Override
	public Produto incluir(Produto produto) {
		
		validar(produto);

		if(produto.getId() != null && produto.getId() > 0) {
			throw new IllegalArgumentException("Um novo produto não pode ter um ID preenchido durante a inclusão!!");
		}		

		return produtoRepository.save(produto);
	}

	@Override
	public List<Produto> obterLista() {
		
		return produtoRepository.findAll();
	}

	@Override
	public Produto alterar(Integer id, Produto produto) {
				
		validar(produto);

		obterPorId(id); 
		
		produto.setId(id);

		return produtoRepository.save(produto);
	}

	@Override
	public void excluir(Integer id) {

		Produto produto = obterPorId(id); 

		produtoRepository.delete(produto);
	}

	@Override
	public Produto obterPorId(Integer id) {
		if(id == null || id <= 0) {
			throw new IllegalArgumentException("O ID utilizado na busca do produto não pode ser nulo/zero/negativo.");
		}

		return produtoRepository.findById(id).orElseThrow(() -> new ProdutoNaoEncontratoException("O produto com o ID ["+id+"] não foi encontrado!"));
	}
}