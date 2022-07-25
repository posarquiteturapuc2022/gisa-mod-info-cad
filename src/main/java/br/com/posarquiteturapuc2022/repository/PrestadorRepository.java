package br.com.posarquiteturapuc2022.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.posarquiteturapuc2022.domain.Prestador;

@Repository
public interface PrestadorRepository extends JpaRepository<Prestador, UUID>{

	// Consulta apenas o prestador pelo cnpj
	@Transactional(readOnly = true)
	@Query(value = "SELECT p FROM Prestador p WHERE p.cnpjPrestador =:cnpjPrestador")
	public Optional<Prestador> findByCnpj(@Param("cnpjPrestador") String cnpjPrestador);
	
}
