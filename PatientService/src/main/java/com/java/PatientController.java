package com.java;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.BasePathAwareController;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.client.RestTemplate;

import com.java.dao.PatientRepository;
import com.java.dto.Patient;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;

/*It must be @BasePathAwareController. @RepositoryRestController will not override exported handlers, but you can use it to add handlers that aren't exported on the repository.
You must NOT have a @RequestMapping at the type level - it has to be at the method level as above.
The path in your @RequestMapping(path="...") must NOT start with a /
OK: @RequestMapping(path="foos")
NOT OK: @RequestMapping(path="/foos")
You cannot define a standalone mapping like foos/count, because it clashes with foos/{id}. If you need to do this, you must override  foos/{id} and decide what do do based on the {id} path variable content. (e.g. if "count".equals(id) { ... }). This is sort of an antipattern anyway.
You must NOT have any @PreAuthorize annotations on the type or methods - this causes the class to be proxied and prevents the desired behaviour. If you need security rules, implement them in a service layer below the controller, or wait for Spring to fix*/

@BasePathAwareController
public class PatientController  {

	@Autowired PatientRepository rep;
	@Autowired RestTemplate template;
	
	//sleepWindowInMilliseconds: If circuit is open and service is up again, after how much time circuit can be closed
	//timeoutInMilliseconds: If service takes long to respond, go to fallback
	@HystrixCommand(defaultFallback="fallbackForGetPatientDetails",
			commandProperties= {
					@HystrixProperty(name= "circuitBreaker.errorThresholdPercentage", value="10"),
					@HystrixProperty(name= "circuitBreaker.sleepWindowInMilliseconds", value="50000"),
					@HystrixProperty(name= "execution.isolation.thread.timeoutInMilliseconds", value="1")
					
			})
	@GetMapping("/patients")
	public ResponseEntity<?> getPatientDetails() {
		List<Patient> list= rep.findAll();
		if(list!= null && list.size()>0) {
			for(Patient patient : list) {
				HttpHeaders headers= new HttpHeaders();
				headers.setContentType(MediaType.APPLICATION_JSON);
				ResponseEntity<String> response=template.exchange("http://user-service/users/"+patient.getUsername(), HttpMethod.GET, new HttpEntity(headers),String.class);
				System.out.println(response.getBody());
				
			}
		return ResponseEntity.ok().body(rep.findAll());
		}else {
			return ResponseEntity.noContent().build();
		}
	}
	
	@ResponseStatus(code=HttpStatus.SERVICE_UNAVAILABLE, reason="Currently unable to retrieve data!")
	public ResponseEntity<?> fallbackForGetPatientDetails(){
		System.out.println("Fallback");
		List<Patient> list= rep.findAll();
		if(list!= null && list.size()>0) {
			return ResponseEntity.ok().body(rep.findAll());
		}else {
			return ResponseEntity.noContent().build();
		}
	}
}
