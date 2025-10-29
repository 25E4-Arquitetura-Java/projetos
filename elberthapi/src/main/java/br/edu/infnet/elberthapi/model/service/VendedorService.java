package br.edu.infnet.elberthapi.model.service;

import java.util.List;

import org.springframework.stereotype.Service;

import br.edu.infnet.elberthapi.exceptions.VendedorInvalidoException;
import br.edu.infnet.elberthapi.exceptions.VendedorNaoEncontratoException;
import br.edu.infnet.elberthapi.model.domain.Vendedor;
import br.edu.infnet.elberthapi.model.repository.VendedorRepository;

@Service
public class VendedorService implements CrudService<Vendedor, Integer> {
	
	private final VendedorRepository vendedorRepository;

	public VendedorService(VendedorRepository vendedorRepository) {
		this.vendedorRepository = vendedorRepository;
	}

	private void validar(Vendedor vendedor) {
		if(vendedor == null) {
			throw new IllegalArgumentException("O vendedor não pode estar nulo!!");
		}
		
		if(vendedor.getNome() == null || vendedor.getNome().trim().isEmpty()) {
			throw new VendedorInvalidoException("O nome do vendedor é uma informação obrigatória!!!");
		}		
	}
	
	@Override
	public Vendedor incluir(Vendedor vendedor) {
		
		validar(vendedor);
		
		if(vendedor.getId() != null && vendedor.getId() > 0) {
			throw new IllegalArgumentException("O novo vendedor não pode ter um ID preenchido durante a inclusão!!");
		}		
		
		return vendedorRepository.save(vendedor);
	}

	@Override
	public List<Vendedor> obterLista() {
		
		return vendedorRepository.findAll();
	}

	@Override
	public Vendedor alterar(Integer id, Vendedor vendedor) {
				
		validar(vendedor);
		
		vendedor.setId(id);
		
		return vendedorRepository.save(vendedor);
	}

	@Override
	public void excluir(Integer id) {
		Vendedor vendedor = obterPorId(id);

		vendedorRepository.delete(vendedor);
	}

	@Override
	public Vendedor obterPorId(Integer id) {
		if(id == null || id <= 0) {
			throw new IllegalArgumentException("o ID utilizado na busca do vendedor não pode ser nulo/zero/negativo");
		}

		return vendedorRepository.findById(id).orElseThrow(() -> new VendedorNaoEncontratoException("O vendedor com o ID ["+id+"] não foi encontrado!"));
	}

	public Vendedor inativar(Integer id) {
		
		Vendedor vendedor = obterPorId(id);
		
		if(!vendedor.isAtivo()) {
			System.err.println("O vendedor "+vendedor.getNome()+" não está inativo!");
			return vendedor;
		}
		
		vendedor.setAtivo(false);

		return vendedorRepository.save(vendedor);
	}

	public Vendedor obterPorCpf(String cpf) {
		if(cpf == null || cpf.trim().isEmpty()) {
			throw new IllegalArgumentException("O CPF utilizado na busca do vendedor não pode ser nulo ou vazio.");
		}
		
		return vendedorRepository.findByCpf(cpf).orElseThrow(() -> new VendedorNaoEncontratoException("O vendedor com o CPF ["+cpf+"] não foi encontrado!"));
	}
}