package br.com.posarquiteturapuc2022.domain.enums;

import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum TipoStatus {
	APROVADO(1, "Aprovado"), 
	SOLICITADO(2, "Solicitado"), 
	GERADO(3, "Gerado"),
	CONCLUIDO(3, "Concluído"),
	CANCELADO(4, "Cancelado");
	
	private Integer codigo;
	private String descricao;
	
	private TipoStatus(Integer codigo, String descricao) {
		this.codigo = codigo;
		this.descricao = descricao;
	}

	public Integer getCodigo() {
		return codigo;
	}

	public String getDescricao() {
		return descricao;
	}
	
	public static TipoStatus toEnum(Integer cod) {
		if(cod == null) {
			return null;
		}
		
		for(TipoStatus x : TipoStatus.values()) {
			if(cod.equals(x.getCodigo())) {
				return x;
			}
		}
		
		throw new IllegalArgumentException("Tipo de Cobertura inválido");
	}

}
