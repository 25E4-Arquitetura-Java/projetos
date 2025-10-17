package br.edu.infnet.elberthapi.model.domain.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.stereotype.Service;

import br.edu.infnet.elberthapi.exceptions.VendedorInvalidoException;
import br.edu.infnet.elberthapi.exceptions.VendedorNaoEncontratoException;
import br.edu.infnet.elberthapi.interfaces.CrudService;
import br.edu.infnet.elberthapi.model.domain.Vendedor;

@Service
public class VendedorService implements CrudService<Vendedor, Integer> {

	private final Map<Integer, Vendedor> mapa = new ConcurrentHashMap<Integer, Vendedor>();
	private final AtomicInteger nextId = new AtomicInteger(1);
	

	private void validar(Vendedor vendedor) {
		//rn1 - o vendedor não pode estar nulo (vendedor == null)
		if(vendedor == null) {
			throw new IllegalArgumentException("O vendedor não pode estar nulo!!");
		}
		
		//rn2 - o nome do vendedor é uma informação obrigatória (vendedor.nome == null OU vendedor.nome.vazio)
		if(vendedor.getNome() == null || vendedor.getNome().trim().isEmpty()) {
			throw new VendedorInvalidoException("O nome do vendedor é uma informação obrigatória!!!");
		}
		
		//rn3 - o novo vendedor não pode ter um ID preenchido durante a inclusão (vendedor.id == null E vendedor.id > 0)
		if(vendedor.getId() != null && vendedor.getId() > 0) {
			throw new IllegalArgumentException("O novo vendedor não pode ter um ID preenchido durante a inclusão!!");
		}
	}
	
	@Override
	public Vendedor incluir(Vendedor vendedor) {
		
		validar(vendedor);
		
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
				
		validar(vendedor);
		
		mapa.put(vendedor.getId(), vendedor);

		return vendedor;
	}

	@Override
	public void excluir(Integer id) {
		Vendedor vendedor = obterPorId(id);

		mapa.remove(vendedor.getId());
	}

	@Override
	public Vendedor obterPorId(Integer id) {
		//rn4 - o ID utilizado na busca do vendedor não pode ser nulo/zero (o parâmetro ID = null OU ID = 0)
		if(id == null || id <= 0) {
			throw new IllegalArgumentException("o ID utilizado na busca do vendedor não pode ser nulo/zero/negativo");
		}
		
		Vendedor vendedor = mapa.get(id);
		
		//rn5 - o vendedor com o ID x não foi encontrado (vendedor == null)
		if(vendedor == null) {
			throw new VendedorNaoEncontratoException("O vendedor com o ID ["+id+"] não foi encontrado!");
		}
		
		return vendedor;
	}

	public Vendedor inativar(Integer id) {
		
		Vendedor vendedor = obterPorId(id);
		
		if(!vendedor.isAtivo()) {
			System.err.println("O vendedor "+vendedor.getNome()+" não está inativo!");
			return vendedor;
		}
		
		vendedor.setAtivo(false);

		return vendedor;
	}
}
