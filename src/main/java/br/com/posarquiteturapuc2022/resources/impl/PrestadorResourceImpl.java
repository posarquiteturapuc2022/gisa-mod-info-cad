package br.com.posarquiteturapuc2022.resources.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.posarquiteturapuc2022.domain.Prestador;
import br.com.posarquiteturapuc2022.resources.PrestadorResource;
import br.com.posarquiteturapuc2022.service.impl.PrestadorServiceImpl;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/v1/api/prestadores")
public class PrestadorResourceImpl implements PrestadorResource{

	private final PrestadorServiceImpl prestadoreService;
	
	@Override
	public ResponseEntity<Prestador> findById(UUID id) {
		return ResponseEntity.ok().body(prestadoreService.findById(id));
	}
	
	@Override
	public ResponseEntity<Prestador> findByCnpj(String cnpj) {
		return ResponseEntity.ok().body(prestadoreService.findByCnpj(cnpj));
	}

	@Override
	public ResponseEntity<List<Prestador>> findAll() {
		return ResponseEntity.ok().body(prestadoreService.findAll());
	}

	@Override
	public ResponseEntity<Prestador> save(Prestador prestador) {
		return ResponseEntity.ok().body(prestadoreService.save(prestador));
	}

	@Override
	public ResponseEntity<Prestador> update(Prestador prestador) {
		return ResponseEntity.ok().body(prestadoreService.update(prestador));
	}

	@Override
	public ResponseEntity<Void> delete(@PathVariable UUID id) {
		prestadoreService.delete(id);
		return ResponseEntity.noContent().build();
	}
}