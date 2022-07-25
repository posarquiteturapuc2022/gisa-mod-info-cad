package br.com.posarquiteturapuc2022.services;

import java.util.List;
import java.util.UUID;

import br.com.posarquiteturapuc2022.domain.Associado;

public interface AssociadoService {


	public Associado findById(UUID id);

	public Associado findByCpf(String cpf);

	public List<Associado> findAll();
	
//	public ResponseEntity<List<Usuario>> getListaAssociados();

	public Associado save(Associado associado);
	
	public Associado update(Associado associado);
	
	public void delete(UUID id);
}
