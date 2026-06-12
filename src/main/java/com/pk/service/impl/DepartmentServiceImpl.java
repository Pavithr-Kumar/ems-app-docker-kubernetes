package com.pk.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.pk.dto.DepartmentDto;
import com.pk.entity.Department;
import com.pk.exception.ResourceNotFoundException;
import com.pk.repository.DepartmentRepository;
import com.pk.service.DepartmentService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {

	private DepartmentRepository departmentRepository;
	private ModelMapper modelMapper;

	@Override
	public DepartmentDto addDepartment(DepartmentDto departmentDto) {
		Department department = modelMapper.map(departmentDto, Department.class);
		Department savedDepartment = departmentRepository.save(department);
		return modelMapper.map(savedDepartment, DepartmentDto.class);
	}

	@Override
	public DepartmentDto getDepartMentById(Long id) {
		Department deparment = departmentRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("No Department found with id : " + id));
		return modelMapper.map(deparment, DepartmentDto.class);
	}

	@Override
	public List<DepartmentDto> getAllDepartments() {
		return departmentRepository.findAll().stream().map(dep -> modelMapper.map(dep, DepartmentDto.class))
				.collect(Collectors.toList());
	}

	@Override
	public DepartmentDto updateDepartment(Long id, DepartmentDto departmentDto) {
		Department department = departmentRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("No Department found with id : " + id));
		Department updatedDepartment = modelMapper.map(departmentDto, Department.class);
		updatedDepartment.setId(department.getId());

		return modelMapper.map(departmentRepository.save(updatedDepartment), DepartmentDto.class);
	}

	@Override
	public void deleteDepartmentById(Long id) {
		departmentRepository.deleteById(id);

	}

}
