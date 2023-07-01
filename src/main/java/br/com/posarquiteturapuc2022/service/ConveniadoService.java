package br.com.posarquiteturapuc2022.service;

import java.util.List;
import java.util.UUID;

import br.com.posarquiteturapuc2022.domain.Conveniado;

public interface ConveniadoService {

	public Conveniado findById(UUID id);
     
	public List<Conveniado> findAll();

	public Conveniado findByCnpj(String cnpj);
     
	public Conveniado save(Conveniado prestador);
     
	public Conveniado update(Conveniado prestador);
     
	public void delete(UUID id);

}
