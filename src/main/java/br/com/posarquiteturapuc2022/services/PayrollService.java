//package br.com.alvaro.payrollapi.services;
//
//import java.util.Objects;
//
//import org.springframework.core.env.Environment;
//import org.springframework.stereotype.Service;
//
//import br.com.alvaro.payrollapi.domain.Payroll;
//import br.com.alvaro.payrollapi.feignClients.UserFeign;
//import br.com.alvaro.payrollapi.services.exceptions.ObjectNotFoundException;
//import feign.FeignException;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//
//@RequiredArgsConstructor
//@Slf4j
//@Service
//public class PayrollService {
//
//	private final Environment env;
//
//	private final UserFeign userFeign;
//
//	public Payroll getPayment(Long workerId, Payroll payroll) {
//		log.info("PAYROLL_SERVICE ::: Get request on " + env.getProperty("local.server.port") + " port");
//		try {
//			var user = userFeign.findById(workerId).getBody();
//
//			if (Objects.nonNull(user)) {
//				return new Payroll(
//						user.getNome(), 
//						payroll.getDescription(), 
//						user.getHourlyPrice(), 
//						payroll.getWorkedHours(), 
//						payroll.getWorkedHours() * user.getHourlyPrice()
//				);
//			}
//		} catch (FeignException.NotFound ex) {
//			throw new ObjectNotFoundException("Object not found");
//		} catch (Exception e) {
//			throw new IllegalArgumentException("Illegal argument exception");
//		}
//		return null;
//	}
//
//}
