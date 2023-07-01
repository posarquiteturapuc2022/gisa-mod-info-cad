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
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.EqualsAndHashCode.Include;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(schema = "gisamodinfocaddb")
@AttributeOverride(name = "id", column = @Column(name = "id_profissao"))
public class Profissao extends EntityAbstract implements Serializable, Comparable<Profissao> {

	private static final long serialVersionUID = 8020526587695386518L;

	@Include
	@Id
	@GeneratedValue(strategy = IDENTITY)
	private UUID id;

	private String nome;

	@Override
	public int compareTo(Profissao o) {
		return  o.getCreatedAt().compareTo(getCreatedAt());
	}
}

