package br.com.posarquiteturapuc2022.resources;

import java.util.List;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import br.com.posarquiteturapuc2022.domain.Prestador;

public interface PrestadorResource {
	
	@RequestMapping(value = "/id/{id}", method = RequestMethod.GET)
	ResponseEntity<Prestador> findById(@PathVariable UUID id);

	@RequestMapping(value = "/cnpj/{cnpj}", method = RequestMethod.GET)
	ResponseEntity<Prestador> findByCnpj(@PathVariable String cnpj);

	@RequestMapping(value = "/lista", method = RequestMethod.GET)
	ResponseEntity<List<Prestador>> findAll();

    @RequestMapping(value = {"/save"}, method = RequestMethod.POST)
	ResponseEntity<Prestador> save(@RequestBody Prestador prestador);

	@RequestMapping(value = {"/update"}, method = RequestMethod.PUT)
	ResponseEntity<Prestador> update(@RequestBody Prestador prestador);

	@DeleteMapping(value = "/delete/{id}")
	ResponseEntity<Void> delete(@PathVariable UUID id);
}