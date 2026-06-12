package com.pk.dto;

import java.util.List;

import com.pk.entity.Employee;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


	@AllArgsConstructor
	@NoArgsConstructor
	@Getter
	@Setter
	public class DepartmentDto {
		
		
		private Long id;
		
		private String name;
		
		private String description;
		
		private List<EmployeeDto> employees;

	

}
