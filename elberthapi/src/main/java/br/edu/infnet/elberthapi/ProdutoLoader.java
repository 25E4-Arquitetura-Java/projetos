package br.edu.infnet.elberthapi;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Collection;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import br.edu.infnet.elberthapi.exceptions.VendedorNaoEncontratoException;
import br.edu.infnet.elberthapi.model.domain.Produto;
import br.edu.infnet.elberthapi.model.domain.TipoProduto;
import br.edu.infnet.elberthapi.model.domain.Vendedor;
import br.edu.infnet.elberthapi.model.service.ProdutoService;
import br.edu.infnet.elberthapi.model.service.VendedorService;

@Order(2)
@Component
public class ProdutoLoader implements ApplicationRunner {
	
	private final ProdutoService produtoService;
	private final VendedorService vendedorService;
	
	public ProdutoLoader(ProdutoService produtoService, VendedorService vendedorService) {
		this.produtoService = produtoService;
		this.vendedorService = vendedorService;
	}
	
	@Override
	public void run(ApplicationArguments args) throws Exception {
		System.out.println("[ProdutoLoader] Iniciando carregamento de produtos...");

		try (FileReader arquivo = new FileReader("produtos-listagem.csv");
			 BufferedReader leitura = new BufferedReader(arquivo)) {
		
			String linha = leitura.readLine();

			if (linha == null || linha.isEmpty()) {
				System.out.println("[ProdutoLoader] Arquivo CSV de produtos vazio ou sem cabeçalho.");
				return;
			}
			
			while(linha != null) {

				String[] campos = linha.split(";");
				
				String cpfVendedor = campos[3];
				
				Vendedor vendedorResponsavel = null;
				try {
					vendedorResponsavel = vendedorService.obterPorCpf(cpfVendedor);

					if(vendedorResponsavel == null) {
						System.err.println("[ERRO] Vendedor não encontrado!!!");
						
						linha = leitura.readLine();
						
						continue;
					}
					
					Produto produto = new Produto();
					produto.setDescricao(campos[0]);
					produto.setValor(Double.valueOf(campos[1]));
					produto.setTipoProduto(TipoProduto.valueOf(campos[2]));
					
					produto.setVendedor(vendedorResponsavel);

					produtoService.incluir(produto);
					System.out.println("[ProdutoLoader] Produto incluído: " + produto.getDescricao() + ")");

				} catch (VendedorNaoEncontratoException e) {
					System.err.println("[ERRO] " + e.getMessage());
				}
				

				linha = leitura.readLine();
			}

		} catch (IOException e) {
			System.err.println("[ProdutoLoader] Erro ao ler o arquivo produtos-listagem.csv: " + e.getMessage());
		}

		Collection<Produto> produtos = produtoService.obterLista();
		
		System.out.println("\n--- Produtos Carregados ---");
		if (produtos.isEmpty()) {
			System.out.println("Nenhum produto foi carregado.");
		} else {
			produtos.forEach(System.out::println);
		}
		System.out.println("---------------------------\n");
	}
}