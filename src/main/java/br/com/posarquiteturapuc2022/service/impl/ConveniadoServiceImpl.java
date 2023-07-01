package br.com.posarquiteturapuc2022.service.impl;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import br.com.posarquiteturapuc2022.domain.Conveniado;
import br.com.posarquiteturapuc2022.domain.Usuario;
import br.com.posarquiteturapuc2022.exception.ObjectNotFoundException;
import br.com.posarquiteturapuc2022.feignInfoCad.UsuarioApiFeign;
import br.com.posarquiteturapuc2022.repository.ConveniadoRepository;
import br.com.posarquiteturapuc2022.service.ConveniadoService;
import feign.FeignException;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class ConveniadoServiceImpl implements ConveniadoService{
	
	private final Logger logger = LoggerFactory.getLogger(ConveniadoServiceImpl.class);

	private final ConveniadoRepository conveniadoRepository;

	private final UsuarioApiFeign infoCadFeign;
	
	private Usuario CACHE = new Usuario();

	@Override
	public Conveniado findById(UUID id) {
		return conveniadoRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException("Conveniado não encontrado!"));
	}

	@Override
	public List<Conveniado> findAll() {
		return conveniadoRepository.findAll();
	}
	
	@Override
	public Conveniado findByCnpj(String cnpj) {
		return conveniadoRepository.findByCnpj(cnpj).orElseThrow(() -> new ObjectNotFoundException("Conveniado não encontrado pelo cnpj!"));
	}
	
	@Override
	public Conveniado save(Conveniado conveniado) {
		Usuario usuario = buscaUsuarioApiCnpj(conveniado.getCnpjConveniado());
		if (!usuario.getCnpj().equals(conveniado.getCnpjConveniado())) {
			throw new ObjectNotFoundException("Usuário não encontrado!");
		}
		
		conveniado.setNomeConveniado(usuario.getNome());
		conveniado.setEmailConveniado(usuario.getEmail());
		conveniado.setCnpjConveniado(usuario.getCnpj());
		conveniado.setEmailConveniado(usuario.getEmail());
	    conveniado.setTipoUsuario(usuario.getTipoUsuario()); 
	    conveniado.setSituacao(true);
		return conveniadoRepository.save(conveniado);
	}

	@Override
	public Conveniado update(Conveniado conveniado) {
		Usuario usuario = buscaUsuarioApiCnpj(conveniado.getCnpjConveniado());
		
		if (!usuario.getCnpj().equals(conveniado.getCnpjConveniado())) {
			throw new ObjectNotFoundException("Usuário não encontrado!");
		}
		
		conveniado.setNomeConveniado(usuario.getNome());
		conveniado.setEmailConveniado(usuario.getEmail());
		conveniado.setCnpjConveniado(usuario.getCnpj());
		conveniado.setEmailConveniado(usuario.getEmail());
		conveniado.setTipoUsuario(usuario.getTipoUsuario()); 
		conveniado.setSituacao(true);
		return conveniadoRepository.saveAndFlush(conveniado);
	}

	@Override
	public void delete(UUID id) {
		Conveniado conveniado = conveniadoRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException("Conveniado não encontrado!"));
		conveniadoRepository.delete(conveniado);	
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
