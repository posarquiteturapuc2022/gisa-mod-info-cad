package br.com.posarquiteturapuc2022.resources.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.posarquiteturapuc2022.domain.Conveniado;
import br.com.posarquiteturapuc2022.resources.ConveniadoResource;
import br.com.posarquiteturapuc2022.service.impl.ConveniadoServiceImpl;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/v1/api/conveniados")
public class ConveniadoResourceImpl implements ConveniadoResource{

	private final ConveniadoServiceImpl conveniadoService;
	
	@Override
	public ResponseEntity<Conveniado> findById(UUID id) {
		return ResponseEntity.ok().body(conveniadoService.findById(id));
	}
	
	@Override
	public ResponseEntity<Conveniado> findByCnpj(String cnpj) {
		return ResponseEntity.ok().body(conveniadoService.findByCnpj(cnpj));
	}

	@Override
	public ResponseEntity<List<Conveniado>> findAll() {
		return ResponseEntity.ok().body(conveniadoService.findAll());
	}

	@Override
	public ResponseEntity<Conveniado> save(Conveniado conveniado) {
		return ResponseEntity.ok().body(conveniadoService.save(conveniado));
	}

	@Override
	public ResponseEntity<Conveniado> update(Conveniado conveniado) {
		return ResponseEntity.ok().body(conveniadoService.update(conveniado));
	}

	@Override
	public ResponseEntity<Void> delete(@PathVariable UUID id) {
		conveniadoService.delete(id);
		return ResponseEntity.noContent().build();
	}

}
