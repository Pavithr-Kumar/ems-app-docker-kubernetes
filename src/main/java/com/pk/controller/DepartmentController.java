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

import com.pk.dto.DepartmentDto;
import com.pk.service.DepartmentService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("api/departments")
@AllArgsConstructor
public class DepartmentController {

	private DepartmentService departmentService;

	@PostMapping()
	public ResponseEntity<DepartmentDto> addDepartment(@RequestBody DepartmentDto departmentDto) {
		DepartmentDto savedDepDto = departmentService.addDepartment(departmentDto);

		return new ResponseEntity<DepartmentDto>(savedDepDto, HttpStatus.CREATED);
	}

	@GetMapping("/{id}")
	public ResponseEntity<DepartmentDto> getDepartMentById(@PathVariable(name = "id") Long depId) {
		return ResponseEntity.ok(departmentService.getDepartMentById(depId));

	}
	
	@GetMapping()
	public ResponseEntity<List<DepartmentDto>> getAllDepartments(){
		return ResponseEntity.ok(departmentService.getAllDepartments());
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<DepartmentDto> updatDepartmentById(@PathVariable(name = "id") Long depId,@RequestBody DepartmentDto departmentDto){
		return ResponseEntity.ok(departmentService.updateDepartment(depId, departmentDto));
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteDepartmentById(@PathVariable(name = "id") Long depId){
		departmentService.deleteDepartmentById(depId);
		
		return ResponseEntity.ok("Department deleted successfully");
	}
	
	

}
