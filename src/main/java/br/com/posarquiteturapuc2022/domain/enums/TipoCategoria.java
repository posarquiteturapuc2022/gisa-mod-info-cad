package br.com.posarquiteturapuc2022.domain.enums;

import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum TipoCategoria {
	ATIVO(1, "Ativo"), 
	SUSPENSO(2, "Suspenso"), 
	INATIVO(3, "Inativo");
	
	private Integer codigo;
	private String descricao;
	
	private TipoCategoria(Integer codigo, String descricao) {
		this.codigo = codigo;
		this.descricao = descricao;
	}

	public Integer getCodigo() {
		return codigo;
	}

	public String getDescricao() {
		return descricao;
	}
	
	public static TipoCategoria toEnum(Integer cod) {
		if(cod == null) {
			return null;
		}
		
		for(TipoCategoria x : TipoCategoria.values()) {
			if(cod.equals(x.getCodigo())) {
				return x;
			}
		}
		
		throw new IllegalArgumentException("Tipo de Categoria inválido");
	}

}
