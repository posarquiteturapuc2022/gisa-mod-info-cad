package br.com.posarquiteturapuc2022.domain;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.com.posarquiteturapuc2022.domain.enums.NivelFormacao;
import br.com.posarquiteturapuc2022.domain.enums.TipoUsuario;
import br.com.posarquiteturapuc2022.utils.EntityAbstract;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
//@Entity(name = "USUARIO")
//@Table(schema = "gisamodinfocaddb")
//@AttributeOverride(name = "id", column = @Column(name = "id_usuario"))
public class Usuario extends EntityAbstract implements Serializable, Comparable<Usuario>{
	
	private static final long serialVersionUID = 1236929852377564915L;
	
//	@Include
//	@Id
//	@GeneratedValue(strategy = IDENTITY)
    private UUID id;

	private String nome;
    private String email;
    
	@Column(length = 20)
	private String cnpj;
	
	@Column(length = 15)
	private String cpf;
	
    private String numeroSUS;
    
    private String sexo;
    private String cep;
    private String endereco;
    private String numero;
    private String bairro;
    private String cidade;
    private String uf;
    private String estadoCivil;
    
    @JsonFormat(with = JsonFormat.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY)
    private NivelFormacao nivelFormacao;

    private TipoUsuario tipoUsuario;
	
    @ManyToOne
	@JoinColumn(name = "id_profissao")
    private Profissao profissao;
    
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataNascimento;
    
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataCadastro;

    private String password;
    
	@Override
	public int compareTo(Usuario o) {
		return  o.getCreatedAt().compareTo(getCreatedAt());
	}
	
	@PrePersist
	public void prepersist() {
		this.setDataCadastro(LocalDate.now());
	}
}