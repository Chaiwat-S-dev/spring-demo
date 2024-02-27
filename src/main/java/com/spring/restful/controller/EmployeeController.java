package com.spring.restful.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.spring.restful.execption.EmployeeNotFoundException;
import com.spring.restful.model.Employee;
import com.spring.restful.service.EmployeeService;

@RestController
@RequestMapping("/api")
public class EmployeeController {

	@Autowired
	private final EmployeeService employeeService;

	EmployeeController(EmployeeService employeeService) {
		this.employeeService = employeeService;
	}

	// Aggregate root
	// tag::get-aggregate-root[]
	@GetMapping("/employees")
	public List<Employee> all(
		@RequestParam(name="page", required=false, defaultValue="0") int page, 
		@RequestParam(name="size", required=false, defaultValue="10") int size,
		@RequestParam(name="sort", required=false, defaultValue="id") String sort,
		@RequestParam(name="is_asc", required=false, defaultValue="true") boolean isAsc) {
		
		Pageable pageSort;
		if (isAsc) {
			pageSort = PageRequest.of(page, size, Sort.by(sort).ascending());
		} else {
			pageSort = PageRequest.of(page, size, Sort.by(sort).descending());
		}
		return employeeService.retrieveEmployee(pageSort);
	}

	// end::get-aggregate-root[]

	@PostMapping("/employees")
	public Employee newEmployee(@Valid @RequestBody Employee newEmployee) {
		System.out.print(newEmployee);
		return employeeService.createEmployee(newEmployee);
	}

	// Single item

	@GetMapping("/employees/{id}")
	public Employee one(@PathVariable Long id) {

		return employeeService.retrieveEmployee(id)
				.orElseThrow(() -> new EmployeeNotFoundException(id));
	}

	@GetMapping("/employees/{name}")
	public List<Employee> retrieveEmployee(@PathVariable String name) {
		return employeeService.retrieveEmployee(name);
	}

	@PutMapping("/employees/{id}")
	public Employee replaceEmployee(@RequestBody Employee newEmployee, @PathVariable Long id) {

		Optional<Employee> updateEmployee = employeeService.updateEmployee(id, newEmployee);
		return updateEmployee.orElseThrow(() -> new EmployeeNotFoundException(id));
	}

	@DeleteMapping("/employees/{id}")
	public void deleteEmployee(@PathVariable Long id) {
		employeeService.deleteEmployee(id);
	}
}