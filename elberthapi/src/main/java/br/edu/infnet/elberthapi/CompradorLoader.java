package br.edu.infnet.elberthapi;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Collection;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import br.edu.infnet.elberthapi.model.domain.Comprador;
import br.edu.infnet.elberthapi.model.service.CompradorService;

@Component
public class CompradorLoader implements ApplicationRunner {
    
    private final CompradorService compradorService;

    public CompradorLoader(CompradorService compradorService) {
        this.compradorService = compradorService;
    }
    
    @Override
    public void run(ApplicationArguments args) throws Exception {
        System.out.println("[CompradorLoader] Iniciando carregamento de compradores...");

        try (FileReader arquivo = new FileReader("compradores-listagem.csv");
             BufferedReader leitura = new BufferedReader(arquivo)) {
        
            String linha = leitura.readLine(); // Lê o cabeçalho

            if (linha == null || linha.isEmpty()) {
                System.out.println("[CompradorLoader] Arquivo CSV de compradores vazio ou sem cabeçalho.");
                return;
            }
            
            linha = leitura.readLine();

            while(linha != null) {

                String[] campos = linha.split(";");

                if (campos.length >= 5) {
                    Comprador comprador = new Comprador();
                    comprador.setNome(campos[0]);
                    comprador.setEmail(campos[1]);
                    comprador.setCpf(campos[2]);
                    comprador.setTelefone(campos[3]);
                    comprador.setFidelidade(campos[4]);
                    
                    compradorService.incluir(comprador);
                } else {
                    System.err.println("[CompradorLoader] Linha inválida no CSV (número de campos insuficiente): " + linha);
                }
                
                linha = leitura.readLine();
            }

        } catch (IOException e) {
            System.err.println("[CompradorLoader] Erro ao ler o arquivo compradores-listagem.csv: " + e.getMessage());
        }

        Collection<Comprador> compradores = compradorService.obterLista();
        
        System.out.println("\n--- Compradores Carregados ---");
        if (compradores.isEmpty()) {
            System.out.println("Nenhum comprador foi carregado.");
        } else {
            compradores.forEach(System.out::println);
        }
        System.out.println("----------------------------\n");
    }
}