package br.edu.infnet.elberthapi.exceptions;

public class CompradorInvalidoException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public CompradorInvalidoException(String mensagem) {
		super(mensagem);
	}
}
