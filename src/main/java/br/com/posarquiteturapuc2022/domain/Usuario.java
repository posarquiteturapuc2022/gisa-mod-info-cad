package br.com.posarquiteturapuc2022.domain;

import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.UUID;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.com.posarquiteturapuc2022.utils.EntityAbstract;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode.Include;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "USUARIO")
@Table(schema = "gisamodinfocaddb")
@AttributeOverride(name = "id", column = @Column(name = "id_usuario"))
public class Usuario extends EntityAbstract implements Serializable, Comparable<Usuario>{
	
	private static final long serialVersionUID = 1236929852377564915L;
	
	@Include
	@Id
	@GeneratedValue(strategy = IDENTITY)
    private UUID id;

	private String nome;
    private String email;
    
	@Column(length = 20)
	private String cnpj;
	
	@Column(length = 15)
	private String cpf;
	
    private String numeroSUS;
    private String cidade;
    private String uf;
    
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataNascimento;

    private String password;
    
	@Override
	public int compareTo(Usuario o) {
		return  o.getCreatedAt().compareTo(getCreatedAt());
	}
	
	@PrePersist
	public void prepersist() {
		this.setDataNascimento(LocalDate.now());
	}
}



