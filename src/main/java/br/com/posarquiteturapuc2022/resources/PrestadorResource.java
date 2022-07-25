package br.com.posarquiteturapuc2022.resources;

import java.util.List;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import br.com.posarquiteturapuc2022.domain.Prestador;


public interface PrestadorResource {
	
	@GetMapping(value = "/{id}")
	ResponseEntity<Prestador> findById(@PathVariable UUID id);

	@GetMapping
	ResponseEntity<List<Prestador>> findAll();

	@PostMapping(value = "/cadastro/prestador")
	ResponseEntity<Prestador> save(Prestador prestador);

	@PostMapping(value = "/atualiza/prestador")
	ResponseEntity<Prestador> update(Prestador prestador);

	@DeleteMapping(value = "/{id}")
	ResponseEntity<Void> delete(UUID id);

}
