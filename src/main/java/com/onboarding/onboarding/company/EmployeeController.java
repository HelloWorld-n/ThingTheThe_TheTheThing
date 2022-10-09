package com.onboarding.onboarding.company;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.onboarding.onboarding.SqlConnection;
import com.onboarding.onboarding.PageUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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

import java.sql.SQLException;

@CrossOrigin(origins = "http://[::1]:8080")
@RestController
@RequestMapping("/employeeUtil")
public class EmployeeController {

	@Autowired(required = false)
	private EmployeeRepository employeeRepository;

	private static EmployeeController employeeController = null;

	private EmployeeController(){
	}

	public static EmployeeController create(){
		if (employeeController == null){
			employeeController = new EmployeeController();
		}
		return employeeController;
	}

	@GetMapping("/")
	public String index(ModelMap model) {
		EmployeeController employeeController = EmployeeController.create();
		return PageUtil.fetchAllPages(EmployeeController.class, model);
	}

	@GetMapping("/fetch")
	public List<Employee> getAllEmployees(){
		return employeeRepository.findAll();
	}		
	
	@PostMapping("/wr")
	public Employee createEmployee(@RequestBody Employee employee) {
		return employeeRepository.save(employee);
	}
	
	@GetMapping("/fetch/{id}")
	public ResponseEntity<Employee> getEmployeeById(@PathVariable Long id) {
		Employee employee = employeeRepository.findById(id).orElseThrow(
			() -> new ResourceNotFoundException("Employee not exist with id :" + id)
		);
		return ResponseEntity.ok(employee);
	}
	
	@PutMapping("/update/{id}")
	public ResponseEntity<Employee> updateEmployee(@PathVariable Long id, @RequestBody Employee employeeDetails){
		Employee employee = employeeRepository.findById(id).orElseThrow(
			() -> new ResourceNotFoundException("Employee not exist with id :" + id)
		);
		
		employee.setFirstName(employeeDetails.getFirstName());
		employee.setLastName(employeeDetails.getLastName());
		employee.setEmailId(employeeDetails.getEmailId());
		
		Employee updatedEmployee = employeeRepository.save(employee);
		return ResponseEntity.ok(updatedEmployee);
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Map<String, Boolean>> deleteEmployee(@PathVariable Long id){
		Employee employee = employeeRepository.findById(id).orElseThrow(
			() -> new ResourceNotFoundException("Employee not exist with id :" + id)
		);
		
		employeeRepository.delete(employee);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return ResponseEntity.ok(response);
	}

	@GetMapping("/error")
	public String page__error(ModelMap Model) {
		String result = "[\"ERROR\"]";
		return result;
	}	
}
