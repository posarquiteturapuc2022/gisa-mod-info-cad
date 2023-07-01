package br.com.posarquiteturapuc2022.resources.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.posarquiteturapuc2022.domain.Associado;
import br.com.posarquiteturapuc2022.resources.AssociadoResource;
import br.com.posarquiteturapuc2022.service.AssociadoService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/v1/api/associados")
public class AssociadoResourceImpl implements AssociadoResource{

	private final AssociadoService associadoService;
	
	@Override
	public ResponseEntity<Associado> findById(UUID id) {
		return ResponseEntity.ok().body(associadoService.findById(id));
	}

	@Override
	public ResponseEntity<Associado> findByCpf(String cpf) {
		return ResponseEntity.ok().body(associadoService.findByCpf(cpf));
	}

	@Override
	public ResponseEntity<List<Associado>> findAll() {
		return ResponseEntity.ok().body(associadoService.findAll());
	}

	@Override
	public ResponseEntity<Associado> save(Associado associado) {
		return ResponseEntity.ok().body(associadoService.save(associado));
	}

	@Override
	public ResponseEntity<Associado> update(Associado associado) {
		return ResponseEntity.ok().body(associadoService.update(associado));
	}

	@Override
	public ResponseEntity<Void> delete(@PathVariable UUID id) {
		associadoService.delete(id);
		return ResponseEntity.noContent().build();
	}
}