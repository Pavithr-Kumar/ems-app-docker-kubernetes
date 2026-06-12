package com.pk.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.pk.dto.EmployeeDto;
import com.pk.entity.Department;
import com.pk.entity.Employee;
import com.pk.exception.BadRequestException;
import com.pk.exception.ResourceNotFoundException;
import com.pk.repository.DepartmentRepository;
import com.pk.repository.EmployeeRespository;
import com.pk.service.EmployeeService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

	private DepartmentRepository departmentRepository;
	private EmployeeRespository employeeRespository;
	private ModelMapper modelMapper;

	@Override
	public EmployeeDto addEmployee(Long depId, EmployeeDto employeeDto) {
		Department department = validateAndReturnDepartment(depId);
		Employee employee = modelMapper.map(employeeDto, Employee.class);
		employee.setDepartment(department);

		Employee savedEmployee = employeeRespository.save(employee);

		EmployeeDto savedEmployeeDto = modelMapper.map(savedEmployee, EmployeeDto.class);
		savedEmployeeDto.setDepartmentId(depId);

		return savedEmployeeDto;
	}

	@Override
	public EmployeeDto getEmployeeById(Long depId, Long empId) {
		Department department = validateAndReturnDepartment(depId);
		Employee employee = validateAndReturnEmployee(empId);

		if (!employee.getDepartment().getId().equals(department.getId())) {
			throw new BadRequestException("Employee with id : " + empId + " not belongs to department : " + depId);
		}
		EmployeeDto employeeDto = modelMapper.map(employee, EmployeeDto.class);
		employeeDto.setDepartmentId(depId);
		return employeeDto;
	}

	@Override
	public List<EmployeeDto> getAllEmployeesByDepId(Long depId) {
		Department department = validateAndReturnDepartment(depId);
		List<Employee> employees = employeeRespository.findByDepartmentId(depId);
		return employees.stream().map(emp -> {
			EmployeeDto dto = modelMapper.map(emp, EmployeeDto.class);
			dto.setDepartmentId(department.getId());
			return dto;
		})

				.collect(Collectors.toList());

	}

	@Override
	public EmployeeDto updateEmployee(Long depId, Long empId, EmployeeDto employeeDto) {
		Department department = validateAndReturnDepartment(depId);
		Employee employee = validateAndReturnEmployee(empId);
		if (!employee.getDepartment().getId().equals(department.getId())) {
			throw new BadRequestException("Employee with id : " + empId + " not belongs to department : " + depId);
		}
		
		employee.setEmail(employeeDto.getEmail());
		employee.setFirstName(employeeDto.getFirstName());
		employee.setLastName(employeeDto.getLastName());
		
		Employee updatedEmployee = employeeRespository.save(employee);
		EmployeeDto updatedDto = modelMapper.map(updatedEmployee, EmployeeDto.class);
		return updatedDto;
	}

	@Override
	public void deleteEmployeeById(Long empId) {
		Employee employee = validateAndReturnEmployee(empId);
		employeeRespository.deleteById(employee.getId());
		
	}
	
	private Employee validateAndReturnEmployee(Long empId) {
		return employeeRespository.findById(empId)
				.orElseThrow(() -> new ResourceNotFoundException("No Employee found with id : " + empId));
	}
	
	private Department validateAndReturnDepartment(Long depId) {
		return departmentRepository.findById(depId)
		.orElseThrow(() -> new ResourceNotFoundException("No Department found with id : " + depId));
	}
	
	

}
