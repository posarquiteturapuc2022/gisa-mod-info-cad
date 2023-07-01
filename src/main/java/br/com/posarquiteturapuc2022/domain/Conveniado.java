package br.com.posarquiteturapuc2022.domain;

import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import br.com.posarquiteturapuc2022.domain.enums.TipoConveniado;
import br.com.posarquiteturapuc2022.domain.enums.TipoUsuario;
import br.com.posarquiteturapuc2022.utils.EntityAbstract;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode.Include;
import lombok.NoArgsConstructor;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(schema = "gisamodinfocaddb")
@AttributeOverride(name = "id", column = @Column(name = "id_conveniado"))
public class Conveniado extends EntityAbstract implements Serializable, Comparable<Conveniado> {

	private static final long serialVersionUID = 8020526587695386518L;

	@Include
	@Id
	@GeneratedValue(strategy = IDENTITY)
	private UUID id;

	private String cnpjConveniado;
	private String nomeConveniado;
	private String emailConveniado;
    private LocalDate dataCadastro;
    
    private TipoConveniado tipoConveniado; 
	private TipoUsuario tipoUsuario; 
	
    private boolean situacao;

	@Override
	public int compareTo(Conveniado o) {
		return  o.getCreatedAt().compareTo(getCreatedAt());
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Conveniado other = (Conveniado) obj;
		return Objects.equals(cnpjConveniado, other.cnpjConveniado) && Objects.equals(dataCadastro, other.dataCadastro)
				&& Objects.equals(emailConveniado, other.emailConveniado) && Objects.equals(id, other.id)
				&& Objects.equals(nomeConveniado, other.nomeConveniado) && situacao == other.situacao
				&& tipoConveniado == other.tipoConveniado;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(cnpjConveniado, dataCadastro, emailConveniado, id,
				nomeConveniado, situacao, tipoConveniado);
		return result;
	}
}