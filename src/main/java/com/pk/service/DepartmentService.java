package com.pk.service;

import java.util.List;

import com.pk.dto.DepartmentDto;

public interface DepartmentService {
	
	DepartmentDto addDepartment(DepartmentDto departmentDto) ;
	
	DepartmentDto getDepartMentById(Long id) ;
	
	List<DepartmentDto> getAllDepartments() ;
	
	DepartmentDto updateDepartment(Long id, DepartmentDto departmentDto) ;
	
	void deleteDepartmentById(Long id);



	

}
