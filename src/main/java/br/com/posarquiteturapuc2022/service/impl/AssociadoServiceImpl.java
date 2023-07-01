package br.com.posarquiteturapuc2022.service.impl;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import br.com.posarquiteturapuc2022.domain.Associado;
import br.com.posarquiteturapuc2022.domain.Usuario;
import br.com.posarquiteturapuc2022.domain.enums.TipoUsuario;
import br.com.posarquiteturapuc2022.exception.ObjectNotFoundException;
import br.com.posarquiteturapuc2022.exception.UsuarioCadastradoException;
import br.com.posarquiteturapuc2022.exception.UsuarioFeignNotFoundException;
import br.com.posarquiteturapuc2022.feignInfoCad.UsuarioApiFeign;
import br.com.posarquiteturapuc2022.repository.AssociadoRepository;
import br.com.posarquiteturapuc2022.service.AssociadoService;
import feign.FeignException;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class AssociadoServiceImpl implements AssociadoService {
	
	private final Logger logger = LoggerFactory.getLogger(AssociadoServiceImpl.class);

	private final AssociadoRepository associadoRepository;
	
	private final UsuarioApiFeign infoCadFeign;
	
	private Usuario CACHE = new Usuario();
	
	@Override
	public Associado findById(UUID id) {
		return associadoRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException("Associado não encontrado!"));
	}
	
	@Override
	public Associado findByCpf(String cpf) {
		return associadoRepository.findByCpf(cpf).orElseThrow(() -> new ObjectNotFoundException("Associado não encontrado pelo cpf!"));
	}

	@Override
	public List<Associado> findAll() {
		return associadoRepository.findAll();
	}
	
	@Override
	public Associado save(Associado associado) {

		boolean flagAssociado = associadoRepository.findByCpf(associado.getCpfAssociado()).isPresent();
//		try {
			if (!flagAssociado) {
				Usuario usuario = buscaUsuarioApiCpf(associado.getCpfAssociado());
				
				if (!usuario.getCpf().equals(associado.getCpfAssociado())) {
					throw new ObjectNotFoundException("Usuário não encontrado!");
				}
				
				associado.setNomeAssociado(usuario.getNome());
				associado.setEmailAssociado(usuario.getEmail());
//				if (usuario.getCpf() != null 
//						&& (usuario.getTipoUsuario().getCodigo().equals(TipoUsuario.ASSOCIADO.getCodigo()) 
//								|| usuario.getTipoUsuario().getCodigo().equals(TipoUsuario.PRESTADOR.getCodigo()))) {
//				}
				associado.setTipoUsuario(usuario.getTipoUsuario());
				associado.setCpfAssociado(usuario.getCpf());
				associado.setIdade(idade(usuario.getDataNascimento()));
				return associadoRepository.save(associado);
			}
			
//		} catch (Exception e) {
//			logger.info("Associado já cadastrado!");
//			throw new UsuarioCadastradoException("Associado já cadastrado!");
//		}
		logger.info("Associado já cadastrado!");
		throw new UsuarioCadastradoException("Associado já cadastrado!");
	}

	@Override
	public Associado update(Associado associado) {
		Usuario usuario = buscaUsuarioApiCpf(associado.getCpfAssociado());
		
		if (!usuario.getCpf().equals(associado.getCpfAssociado())) {
			throw new ObjectNotFoundException("Usuário não encontrado!");
		}
		
		associado.setNomeAssociado(usuario.getNome());
		associado.setEmailAssociado(usuario.getEmail());
		if (usuario.getCpf() != null && usuario.getTipoUsuario().equals(TipoUsuario.ASSOCIADO)) {
			associado.setTipoUsuario(usuario.getTipoUsuario());
		}
		associado.setCpfAssociado(usuario.getCpf());
		associado.setIdade(idade(usuario.getDataNascimento()));
		try {
			return associadoRepository.saveAndFlush(associado);
		} catch (Exception e) {
			throw new UsuarioCadastradoException("Usuário já cadastrado no sistema!!");
		}
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
	
	@CircuitBreaker(name = "associadoCB", fallbackMethod = "buscaUsuarioApiCpfNoCACHE")
	public Usuario buscaUsuarioApiCpf(String cpf) {
		Usuario usuario = executarRequisicao(cpf);		
		return usuario;		
	}

	private Usuario executarRequisicao(String cpf) {
		final Usuario usuario;
		try {
			logger.info("Buscando da API de Usuários");
			usuario = infoCadFeign.findByCpf(cpf).getBody();
			logger.info("Alimentando CACHE");
			CACHE = usuario;
		} catch (FeignException.NotFound ex) {
			logger.error("Erro ao buscar novos Usuários!");
			throw new UsuarioFeignNotFoundException("Usuário não encontrado na API de Usuários!");
		}
		logger.info("Erro ao buscar Usuários!");
		return carregaUsuarioConstrutor(usuario);
	}

	private Usuario carregaUsuarioConstrutor(Usuario usuario) {
		if (Objects.nonNull(usuario)) {
			logger.info("Buscando da API de Usuários");
			return new Usuario( 
					usuario.getId(), 
					usuario.getNome(), 
					usuario.getEmail(), 
					null, 
					usuario.getCpf(), 
					usuario.getNumeroSUS(),
					usuario.getSexo(), 
					usuario.getCep(), 
					usuario.getEndereco(), 
					usuario.getNumero(), 
					usuario.getBairro(), 
					usuario.getCidade(), 
					usuario.getUf(), 
					usuario.getEstadoCivil(), 
					usuario.getNivelFormacao(), 
					usuario.getTipoUsuario(), 
					usuario.getProfissao(), 
					usuario.getDataNascimento(), 
					usuario.getDataCadastro(), 
					usuario.getPassword() 
				);
		}else {
			return CACHE;
		}
	}
	
	private Usuario buscaUsuarioApiCpfNoCACHE(String cpf, Throwable e){
		logger.info("Buscando Usuário no CACHE");
		return CACHE;
	}
}
