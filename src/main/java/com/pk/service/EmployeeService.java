package com.pk.service;

import java.util.List;

import com.pk.dto.EmployeeDto;

public interface EmployeeService {
	
	public EmployeeDto addEmployee(Long depId, EmployeeDto employeeDto); 
	
	public EmployeeDto getEmployeeById(Long depId, Long empId);
	
	public List<EmployeeDto> getAllEmployeesByDepId(Long depId);
	
	public EmployeeDto updateEmployee(Long depId, Long EmpId, EmployeeDto employeeDto);
	
	public void deleteEmployeeById(Long empId);
	

}
