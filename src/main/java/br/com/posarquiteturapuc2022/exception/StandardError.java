package br.com.posarquiteturapuc2022.exception;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class StandardError implements Serializable {

	private static final long serialVersionUID = 6252276162991245485L;

	private Long timestamp;
	private Integer status;
	private String error;
	private String message;
	private String path;

	public StandardError() {
		super();
	}

}
