package br.com.posarquiteturapuc2022.domain;

import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.util.UUID;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import br.com.posarquiteturapuc2022.domain.enums.TipoCategoria;
import br.com.posarquiteturapuc2022.domain.enums.TipoCobertura;
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
public class Associado extends EntityAbstract implements Serializable, Comparable<Associado>{
	
	private static final long serialVersionUID = 6089823561002678264L;

	@Include
	@Id
	@GeneratedValue(strategy = IDENTITY)
    private UUID id;

    private String nomeAssociado;
    private String cpfAssociado;
    private String emailAssociado;
    private LocalDate dataCadastro;
    	
    private Integer idade;
    private Integer codigoPlano;
    private BigDecimal valorPlanoMensal;
	private TipoCobertura tipoCobertura;
	private TipoCategoria tipoCategoria;
    private boolean odontologico;
    
    private boolean situacao;

	public void planoOdontologico(boolean planoOdontologico, BigDecimal valorPlano) {
		if (planoOdontologico) {
			setValorPlanoMensal(valorPlano);
	        NumberFormat df2 = NumberFormat.getInstance();
	        BigDecimal percent15 = new BigDecimal ("0.15");
	        System.out.println("Valor Fixo do Plano: " + getValorPlanoMensal());
	        System.out.println ("15% de Valor do Plano Mensal: " + df2.format(getValorPlanoMensal().multiply(percent15)).replace(",","."));
	        BigDecimal vpm = new BigDecimal(String.valueOf(df2.format(getValorPlanoMensal().multiply(percent15)).replace(",",".")));
			setValorPlanoMensal(valorPlano.add(vpm));
			System.out.println("setValorPlanoMensal(): " + valorPlano.add(vpm));
		}
	}

	@Override
	public int compareTo(Associado o) {
		return  o.getCreatedAt().compareTo(getCreatedAt());
	}
}
