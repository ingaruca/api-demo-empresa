package com.everis.empresa.api.controllers;

import com.everis.empresa.api.entities.Employee;
import com.everis.empresa.api.services.EmployeeService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmployeeController {

  private final EmployeeService employeeService;

  @Autowired
  public EmployeeController(final EmployeeService employeeService) {
    this.employeeService = employeeService;
  }

  @GetMapping("/employees")
  public List<Employee> getAll() {
    return employeeService.findAll();
  }

  @GetMapping("/employees/{id}")
  public Employee findById(@PathVariable  Long id) {
    return employeeService.findById(id);
  }

  @PostMapping("/employees")
  @ResponseStatus(HttpStatus.CREATED)
  public void create(@RequestBody Employee employee) {
    employeeService.save(employee);
  }

  @PutMapping("/employees")
  public void update(@RequestBody Employee employee) {
    employeeService.update(employee);
  }

  @DeleteMapping("/employees/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void delete(@PathVariable Long id) {
    employeeService.delete(id);
  }

}
