package br.com.posarquiteturapuc2022.feignClients;

import java.util.List;
import java.util.UUID;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import br.com.posarquiteturapuc2022.domain.Usuario;

@FeignClient(name = "gisa-user-api", url = "http://localhost:8000/api/usuarios")
public interface UserFeign {

	@GetMapping(value = "/{id}")
	ResponseEntity<Usuario> findById(@PathVariable UUID id);
	
	@GetMapping(value = "/{cpfAssociado}")
	ResponseEntity<Usuario> findByCpf(@PathVariable String cpfAssociado);
	
	@GetMapping(value = "/{cnpj}")
	ResponseEntity<Usuario> findByCnpj(@PathVariable String cnpj);
	
	@GetMapping
	ResponseEntity<List<Usuario>> findAll();
	
	@PostMapping
	ResponseEntity<Usuario> save(@RequestBody Usuario usuario);

	@PutMapping
	ResponseEntity<Usuario> update(@RequestBody Usuario usuario);

	@DeleteMapping(value = "/{id}")
	ResponseEntity<Void> delete(@PathVariable UUID id);
	
}
