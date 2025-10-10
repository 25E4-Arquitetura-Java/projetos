package br.edu.infnet.elberthapi.model.domain.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.stereotype.Service;

import br.edu.infnet.elberthapi.interfaces.CrudService;
import br.edu.infnet.elberthapi.model.domain.Vendedor;

@Service
public class VendedorService implements CrudService<Vendedor, Integer> {

	private final Map<Integer, Vendedor> mapa = new ConcurrentHashMap<Integer, Vendedor>();
	private final AtomicInteger nextId = new AtomicInteger(1);
	

	@Override
	public Vendedor incluir(Vendedor vendedor) {

		vendedor.setId(nextId.getAndIncrement());

		mapa.put(vendedor.getId(), vendedor);
		
		return vendedor;
	}

	@Override
	public List<Vendedor> obterLista() {
		
		return new ArrayList<Vendedor>(mapa.values());
	}

	@Override
	public Vendedor alterar(Integer id, Vendedor vendedor) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void excluir(Integer id) {
		mapa.remove(id);
	}

}
