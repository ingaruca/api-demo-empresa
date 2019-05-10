package com.everis.empresa.api.services.impl;

import com.everis.empresa.api.entities.Employee;
import com.everis.empresa.api.repositories.EmployeeRepository;
import com.everis.empresa.api.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

  @Autowired
  EmployeeRepository employeeRepository;

  @Override
  public List<Employee> findAll() {
    return employeeRepository.findAll();
  }

  @Override
  public Employee findById(Long id) {
    return employeeRepository.findById(id).orElse(null);
  }

  @Override
  public void save(Employee employee) {
    employeeRepository.save(employee);
  }

  @Override
  public void delete(Long id) {
    employeeRepository.deleteById(id);
  }
}
