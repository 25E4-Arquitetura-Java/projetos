package br.edu.infnet.elberthapi.exceptions;

public class CompradorNaoEncontratoException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public CompradorNaoEncontratoException(String mensagem) {
		super(mensagem);
	}
}
