package com.spring.restful.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.spring.restful.model.Employee;
import com.spring.restful.repository.EmployeeRepository;

@Service
public class EmployeeService {
    private EmployeeRepository EmployeeRepository;

    @Autowired
    public EmployeeService(EmployeeRepository repository) {
        this.EmployeeRepository = repository;
    }

    public List<Employee> retrieveEmployee(Pageable page) {
        return EmployeeRepository.findAll(page).toList();
    }
    
    @Cacheable(value = "employee", key = "#id", unless = "#result==null")
    public Optional<Employee> retrieveEmployee(Long id) {
        return EmployeeRepository.findById(id);
    }

    public List<Employee> retrieveEmployee(String name) {
        return EmployeeRepository.findByName(name);
    }

    public Employee createEmployee(Employee Employee) {
        Employee.setId(null);
        return EmployeeRepository.save(Employee);
    }
    
    @CachePut(value = "user", key = "#id")
    public Optional<Employee> updateEmployee(Long id, Employee Employee) {
        Optional<Employee> EmployeeOptional = EmployeeRepository.findById(id);
        if (!EmployeeOptional.isPresent()) {
            return EmployeeOptional;
        }
        Employee.setId(id);
        return Optional.of(EmployeeRepository.save(Employee));
    }
    
    @CacheEvict(value = "user", key = "#id")
    public boolean deleteEmployee(Long id) {
        try {
            EmployeeRepository.deleteById(id);
            return true;
        } catch (EmptyResultDataAccessException e) {
            return false;
        }
    }
}
