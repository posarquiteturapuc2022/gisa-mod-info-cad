package br.com.posarquiteturapuc2022.resources;

import java.util.List;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import br.com.posarquiteturapuc2022.domain.Associado;

public interface AssociadoResource {
	
	@GetMapping(value = "/{id}")
	ResponseEntity<Associado> findById(@PathVariable UUID id);
	
	@GetMapping
	ResponseEntity<List<Associado>> findAll();

	@PostMapping(value = "/cadastro/associado")
	ResponseEntity<Associado> save(@RequestBody Associado associado);

	@PostMapping(value = "/atualiza/associado")
	ResponseEntity<Associado> update(@RequestBody Associado associado);

	@DeleteMapping(value = "/{id}")
	ResponseEntity<Void> delete(@PathVariable UUID id);
}
