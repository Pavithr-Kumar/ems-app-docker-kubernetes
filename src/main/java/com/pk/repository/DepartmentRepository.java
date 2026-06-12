package com.pk.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pk.entity.Department;


public interface DepartmentRepository extends JpaRepository<Department, Long> {

}
