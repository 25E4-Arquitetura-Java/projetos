package br.edu.infnet.elberthapi.model.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Transient;

@Entity
public class Vendedor extends Pessoa {

	private int matricula;
	private double salario;
	private boolean ativo;

	@Transient
	private Endereco endereco;
	
	@Override
	public String toString() {
	    return String.format(
	        "%s | Matrícula: %05d | Salário: R$ %8.2f | Ativo: %-3s | Endereco: %s",
	        super.toString(),
	        matricula,
	        salario,
	        ativo ? "Sim" : "Não",
	        endereco
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
}