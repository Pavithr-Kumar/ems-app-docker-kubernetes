package com.pk.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pk.dto.EmployeeDto;
import com.pk.service.EmployeeService;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/api/departments")
public class EmployeeController {

	private EmployeeService employeeService;

	@PostMapping("/{depId}/employees")
	public ResponseEntity<EmployeeDto> addEmployee(@PathVariable Long depId, @RequestBody EmployeeDto employeeDto) {

		EmployeeDto savedDto = employeeService.addEmployee(depId, employeeDto);

		return new ResponseEntity<EmployeeDto>(savedDto, HttpStatus.CREATED);

	}

	@GetMapping("/{depId}/employees/{employeeId}")
	public ResponseEntity<EmployeeDto> getEmployeeById(@PathVariable Long depId,
			@PathVariable(name = "employeeId") Long empId) {
		return ResponseEntity.ok(employeeService.getEmployeeById(depId, empId));
	}
	
	@GetMapping("/{depId}/employees")
	public ResponseEntity<List<EmployeeDto>> getAllEmployeesByDep(@PathVariable Long depId) {
		return ResponseEntity.ok(employeeService.getAllEmployeesByDepId(depId));
	}
	
	@PutMapping("/{depId}/employees/{employeeId}")
	public ResponseEntity<EmployeeDto> updateEMployee(@PathVariable Long depId,
			@PathVariable(name = "employeeId") Long empId, @RequestBody EmployeeDto employeeDto) {
		return ResponseEntity.ok(employeeService.updateEmployee(depId, empId, employeeDto));
	}
	
	@DeleteMapping("/{depId}/employees/{employeeId}")
	public ResponseEntity<String> deleteEmployeeById(@PathVariable(name = "employeeId") Long empId){
		employeeService.deleteEmployeeById(empId);
		return ResponseEntity.ok("Employee deleted successfully");
	}
	
	

}
