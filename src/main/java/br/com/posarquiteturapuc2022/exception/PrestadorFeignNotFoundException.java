package br.com.posarquiteturapuc2022.exception;

public class PrestadorFeignNotFoundException extends RuntimeException {
	
	private static final long serialVersionUID = -8820746741138140448L;

	public PrestadorFeignNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

	public PrestadorFeignNotFoundException(String message) {
		super(message);
	}

}
