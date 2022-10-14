package com.onboarding.onboarding.company;

import com.onboarding.onboarding.SqlConnection;
import com.onboarding.onboarding.PageUtil;

import java.lang.reflect.Method;
import java.net.URLDecoder;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.validation.Valid; 

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@Component
@ComponentScan("com.onboarding.onboaring.company")
@CrossOrigin(origins = "http://[::1]:8080")
@RestController
@RequestMapping("/employee")
public class EmployeeController {

	private EmployeeRepository employeeRepository;

	private static EmployeeController employeeController = null;

	private EmployeeController(){
	}

	private EmployeeController(EmployeeRepository employeeRepository){
		this.employeeRepository = employeeRepository;
	}

	@Autowired
	public static EmployeeController create(){
		if (employeeController == null) {
			employeeController = new EmployeeController();
		}
		return employeeController;
	}

	@GetMapping(value = "/", headers = "Accept=application/json")
	public String index(ModelMap model) {
		return PageUtil.fetchAllPages(EmployeeController.class, model);
	}
 	
	@GetMapping(value = "/find")
	public List<Employee> findAllEmployees(){
		return EmployeeUtil.findAll();
	}		

	@GetMapping(value = "/find'")
	public List<Employee> findAllEmployees_viaSpringboot(){
		return this.employeeRepository.findAll();
	}
	
	@GetMapping(value = "/find/{id}")
	public ResponseEntity<Employee> findEmployeeById(@PathVariable Long id) {
		Employee employee = EmployeeUtil.findById(id);
		return ResponseEntity.ok(employee);
	}
	
	@PostMapping(value = "/create")
	public Employee createEmployee(@RequestBody Employee employee) {
		/*
		return EmployeeUtil.save(employee);
		*/
		System.out.print(this.employeeRepository);
		return this.employeeRepository.save(employee);
	}
	
	
	@PostMapping(value = "/create'")
	public Employee createEmployee(@RequestBody String employeeInfo) {
		String firstName = "";
		String lastName = "";
		String emailId = "";
		for (String thing : employeeInfo.split("&")){
			switch(thing.split("=")[0]){
				case "first_name":
					firstName = URLDecoder.decode(thing.split("=")[1]);
				break;
				case "last_name":
					lastName = URLDecoder.decode(thing.split("=")[1]);
				break;
				case "email_id":
					emailId = URLDecoder.decode(thing.split("=")[1]);
				break;
			}
		}
		
		return EmployeeUtil.save(new Employee(firstName, lastName, emailId));
	}
	
	
	
	
	
	@PutMapping("/update/{id}")
	public ResponseEntity<Employee> updateEmployee(
		@PathVariable Long id,
		@RequestBody Employee employeeDetails
	){
		Employee employee = this.employeeRepository.findById(id).orElseThrow(
			() -> new ResourceNotFoundException("Employee not exist with id :" + id)
		);
		
		employee.setFirstName(employeeDetails.getFirstName());
		employee.setLastName(employeeDetails.getLastName());
		employee.setEmailId(employeeDetails.getEmailId());
		
		Employee updatedEmployee = this.employeeRepository.save(employee);
		return ResponseEntity.ok(updatedEmployee);
	}

	@PostMapping("/update'")
	public ResponseEntity<Employee> updateEmployee(@RequestBody String employeeInfo){
		Long id = new Long(0);
		String firstName = "";
		String lastName = "";
		String emailId = "";
		for (String thing : employeeInfo.split("&")){
			switch(thing.split("=")[0]){
				case "id":
					id = new Long(URLDecoder.decode(thing.split("=")[1]));
				break;
				case "first_name":
					firstName = URLDecoder.decode(thing.split("=")[1]);
				break;
				case "last_name":
					lastName = URLDecoder.decode(thing.split("=")[1]);
				break;
				case "email_id":
					emailId = URLDecoder.decode(thing.split("=")[1]);
				break;
			}
		}
		Employee employee = EmployeeUtil.findById(id);
		if (! firstName.equals("")){
			employee.setFirstName(firstName);
		}
		if (! lastName.equals("")){
			employee.setLastName(lastName);
		}
		if (! emailId.equals("")){
			employee.setEmailId(emailId);
		}
		return ResponseEntity.ok(EmployeeUtil.update(employee));
	}

	@GetMapping("/delete/{id}")
	public ResponseEntity<Map<String, Boolean>> deleteEmployee(@PathVariable Long id){
		EmployeeUtil.delete(id);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return ResponseEntity.ok(response);
	}

	@GetMapping(value = "/error")
	public String page__error(ModelMap Model) {
		String result = "[\"ERROR\"]";
		return result;
	}	
}
