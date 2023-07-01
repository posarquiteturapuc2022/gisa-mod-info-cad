package br.com.posarquiteturapuc2022.exception;

public class AssociadoFeignNotFoundException extends RuntimeException {
	
	private static final long serialVersionUID = -128806135749313762L;

	public AssociadoFeignNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

	public AssociadoFeignNotFoundException(String message) {
		super(message);
	}

}
