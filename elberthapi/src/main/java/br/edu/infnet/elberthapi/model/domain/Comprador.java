package br.edu.infnet.elberthapi.model.domain;

import java.time.LocalDateTime;

public class Comprador extends Pessoa {

	private String fidelidade;
	private LocalDateTime dataCadastramento;

	public String getFidelidade() {
		return fidelidade;
	}
	public void setFidelidade(String fidelidade) {
		this.fidelidade = fidelidade;
	}
	public LocalDateTime getDataCadastramento() {
		return dataCadastramento;
	}
	public void setDataCadastramento(LocalDateTime dataCadastramento) {
		this.dataCadastramento = dataCadastramento;
	}
}