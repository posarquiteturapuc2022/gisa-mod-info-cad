package br.com.posarquiteturapuc2022.feignInfoCad;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import br.com.posarquiteturapuc2022.domain.Usuario;

@FeignClient(name = "gisa-user-api", url = "http://localhost:8000/gisa-user-api/v1/api/usuarios")
public interface UsuarioApiFeign {

	@RequestMapping(value = "/cpf/{cpf}", method = RequestMethod.GET)
	ResponseEntity<Usuario> findByCpf(@RequestParam(value="cpf") String cpf);
	
	@RequestMapping(value = "/cnpj/{cnpj}", method = RequestMethod.GET)
	ResponseEntity<Usuario> findByCnpj(@RequestParam(value="cnpj")  String cnpj);
}
