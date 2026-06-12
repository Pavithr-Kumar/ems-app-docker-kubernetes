package com.pk.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pk.entity.Employee;

public interface EmployeeRespository extends JpaRepository<Employee, Long> {
	
	List<Employee> findByDepartmentId(Long depId);

}
