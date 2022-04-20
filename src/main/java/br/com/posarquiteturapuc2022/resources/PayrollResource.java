//package br.com.alvaro.payrollapi.resources;
//
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import br.com.alvaro.payrollapi.services.PayrollService;
//import br.com.posarquiteturapuc2022.domain.Payroll;
//import lombok.RequiredArgsConstructor;
//
//@RequiredArgsConstructor
//@RestController
//@RequestMapping("/api/payments")
//public class PayrollResource {
//	
//	private final PayrollService payrollService;
//	
//	@GetMapping(value = "/{workerId}")
//	public ResponseEntity<Payroll> getpayment(@PathVariable Long workerId, @RequestBody Payroll payment) {
//		return ResponseEntity.ok().body(payrollService.getPayment(workerId, payment));
//	}
//
//}
