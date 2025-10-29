package br.edu.infnet.elberthapi.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.edu.infnet.elberthapi.model.domain.Produto;
import br.edu.infnet.elberthapi.model.service.ProdutoService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/produtos")
public class ProdutoController {

	private final ProdutoService produtoService;
	
	public ProdutoController(ProdutoService produtoService) {
		this.produtoService = produtoService;
	}

	@PostMapping
	public ResponseEntity<Produto> incluir(@Valid @RequestBody Produto produto) {
		Produto produtoIncluido = produtoService.incluir(produto);
		return ResponseEntity.status(HttpStatus.CREATED).body(produtoIncluido);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Produto> alterar(@PathVariable Integer id, @Valid @RequestBody Produto produto) {
		Produto produtoAlterado = produtoService.alterar(id, produto);
		return ResponseEntity.ok(produtoAlterado);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> excluir(@PathVariable Integer id) {
		produtoService.excluir(id);
		return ResponseEntity.noContent().build(); // Retorna 204 No Content para exclusão bem-sucedida
	}
	
	@GetMapping
	public ResponseEntity<List<Produto>> obterLista() {
		List<Produto> lista = produtoService.obterLista();
		if(lista.isEmpty()) {
			return ResponseEntity.noContent().build(); // Retorna 204 se a lista estiver vazia
		}
		return ResponseEntity.ok(lista);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Produto> obterPorId(@PathVariable Integer id) {
		Produto produtoObtido = produtoService.obterPorId(id);
		return ResponseEntity.ok(produtoObtido);
	}
}