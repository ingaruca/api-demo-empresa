package com.everis.empresa.api.services;

import com.everis.empresa.api.entities.Employee;

import java.util.List;

public interface EmployeeService {

  List<Employee> findAll();

  Employee findById(Long id);

  Employee save(Employee employee);

  Employee update(Employee employee);

  void delete(Long id);

}
