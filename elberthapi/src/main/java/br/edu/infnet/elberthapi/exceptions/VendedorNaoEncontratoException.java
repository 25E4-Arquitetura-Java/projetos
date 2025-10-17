package br.edu.infnet.elberthapi.exceptions;

public class VendedorNaoEncontratoException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public VendedorNaoEncontratoException(String mensagem) {
		super(mensagem);
	}
}
