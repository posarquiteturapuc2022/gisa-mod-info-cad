package br.com.posarquiteturapuc2022.domain.enums;

import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum TipoPrestador {
	MEDICO(0, "Médico"), 
	ENFERMEIRO(1, "Enfermeiro"), 
	FISIOTERAPEUTA(2, "Fisioterapeuta"), 
	DENTISTA(3, "Dentista"), 
	FONOAUDIOLOGO(4, "Fonoaudiólogo"), 
	PSICOLOGA(5, "Psicóloga"), 
	FAMACEUTICO(6, "Famacêutico"), 
	BIOMEDICO(7, "Biomédico"), 
	RADIOLOGISTA(8, "Radiologista"), 
	NUTRICIONISTA(9, "Nutricionista"), 
	PSIQUIATRA(10, "Psiquiatra"), 
	TERAPEUTA_OCUPACIONAL(11, "Terapeuta Ocupacional");
	
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
