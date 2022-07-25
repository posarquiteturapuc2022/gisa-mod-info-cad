package br.com.posarquiteturapuc2022.domain;

import lombok.*;

import javax.persistence.*;

import br.com.posarquiteturapuc2022.utils.EntityAbstract;

import static javax.persistence.GenerationType.IDENTITY;
import static lombok.EqualsAndHashCode.Include;

import java.io.Serializable;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "ESPECIALIDADE")
@Table(schema = "gisamodinfocaddb")
@AttributeOverride(name = "id", column = @Column(name = "id_especialidade"))
public class Especialidade extends EntityAbstract implements Serializable, Comparable<Especialidade> {

	private static final long serialVersionUID = -6666063957896851258L;

	@Include
	@Id
	@GeneratedValue(strategy = IDENTITY)
	private UUID id;

	@ManyToOne
	@JoinColumn(name = "id_consulta")
	private Consulta consulta;

	@ManyToOne
	@JoinColumn(name = "id_cirurgia")
	private Cirurgia cirurgia;

	@Override
	public int compareTo(Especialidade o) {
		return  o.getCreatedAt().compareTo(getCreatedAt());
	}
}
