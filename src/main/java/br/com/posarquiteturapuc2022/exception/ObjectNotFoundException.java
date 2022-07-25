package br.com.posarquiteturapuc2022.exception;

public class ObjectNotFoundException extends RuntimeException{

	private static final long serialVersionUID = 3230839809815501948L;
	
	public ObjectNotFoundException(String menssage) {
		super(menssage);
	}

}
