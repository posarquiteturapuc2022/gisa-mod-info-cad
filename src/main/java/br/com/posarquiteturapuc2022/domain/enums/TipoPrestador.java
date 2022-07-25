package br.com.posarquiteturapuc2022.domain.enums;

import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum TipoPrestador {
	MEDICO(1, "Médico"), 
	ENFERMEIRO(2, "Enfermeiro"), 
	FISIOTERAPEUTA(3, "Fisioterapeuta"), 
	DENTISTA(4, "Dentista"), 
	FONOAUDIOLOGO(5, "Fonoaudiólogo"), 
	PSICOLOGA(6, "Psicóloga"), 
	FAMACEUTICO(7, "Famacêutico"), 
	BIOMEDICO(8, "Biomédico"), 
	RADIOLOGISTA(9, "Radiologista"), 
	NUTRICIONISTA(10, "Nutricionista"), 
	PSIQUIATRA(11, "Psiquiatra"), 
	TERAPEUTA_OCUPACIONAL(12, "Terapeuta Ocupacional");
	
	private Integer codigo;
	private String descricao;
	
	private TipoPrestador(Integer codigo, String descricao) {
		this.codigo = codigo;
		this.descricao = descricao;
	}

	public Integer getCodigo() {
		return codigo;
	}

	public String getDescricao() {
		return descricao;
	}
	
	public static TipoPrestador toEnum(Integer cod) {
		if(cod == null) {
			return null;
		}
		
		for(TipoPrestador x : TipoPrestador.values()) {
			if(cod.equals(x.getCodigo())) {
				return x;
			}
		}
		
		throw new IllegalArgumentException("Tipo de Prestador inválido");
	}

}
