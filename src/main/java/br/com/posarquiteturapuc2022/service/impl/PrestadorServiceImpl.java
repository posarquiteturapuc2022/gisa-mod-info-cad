package br.com.posarquiteturapuc2022.service.impl;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import br.com.posarquiteturapuc2022.domain.Especialidade;
import br.com.posarquiteturapuc2022.domain.Prestador;
import br.com.posarquiteturapuc2022.domain.Usuario;
import br.com.posarquiteturapuc2022.domain.enums.TipoPrestador;
import br.com.posarquiteturapuc2022.domain.enums.TipoUsuario;
import br.com.posarquiteturapuc2022.exception.ObjectNotFoundException;
import br.com.posarquiteturapuc2022.feignInfoCad.UsuarioApiFeign;
import br.com.posarquiteturapuc2022.repository.PrestadorRepository;
import br.com.posarquiteturapuc2022.service.PrestadorService;
import feign.FeignException;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class PrestadorServiceImpl implements PrestadorService{
	
	private final Logger logger = LoggerFactory.getLogger(PrestadorServiceImpl.class);

	private final PrestadorRepository prestadorRepository;

	private final UsuarioApiFeign infoCadFeign;
	
	private Usuario CACHE = new Usuario();

	@Override
	public Prestador findById(UUID id) {
		return prestadorRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException("Prestador não encontrado!"));
	}

	@Override
	public List<Prestador> findAll() {
		return prestadorRepository.findAll();
	}
	
	@Override
	public Prestador findByCnpj(String cnpj) {
		return prestadorRepository.findByCnpj(cnpj).orElseThrow(() -> new ObjectNotFoundException("Prestador não encontrado pelo cnpj!"));
	}
	
	@Override
	public Prestador save(Prestador prestador) {
		Usuario usuario = buscaUsuarioApiCnpj(prestador.getCnpjPrestador());
		
		if (!usuario.getCnpj().equals(prestador.getCnpjPrestador())) {
			throw new ObjectNotFoundException("Usuário não encontrado!");
		}
		
		prestador.setNomePrestador(usuario.getNome());
		prestador.setEmailPrestador(usuario.getEmail());
		if (usuario.getCnpj() != null 
				&& (usuario.getTipoUsuario().equals(TipoUsuario.ASSOCIADO) 
					|| usuario.getTipoUsuario().equals(TipoUsuario.PRESTADOR))) {
			prestador.setTipoUsuario(usuario.getTipoUsuario());
		}
		prestador.setCnpjPrestador(usuario.getCnpj());
		prestador.setResponsavelPrestador(usuario.getNome());
	    prestador.setTipoPrestador(TipoPrestador.MEDICO); 
	    prestador.setSituacao(true);;
		return prestadorRepository.save(prestador);
	}

	@Override
	public Prestador update(Prestador prestador) {
		Especialidade especialidade = new Especialidade(); 
		Usuario usuario = buscaUsuarioApiCnpj(prestador.getCnpjPrestador());
		
		if (!usuario.getCnpj().equals(prestador.getCnpjPrestador())) {
			throw new ObjectNotFoundException("Usuário não encontrado!");
		}
		
		prestador.setNomePrestador(usuario.getNome());
		prestador.setEmailPrestador(usuario.getEmail());
		if (usuario.getCnpj() != null 
				&& (usuario.getTipoUsuario().equals(TipoUsuario.ASSOCIADO) 
				|| usuario.getTipoUsuario().equals(TipoUsuario.PRESTADOR))) {
			prestador.setTipoUsuario(usuario.getTipoUsuario());
		}
		prestador.setCnpjPrestador(usuario.getCnpj());
		prestador.setResponsavelPrestador(usuario.getNome());
		prestador.setTipoPrestador(TipoPrestador.MEDICO); 
		prestador.setSituacao(true);;
		return prestadorRepository.saveAndFlush(prestador);
	}

	@Override
	public void delete(UUID id) {
		Prestador prestador = prestadorRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException("Prestador não encontrado!"));
		prestadorRepository.delete(prestador);	
	}

	public Usuario buscaUsuarioApiCnpj(String cnpj) {
		try {
			var usuario = infoCadFeign.findByCnpj(cnpj).getBody();
			logger.info("Buscando da API de Usuários");
			if (Objects.nonNull(usuario)) {
				return new Usuario(
						usuario.getId(), 
						usuario.getNome(),
						usuario.getEmail(), 
						usuario.getCnpj(), 
						null, 
						null, 
						null, 
						usuario.getCep(), 
						usuario.getEndereco(), 
						usuario.getNumero(), 
						usuario.getBairro(), 
						usuario.getCidade(), 
						usuario.getUf(), 
						null, 
						null, 
						usuario.getTipoUsuario(), 
						null, 
						null,
						usuario.getDataCadastro(),
						usuario.getPassword()  
				);
			}
		} catch (FeignException.NotFound ex) {
			throw new ObjectNotFoundException("Usuário não encontrado!");
		} catch (Exception e) {
			throw new IllegalArgumentException("Illegal argument exception");
		}
		return null;
	}
	
	private Usuario carregaUsuarioConstrutor(Usuario usuario) {
		if (Objects.nonNull(usuario)) {
			logger.info("Buscando da API de Usuários");
			return new Usuario( 
					usuario.getId(), 
					usuario.getNome(),
					usuario.getEmail(), 
					usuario.getCnpj(), 
					null, 
					null, 
					null, 
					usuario.getCep(), 
					usuario.getEndereco(), 
					usuario.getNumero(), 
					usuario.getBairro(), 
					usuario.getCidade(), 
					usuario.getUf(), 
					null, 
					null, 
					usuario.getTipoUsuario(), 
					null, 
					null,
					usuario.getDataCadastro(),
					usuario.getPassword() 
				);
		}else {
			return CACHE;
		}
	}
}
