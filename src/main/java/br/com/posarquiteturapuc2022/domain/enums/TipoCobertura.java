package br.com.posarquiteturapuc2022.domain.enums;

import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum TipoCobertura {
	INDIVIDUAL_NACIONAL(1, "Individual Nacional"), 
	EMPRESARIAL_NACIONAL(2, "Empresarial Nacional"), 
	INDIVIDUAL_INTERNACIONAL(3, "Individual Internacional"),
	EMPRESARIAL_INTERNACIONAL(4, "Empresarial Internacional");
	
	private Integer codigo;
	private String descricao;
	
	private TipoCobertura(Integer codigo, String descricao) {
		this.codigo = codigo;
		this.descricao = descricao;
	}

	public Integer getCodigo() {
		return codigo;
	}

	public String getDescricao() {
		return descricao;
	}
	
	public static TipoCobertura toEnum(Integer cod) {
		if(cod == null) {
			return null;
		}
		
		for(TipoCobertura x : TipoCobertura.values()) {
			if(cod.equals(x.getCodigo())) {
				return x;
			}
		}
		
		throw new IllegalArgumentException("Tipo de Cobertura inválido");
	}

}
