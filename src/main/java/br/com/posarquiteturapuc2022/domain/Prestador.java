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
import javax.persistence.PrePersist;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import br.com.posarquiteturapuc2022.domain.enums.TipoPrestador;
import br.com.posarquiteturapuc2022.domain.enums.TipoUsuario;
import br.com.posarquiteturapuc2022.utils.EntityAbstract;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode.Include;
import lombok.NoArgsConstructor;

/**
 * @author alvar
 *
 */
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(schema = "gisamodinfocaddb")
@AttributeOverride(name = "id", column = @Column(name = "id_prestador"))
public class Prestador extends EntityAbstract implements Serializable, Comparable<Prestador>{
	
	private static final long serialVersionUID = 5136765744664261941L;

	@Include
	@Id
    @GeneratedValue(strategy = IDENTITY)
    private UUID id;
    
    private String cnpjPrestador;
    private String nomePrestador;
    private String emailPrestador;
    private String responsavelPrestador;
    
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataCadastro;
    
    private TipoPrestador tipoPrestador; 
    private TipoUsuario tipoUsuario;
    
    private boolean situacao;
    
    @PrePersist
    public void prepersist() {
    	this.setDataCadastro(LocalDate.now());
    }
    
	@Override
	public int compareTo(Prestador o) {
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
		Prestador other = (Prestador) obj;
		return Objects.equals(cnpjPrestador, other.cnpjPrestador) && Objects.equals(dataCadastro, other.dataCadastro)
				&& Objects.equals(emailPrestador, other.emailPrestador) && Objects.equals(id, other.id)
				&& Objects.equals(nomePrestador, other.nomePrestador)
				&& Objects.equals(responsavelPrestador, other.responsavelPrestador) && situacao == other.situacao
				&& tipoPrestador == other.tipoPrestador;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(cnpjPrestador, dataCadastro, emailPrestador, id, nomePrestador,
				responsavelPrestador, situacao, tipoPrestador);
		return result;
	}
}