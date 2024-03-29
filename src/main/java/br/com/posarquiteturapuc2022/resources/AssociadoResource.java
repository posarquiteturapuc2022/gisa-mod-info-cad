package br.com.posarquiteturapuc2022.resources;

import java.util.List;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import br.com.posarquiteturapuc2022.domain.Associado;

public interface AssociadoResource {
	
	@RequestMapping(value = "/id/{id}", method = RequestMethod.GET)
	ResponseEntity<Associado> findById(@PathVariable UUID id);
	
	@RequestMapping(value = "/cnpj/{cnpj}", method = RequestMethod.GET)
	ResponseEntity<Associado> findByCpf(@PathVariable String cpf);
	
	@RequestMapping(value = "/lista", method = RequestMethod.GET)
	ResponseEntity<List<Associado>> findAll();

    @RequestMapping(value = {"/save"}, method = RequestMethod.POST)
	ResponseEntity<Associado> save(@RequestBody Associado associado);

	@RequestMapping(value = {"/update"}, method = RequestMethod.PUT)
	ResponseEntity<Associado> update(@RequestBody Associado associado);

	@DeleteMapping(value = "/delete/{id}")
	ResponseEntity<Void> delete(@PathVariable UUID id);
}