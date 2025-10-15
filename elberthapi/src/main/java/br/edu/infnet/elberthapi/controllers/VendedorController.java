package br.edu.infnet.elberthapi.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.edu.infnet.elberthapi.model.domain.Vendedor;
import br.edu.infnet.elberthapi.model.domain.service.VendedorService;

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

	@GetMapping
	public List<Vendedor> obterLista() {
		
		return vendedorService.obterLista();
	}	
}