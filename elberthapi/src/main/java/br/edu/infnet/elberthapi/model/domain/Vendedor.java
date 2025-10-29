package br.edu.infnet.elberthapi.model.domain;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

@Entity
public class Vendedor extends Pessoa {

	@NotNull(message = "A matrícula é obrigatória.")
	@Min(value = 1, message = "A matrícula deve ser um número positivo maior q zero.")
	private int matricula;
	
	@NotNull(message = "O salário é obrigatório.")
	@Min(value = 0, message = "O salário não pode ser negativo.")
	private double salario;
	
	private boolean ativo;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "endereco_id")
	@Valid
	private Endereco endereco;
	
	@OneToMany(mappedBy = "vendedor", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Produto> produtos;

	@Override
	public String toString() {
	    return String.format(
	        "%s | Matrícula: %05d | Salário: R$ %8.2f | Ativo: %-3s | Endereco: %s", // | Produtos: %d
	        super.toString(),
	        matricula,
	        salario,
	        ativo ? "Sim" : "Não",
	        endereco
	        //,produtos != null ? produtos.size() : 0
	    );
	}

	public int getMatricula() {
		return matricula;
	}
	public void setMatricula(int matricula) {
		this.matricula = matricula;
	}
	public double getSalario() {
		return salario;
	}
	public void setSalario(double salario) {
		this.salario = salario;
	}
	public boolean isAtivo() {
		return ativo;
	}
	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}
	public Endereco getEndereco() {
		return endereco;
	}
	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public List<Produto> getProdutos() {
		return produtos;
	}

	public void setProdutos(List<Produto> produtos) {
		this.produtos = produtos;
	}
}