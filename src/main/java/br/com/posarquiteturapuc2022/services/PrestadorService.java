package br.com.posarquiteturapuc2022.services;

import java.util.List;
import java.util.UUID;

import br.com.posarquiteturapuc2022.domain.Prestador;

public interface PrestadorService {

	public Prestador findById(UUID id);
     
	public List<Prestador> findAll();

	public Prestador findByCnpj(String cnpj);
     
	public Prestador save(Prestador prestador);
     
	public Prestador update(Prestador prestador);
     
	public void delete(UUID id);

}
