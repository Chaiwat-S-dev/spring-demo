package com.spring.restful.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.restful.model.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
	List<Employee> findByName(String name);
}