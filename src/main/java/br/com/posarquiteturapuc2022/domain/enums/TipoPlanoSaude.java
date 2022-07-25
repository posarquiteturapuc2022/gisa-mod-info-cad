package br.com.posarquiteturapuc2022.domain.enums;

import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum TipoPlanoSaude {
	ENFERMARIA(1, "Enfermaria - Plano básico com co-participação"), 
	APARTAMENTO(2, "Apartamento - Plano intermediário sem co-particiação"), 
	VIP(3, "VIP - Plano com mais serviços e atendimentos incluídos");
	
	private Integer codigo;
	private String descricao;
	
	private TipoPlanoSaude(Integer codigo, String descricao) {
		this.codigo = codigo;
		this.descricao = descricao;
	}

	public Integer getCodigo() {
		return codigo;
	}

	public String getDescricao() {
		return descricao;
	}
	
	public static TipoPlanoSaude toEnum(Integer cod) {
		if(cod == null) {
			return null;
		}
		
		for(TipoPlanoSaude x : TipoPlanoSaude.values()) {
			if(cod.equals(x.getCodigo())) {
				return x;
			}
		}
		
		throw new IllegalArgumentException("Tipo de Plano de Saúde inválido");
	}

}
