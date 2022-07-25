package br.com.posarquiteturapuc2022.domain;

import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;
import java.util.UUID;

import javax.persistence.*;

import br.com.posarquiteturapuc2022.utils.EntityAbstract;
import lombok.EqualsAndHashCode.Include;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "CONSULTA")
@Table(schema = "gisamodinfocaddb")
@AttributeOverride(name = "id", column = @Column(name = "id_consulta"))
public class Consulta extends EntityAbstract implements Serializable, Comparable<Consulta> {

	private static final long serialVersionUID = 8020526587695386518L;

	@Include
	@Id
	@GeneratedValue(strategy = IDENTITY)
	private UUID id;

	private String nome;
	private String descricao;

	@Override
	public int compareTo(Consulta o) {
		return  o.getCreatedAt().compareTo(getCreatedAt());
	}
}
