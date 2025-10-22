package br.edu.infnet.elberthapi.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.edu.infnet.elberthapi.model.domain.Vendedor;
import br.edu.infnet.elberthapi.model.service.VendedorService;

@RestController
@RequestMapping("/api/vendedores")
public class VendedorController {

	private final VendedorService vendedorService;
	
	public VendedorController(VendedorService vendedorService) {
		this.vendedorService = vendedorService;
	}

	@PostMapping
	public Vendedor incluir(@RequestBody Vendedor vendedor) {
		
		Vendedor vendedorIncluido = vendedorService.incluir(vendedor);

		return vendedorIncluido;
	}

	@PutMapping("/{id}")
	public Vendedor alterar(@PathVariable Integer id, @RequestBody Vendedor vendedor) {

		Vendedor vendedorAlterado = vendedorService.alterar(id, vendedor);
		
		return vendedorAlterado;
	}

	@DeleteMapping("/{id}")
	public void excluir(@PathVariable Integer id) {
		vendedorService.excluir(id);
	}
	
	@GetMapping
	public List<Vendedor> obterLista() {
		
		return vendedorService.obterLista();
	}
	
	@GetMapping("/{id}")
	public Vendedor obterPorId(@PathVariable Integer id) {
		
		Vendedor vendedorObtido = vendedorService.obterPorId(id);
		
		return vendedorObtido;
	}
	
	@PatchMapping("/{id}/inativar")
	public Vendedor inativar(@PathVariable Integer id) {
		
		Vendedor vendedorInativado = vendedorService.inativar(id);
		
		return vendedorInativado;
	}
}