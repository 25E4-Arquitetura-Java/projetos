package br.edu.infnet.elberthapi.model.domain;

public enum TipoProduto {
	ALIMENTICIO("Alimentício"),
	ROUPA("Roupa"),
	ELETRONICO("Eletrônico"),
	SERVICO("Serviço"),
	LIVRO("Livro"),
	OUTRO("Outro");

	private String descricao;

	TipoProduto(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}
}