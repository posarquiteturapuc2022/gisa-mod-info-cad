package br.com.posarquiteturapuc2022.exception;

public class APIServiceOutException extends RuntimeException {
	
	private static final long serialVersionUID = 3230839809815501948L;

	public APIServiceOutException(String message, Throwable cause) {
		super(message, cause);
	}

	public APIServiceOutException(String message) {
		super(message);
	}

}
