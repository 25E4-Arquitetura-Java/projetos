package br.edu.infnet.elberthapi.exceptions;

public class ProdutoNaoEncontratoException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ProdutoNaoEncontratoException(String mensagem) {
		super(mensagem);
	}
}
