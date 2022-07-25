package br.com.posarquiteturapuc2022.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.posarquiteturapuc2022.domain.Associado;

@Repository
public interface AssociadoRepository extends JpaRepository<Associado, UUID>{

	// Consulta apenas o associado pelo cpf
	@Transactional(readOnly = true)
	@Query(value = "SELECT a FROM Associado a WHERE a.cpfAssociado =:cpfAssociado")
	public Optional<Associado> findByCpf(@Param("cpfAssociado") String cpfAssociado);

}
