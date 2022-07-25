package br.com.posarquiteturapuc2022.services.impl;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import br.com.posarquiteturapuc2022.domain.Associado;
import br.com.posarquiteturapuc2022.domain.Usuario;
import br.com.posarquiteturapuc2022.exception.ObjectNotFoundException;
import br.com.posarquiteturapuc2022.feignIndoCad.UsuarioApiFeign;
import br.com.posarquiteturapuc2022.repository.AssociadoRepository;
import br.com.posarquiteturapuc2022.repository.UsuarioRepository;
import br.com.posarquiteturapuc2022.services.AssociadoService;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Slf4j
@Service
public class AssociadoServiceImpl implements AssociadoService {

	private final UsuarioRepository usuarioRepository;
	
	private final AssociadoRepository associadoRepository;
	
	private final Environment env;

	private final UsuarioApiFeign infoCadFeign;
	
	@Override
	public Associado findById(UUID id) {
		return associadoRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException("Associado não encontrado!"));
	}
	
	@Override
	public Associado findByCpf(String cpf) {
		return associadoRepository.findByCpf(cpf).orElseThrow(() -> new ObjectNotFoundException("Associado não encontrado!"));
	}

	@Override
	public List<Associado> findAll() {
		return associadoRepository.findAll();
	}
	
	@Override
	public Associado save(Associado associado) {
		Usuario usuario = buscaUsuarioApiCpf(associado.getCpfAssociado());
		
		if (!usuario.getCpf().equals(associado.getCpfAssociado())) {
			throw new ObjectNotFoundException("Usuário não encontrado!");
		}
		
		usuarioRepository.save(usuario);
		associado.setNomeAssociado(usuario.getNome());
		associado.setEmailAssociado(usuario.getEmail());
		associado.setCpfAssociado(usuario.getCpf());
		associado.setIdade(idade(usuario.getDataNascimento()));
		return associadoRepository.save(associado);
	}

	@Override
	public Associado update(Associado associado) {
		Usuario usuario = buscaUsuarioApiCpf(associado.getCpfAssociado());
		
		if (!usuario.getCpf().equals(associado.getCpfAssociado())) {
			throw new ObjectNotFoundException("Usuário não encontrado!");
		}
		
		usuarioRepository.saveAndFlush(usuario);
		associado.setNomeAssociado(usuario.getNome());
		associado.setEmailAssociado(usuario.getEmail());
		associado.setCpfAssociado(usuario.getCpf());
		associado.setIdade(idade(usuario.getDataNascimento()));
		return associadoRepository.save(associado);
	}
	
	@Override
	public void delete(UUID id) {
		Associado associado = associadoRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException("Associado não encontrado!"));
		associadoRepository.delete(associado);
	}
	
	public static int idade(final LocalDate aniversario) {
	    final LocalDate dataAtual = LocalDate.now();
	    final Period periodo = Period.between(aniversario, dataAtual);
	    return periodo.getYears();
	}
	
	public Usuario buscaUsuarioApiCpf(String cpf) {
		log.info("USUARIO_SERVICE ::: Get request on " + env.getProperty("local.server.port") + " port");
		try {
			Usuario usuario = infoCadFeign.findByCpf(cpf).getBody();

			if (Objects.nonNull(usuario)) {
				return new Usuario(
						usuario.getId(), 
						usuario.getNome(),
						usuario.getEmail(), 
						usuario.getCpf(), 
						null, 
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
			System.out.println(e.getCause()+"\n"+e.getMessage()); 
			
			throw new IllegalArgumentException("Illegal argument exception");
		}
		return null;
	}

}
