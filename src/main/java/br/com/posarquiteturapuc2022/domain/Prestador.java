package br.com.posarquiteturapuc2022.domain;

import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;
import java.util.UUID;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import br.com.posarquiteturapuc2022.domain.enums.TipoPrestador;
import br.com.posarquiteturapuc2022.utils.EntityAbstract;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode.Include;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(schema = "gisamodinfocaddb")
@AttributeOverride(name = "id", column = @Column(name = "id_associado"))
public class Prestador extends EntityAbstract implements Serializable, Comparable<Associado>{
	
	private static final long serialVersionUID = 5136765744664261941L;

	@Include
	@Id
    @GeneratedValue(strategy = IDENTITY)
    private UUID id;
    
    private String cnpjPrestador;
    private String nomePrestador;
    private String emailPrestador;
    private String responsavelPrestador;
    
    private TipoPrestador tipoPrestador; 
    
	@ManyToOne
	@JoinColumn(name = "id_especialidade")
    private Especialidade especialidade; 
    private boolean situacao;
    
	@Override
	public int compareTo(Associado o) {
		return  o.getCreatedAt().compareTo(getCreatedAt());
	}
}
