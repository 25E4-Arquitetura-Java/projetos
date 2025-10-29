package br.edu.infnet.elberthapi;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Collection;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import br.edu.infnet.elberthapi.clients.ViaCepFeignClient;
import br.edu.infnet.elberthapi.model.domain.Vendedor;
import br.edu.infnet.elberthapi.model.service.VendedorService;

@Order(1)
@Component
public class VendedorLoader implements ApplicationRunner {
	
	private final VendedorService vendedorService;
	private final ViaCepFeignClient cepFeignClient;
	
	public VendedorLoader(VendedorService vendedorService, ViaCepFeignClient cepFeignClient) {
		this.vendedorService = vendedorService;
		this.cepFeignClient = cepFeignClient;
	}
	
	@Override
	public void run(ApplicationArguments args) throws Exception {
		
		FileReader arquivo = new FileReader("vendedores-listagem.csv");
		BufferedReader leitura = new BufferedReader(arquivo);
		
		String linha = leitura.readLine();
		String[] campos = null;

		while(linha != null) {

			campos = linha.split(";");

			Vendedor vendedor = new Vendedor();
			vendedor.setNome(campos[0]);
			vendedor.setEmail(campos[1]);
			vendedor.setCpf(campos[2]);
			vendedor.setTelefone(campos[3]);
			vendedor.setMatricula(Integer.valueOf(campos[4]));
			vendedor.setSalario(Double.valueOf(campos[5]));
			vendedor.setAtivo(Boolean.valueOf(campos[6]));
			vendedor.setEndereco(cepFeignClient.findByCep(campos[7]));
			
			vendedorService.incluir(vendedor);
						
			linha = leitura.readLine();
		}

		Collection<Vendedor> vendedores = vendedorService.obterLista();
		
		vendedores.forEach(System.out::println);
		
		leitura.close();
	}

}
