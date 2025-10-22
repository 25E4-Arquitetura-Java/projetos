package br.edu.infnet.elberthapi.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.edu.infnet.elberthapi.model.domain.Comprador; // Importar a classe Comprador
import br.edu.infnet.elberthapi.model.service.CompradorService;

@RestController
@RequestMapping("/api/compradores")
public class CompradorController {

    private final CompradorService compradorService;
    
    public CompradorController(CompradorService compradorService) {
        this.compradorService = compradorService;
    }

    @PostMapping
    public Comprador incluir(@RequestBody Comprador comprador) {
        
        Comprador compradorIncluido = compradorService.incluir(comprador);

        return compradorIncluido;
    }

    @PutMapping("/{id}")
    public Comprador alterar(@PathVariable Integer id, @RequestBody Comprador comprador) {

        Comprador compradorAlterado = compradorService.alterar(id, comprador);
        
        return compradorAlterado;
    }

    @DeleteMapping("/{id}")
    public void excluir(@PathVariable Integer id) {
        compradorService.excluir(id);
    }
    
    @GetMapping
    public List<Comprador> obterLista() {
        
        return compradorService.obterLista();
    }
    
    @GetMapping("/{id}")
    public Comprador obterPorId(@PathVariable Integer id) {
        
        Comprador compradorObtido = compradorService.obterPorId(id);
        
        return compradorObtido;
    }
}