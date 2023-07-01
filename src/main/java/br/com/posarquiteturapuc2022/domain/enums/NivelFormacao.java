package br.com.posarquiteturapuc2022.domain.enums;

import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum NivelFormacao {

	ENSINO_FUNDAMENTAL_NOMAL(1, "Ensino Fundamental - Normal"),
	ENSINO_FUNDAMENTAL_SUPLETIVO(2, "Ensino Fundamental - Supletivo"),
	ENSINO_MEDIO_NORMAL(3, "Ensino Médio - Normal"),
	ENSINO_MEDIO_SUPLETIVO(4, "Ensino Médio - Supletivo"),
	ENSINO_MEDIO_TECNICO(5, "Ensino Médio - Técnico"),
	SUPERIOR(6, "Superior"),
	POS_GRADUACAO_ESPECIALIZACAO(7, "Pós-Graduação Especialização"),
	POS_GRADUACAO_MESTRADO(8, "Pós-Graduação Mestrado"),
	POS_GRADUACAO_DOUTORADO(9, "Pós-Graduação Mestrado"),
	POS_DOUTORADO(10, "Pós-Doutorado"),
	LIVRE_DOCENCIA(11, "Livre Docência"),
	ADJUNTO(12, "Adjunto"),
	TITULAR(13, "Titular"),
	MBA(14, "MBA - Master in Business Administration");
	
	private Integer codigo;
	private String descricao;
	
	private NivelFormacao(Integer codigo, String descricao) {
		this.codigo = codigo;
		this.descricao = descricao;
	}

	public Integer getCodigo() {
		return codigo;
	}

	public String getDescricao() {
		return descricao;
	}
	
	public static NivelFormacao toEnum(Integer cod) {
		if(cod == null) {
			return null;
		}
		
		for(NivelFormacao x : NivelFormacao.values()) {
			if(cod.equals(x.getCodigo())) {
				return x;
			}
		}
		
		throw new IllegalArgumentException("Tipo Conveniado inválido");
	}

}
