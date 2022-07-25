package br.com.posarquiteturapuc2022.services.impl;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import br.com.posarquiteturapuc2022.domain.Especialidade;
import br.com.posarquiteturapuc2022.domain.Prestador;
import br.com.posarquiteturapuc2022.domain.Usuario;
import br.com.posarquiteturapuc2022.domain.enums.TipoPrestador;
import br.com.posarquiteturapuc2022.exception.ObjectNotFoundException;
import br.com.posarquiteturapuc2022.feignIndoCad.UsuarioApiFeign;
import br.com.posarquiteturapuc2022.repository.PrestadorRepository;
import br.com.posarquiteturapuc2022.repository.UsuarioRepository;
import br.com.posarquiteturapuc2022.services.PrestadorService;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Slf4j
@Service
public class PrestadorServiceImpl implements PrestadorService{

	private final UsuarioRepository usuarioRepository;
	
	private final PrestadorRepository prestadorRepository;

	private final Environment env;
	
	private final UsuarioApiFeign infoCadFeign;

	@Override
	public Prestador findById(UUID id) {
		return prestadorRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException("Associado não encontrado!"));
	}

	@Override
	public List<Prestador> findAll() {
		return prestadorRepository.findAll();
	}
	
	@Override
	public Prestador findByCnpj(String cnpj) {
		return prestadorRepository.findByCnpj(cnpj).orElseThrow(() -> new ObjectNotFoundException("Associado não encontrado!"));
	}
	
	@Override
	public Prestador save(Prestador prestador) {
	    Especialidade especialidade = new Especialidade(); 
		Usuario usuario = buscaUsuarioApiCnpj(prestador.getCnpjPrestador());
		
		if (!usuario.getCnpj().equals(prestador.getCnpjPrestador())) {
			throw new ObjectNotFoundException("Usuário não encontrado!");
		}
		
		usuarioRepository.save(usuario);
		
		prestador.setNomePrestador(usuario.getNome());
		prestador.setEmailPrestador(usuario.getEmail());
		prestador.setCnpjPrestador(usuario.getCnpj());
		prestador.setResponsavelPrestador(usuario.getNome());
	    prestador.setEspecialidade(especialidade);
	    prestador.setTipoPrestador(TipoPrestador.MEDICO); 
	    prestador.setSituacao(true);;
		return prestadorRepository.save(prestador);
	}

	@Override
	public Prestador update(Prestador prestador) {
		Especialidade especialidade = new Especialidade(); 
		if (prestador.getEspecialidade().getConsulta().getId() != null) {
			especialidade = geradorConsulta();
		}else {
			especialidade = geradorCirurgia();
		}
		
		Usuario usuario = buscaUsuarioApiCnpj(prestador.getCnpjPrestador());
		
		if (!usuario.getCnpj().equals(prestador.getCnpjPrestador())) {
			throw new ObjectNotFoundException("Usuário não encontrado!");
		}
		
		usuarioRepository.save(usuario);
		
		prestador.setNomePrestador(usuario.getNome());
		prestador.setEmailPrestador(usuario.getEmail());
		prestador.setCnpjPrestador(usuario.getCnpj());
		prestador.setResponsavelPrestador(usuario.getNome());
		prestador.setEspecialidade(especialidade);
		prestador.setTipoPrestador(TipoPrestador.MEDICO); 
		prestador.setSituacao(true);;
		return prestadorRepository.saveAndFlush(prestador);
	}

	@Override
	public void delete(UUID id) {
		Prestador prestador = prestadorRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException("Associado não encontrado!"));
		prestadorRepository.delete(prestador);	
	}
	

	private Especialidade geradorCirurgia() {
		// TODO Auto-generated method stub
		return null;
	}

	private Especialidade geradorConsulta() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public Usuario buscaUsuarioApiCnpj(String cnpj) {
		log.info("USUARIO_SERVICE ::: Get request on " + env.getProperty("local.server.port") + " port");
		try {
			var usuario = infoCadFeign.findByCnpj(cnpj).getBody();
			
			if (Objects.nonNull(usuario)) {
				return new Usuario(
						usuario.getId(), 
						usuario.getNome(),
						usuario.getEmail(), 
						null, 
						usuario.getCnpj(), 
						usuario.getNumeroSUS(), 
						usuario.getCidade(), 
						usuario.getUf(), 
						usuario.getDataNascimento(),
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
	
	
}
