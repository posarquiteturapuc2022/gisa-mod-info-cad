package br.com.posarquiteturapuc2022.domain;

import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;
import java.util.UUID;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import br.com.posarquiteturapuc2022.utils.EntityAbstract;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode.Include;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "CIRURGIA")
@Table(schema = "gisamodinfocaddb")
@AttributeOverride(name = "id", column = @Column(name = "id_cirurgia"))
public class Cirurgia extends EntityAbstract implements Serializable, Comparable<Cirurgia> {

	private static final long serialVersionUID = -5788261164359994159L;

	@Include
	@Id
	@GeneratedValue(strategy = IDENTITY)
	private UUID id;

	private String nome;
	private String descricao;

	@Override
	public int compareTo(Cirurgia o) {
		return o.getCreatedAt().compareTo(getCreatedAt());
	}
}
