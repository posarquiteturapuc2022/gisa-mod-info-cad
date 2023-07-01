package br.com.posarquiteturapuc2022.resources;

import java.util.List;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import br.com.posarquiteturapuc2022.domain.Conveniado;

public interface ConveniadoResource {
	
	@RequestMapping(value = "/id/{id}", method = RequestMethod.GET)
	ResponseEntity<Conveniado> findById(@PathVariable UUID id);

	@RequestMapping(value = "/cnpj/{cnpj}", method = RequestMethod.GET)
	ResponseEntity<Conveniado> findByCnpj(@PathVariable String cnpj);

	@RequestMapping(value = "/lista", method = RequestMethod.GET)
	ResponseEntity<List<Conveniado>> findAll();

    @RequestMapping(value = {"/save"}, method = RequestMethod.POST)
	ResponseEntity<Conveniado> save(@RequestBody Conveniado conveniado);

	@RequestMapping(value = {"/update"}, method = RequestMethod.POST)
	ResponseEntity<Conveniado> update(@RequestBody Conveniado conveniado);

	@DeleteMapping(value = "/delete/{id}")
	ResponseEntity<Void> delete(@PathVariable UUID id);
}