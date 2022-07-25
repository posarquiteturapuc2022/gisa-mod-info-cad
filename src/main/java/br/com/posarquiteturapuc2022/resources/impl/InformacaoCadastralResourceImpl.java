//package br.com.posarquiteturapuc2022.resources.impl;
//
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import br.com.posarquiteturapuc2022.resources.InformacaoCadastralResource;
//import br.com.posarquiteturapuc2022.services.AssociadoService;
//import br.com.posarquiteturapuc2022.services.impl.PrestadorServiceImpl;
//import lombok.RequiredArgsConstructor;
//
//@RequiredArgsConstructor
//@RestController
//@RequestMapping("/api/informacoes-cadastrais")
//public class InformacaoCadastralResourceImpl implements InformacaoCadastralResource{
//	
//	private final AssociadoService associadosService;
//	
//	private final PrestadorServiceImpl prestadoresService;
//	
//	/* PRESTADORES */
////	@PostMapping(value = "/cadastro/prestador")
////	public ResponseEntity<Usuario> getCadastraPrestadoresUsuario(@RequestBody Usuario usuario) {
////		return ResponseEntity.ok().body(prestadoresService.getCadastraPrestadoresUsuario(usuario));
////	}
////
////	@GetMapping(value = "/consulta/prestadores/{pretadorId}")
////	public ResponseEntity<Usuario> getConsultaPrestadoresUsuario(@PathVariable Long workerId) {
////		return ResponseEntity.ok().body(prestadoresService.getConsultaUsuario(workerId));
////	}
////	
////	@GetMapping(value = "/lista/prestadores")
////	public ResponseEntity<List<Usuario>> findAllPrestadores() {
////		ResponseEntity<List<Usuario>> list = prestadoresService.getListaPrestadorUsuario();
////		return list;
////	}
////
////	/* ASSOCIADOS */
////	@PostMapping(value = "/cadastro/associado")
////	public ResponseEntity<Usuario> getCadastraAssociadosUsuario(@RequestBody Usuario usuario) {
////		return ResponseEntity.ok().body(associadosService.getCadastraAssociadosUsuario(usuario));
////	}
////	
////	@GetMapping(value = "/consulta/associados/{associadoId}")
////	public ResponseEntity<Usuario> getConsultaAssociadosUsuario(@PathVariable Long workerId) {
////		return ResponseEntity.ok().body(associadosService.getConsultaUsuario(workerId));
////	}
////	
////	
////	@GetMapping(value = "/lista/associados")
////	public ResponseEntity<List<Usuario>> findAllAssociados() {
////		ResponseEntity<List<Usuario>> list = associadosService.getListaAssociados();
////		return list;
////	}
//
////	@Override
////	public ResponseEntity<Associado> criarUsuarioAssociado(Associado associado) {
////		// TODO Auto-generated method stub
////		return null;
////	}
////
////	@Override
////	public ResponseEntity<Associado> findById(Long id) {
////		// TODO Auto-generated method stub
////		return null;
////	}
////
////	@Override
////	public ResponseEntity<List<Associado>> findAll() {
////		// TODO Auto-generated method stub
////		return null;
////	}
//	
////	@GetMapping(value = "/{workerId}")
////	public ResponseEntity<Payroll> getpayment(@PathVariable Long workerId, @RequestBody Payroll payment) {
////		return ResponseEntity.ok().body(prestadoresService.getPayment(workerId, payment));
////	}
//	
////	@PostMapping
////	public ResponseEntity<UserDTO> create(@Valid @RequestBody UserDTO objDTO) {
////		Users newObj = service.create(objDTO);
////		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newObj.getId()).toUri();
////		return ResponseEntity.created(uri).build();
////	}
////
////	@PreAuthorize("hasAnyRole('ADMIN')")
////	@PutMapping(value = "/{id}")
////	public ResponseEntity<UserDTO> update(@PathVariable Integer id,
////			@Valid @RequestBody UserDTO objDTO) {
////		Users obj = service.update(id, objDTO);
////		return ResponseEntity.ok().body(new UserDTO(obj));
////	}
////
////	@PreAuthorize("hasAnyRole('ADMIN')")
////	@DeleteMapping(value = "/{id}")
////	public ResponseEntity<UserDTO> delete(@PathVariable Integer id) {
////		service.delete(id);
////		return ResponseEntity.noContent().build();
////	}
//
//}
