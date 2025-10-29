package br.edu.infnet.elberthapi.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.edu.infnet.elberthapi.model.domain.Produto;
import br.edu.infnet.elberthapi.model.domain.TipoProduto;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Integer> {

	List<Produto> findByTipoProduto(TipoProduto tipoProduto);
	List<Produto> findByTipoProdutoAndValorGreaterThan(TipoProduto tipoProduto, Double valor);
	List<Produto> findByTipoProdutoAndValorGreaterThanOrderByValorAsc(TipoProduto tipoProduto, Double valor);
}
