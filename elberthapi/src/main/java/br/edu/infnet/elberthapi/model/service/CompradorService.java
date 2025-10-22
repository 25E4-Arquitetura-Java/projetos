package br.edu.infnet.elberthapi.model.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.stereotype.Service;

import br.edu.infnet.elberthapi.exceptions.CompradorInvalidoException;
import br.edu.infnet.elberthapi.exceptions.CompradorNaoEncontratoException;
import br.edu.infnet.elberthapi.model.domain.Comprador;

@Service
public class CompradorService implements CrudService<Comprador, Integer> {

    private final Map<Integer, Comprador> mapa = new ConcurrentHashMap<Integer, Comprador>();
    private final AtomicInteger nextId = new AtomicInteger(1);

    private void validar(Comprador comprador) {
        if(comprador == null) {
            throw new IllegalArgumentException("O comprador não pode estar nulo!!");
        }
        
        if(comprador.getNome() == null || comprador.getNome().trim().isEmpty()) {
            throw new CompradorInvalidoException("O nome do comprador é uma informação obrigatória!!!");
        }

        if(comprador.getId() != null && comprador.getId() > 0) {
            throw new IllegalArgumentException("O novo comprador não pode ter um ID preenchido durante a inclusão/alteração (use o método alterar com um ID válido)!!");
        }
    }
    
    @Override
    public Comprador incluir(Comprador comprador) {
		
		validar(comprador);
		
		comprador.setId(nextId.getAndIncrement());
		mapa.put(comprador.getId(), comprador);

		return comprador;
    }

    @Override
    public List<Comprador> obterLista() {
        
        return new ArrayList<Comprador>(mapa.values());
    }

    @Override
    public Comprador alterar(Integer id, Comprador comprador) {
				
		validar(comprador);
		
		mapa.put(comprador.getId(), comprador);
		
		return comprador;
    }

    @Override
    public void excluir(Integer id) {
        Comprador comprador = obterPorId(id);

        mapa.remove(comprador.getId());
    }

    @Override
    public Comprador obterPorId(Integer id) {
        if(id == null || id <= 0) {
            throw new IllegalArgumentException("O ID utilizado na busca do comprador não pode ser nulo/zero/negativo");
        }
        
        Comprador comprador = mapa.get(id);
        
        if(comprador == null) {
            throw new CompradorNaoEncontratoException("O comprador com o ID ["+id+"] não foi encontrado!");
        }
        
        return comprador;
    }
}