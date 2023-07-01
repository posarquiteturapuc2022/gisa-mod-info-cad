package br.com.posarquiteturapuc2022.exception;

public class UsuarioFeignNotFoundException extends RuntimeException {
	
	private static final long serialVersionUID = 3230839809815501948L;

	public UsuarioFeignNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

	public UsuarioFeignNotFoundException(String message) {
		super(message);
	}

}
