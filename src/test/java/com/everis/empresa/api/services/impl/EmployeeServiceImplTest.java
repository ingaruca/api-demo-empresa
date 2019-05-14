package com.everis.empresa.api.services.impl;

import com.everis.empresa.api.entities.Department;
import com.everis.empresa.api.entities.Employee;
import com.everis.empresa.api.exceptions.NotFoundException;
import com.everis.empresa.api.repositories.DepartmentRepository;
import com.everis.empresa.api.repositories.EmployeeRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.BDDMockito.given;

@RunWith(MockitoJUnitRunner.class)
public class EmployeeServiceImplTest {

  @InjectMocks
  private EmployeeServiceImpl employeeService;

  @Mock
  private EmployeeRepository employeeRepository;

  @Mock
  private DepartmentRepository departmentRepository;

  private Employee employee;

  @Test
  public void findAll() {
    List<Employee> employees = new ArrayList<>();
    given(employeeRepository.findAll()).willReturn(employees);
    assertEquals(employeeService.findAll(), employees);
  }

  @Test
  public void findById() {
    Department department = new Department(1L, "Technology", "2B");
    employee = new Employee();
    employee.setName("Luis");
    employee.setSalary(2000.00);
    employee.setTelephone(999888777);
    employee.setDepartment(department);
    employee.setBoss("Yes");
    given(employeeRepository.findById(1L)).willReturn(Optional.of(employee));
    assertEquals(employeeService.findById(1L), employee);
  }

  @Test(expected = NotFoundException.class)
  public void findByIdNotFound() {
    employeeService.findById(1L);
  }

  @Test
  public void save() {
    Department department = new Department(1L, "Technology", "2B");
    employee = new Employee();
    employee.setName("Luis");
    employee.setSalary(2000.00);
    employee.setTelephone(999888777);
    employee.setDepartment(department);
    employee.setBoss("Yes");
    given(departmentRepository.findById(employee.getDepartment().getDepartmentId())).willReturn(Optional.of(department));
    given(employeeRepository.save(employee)).willReturn(employee);
    assertEquals(employeeService.save(employee), employee);
  }

  @Test(expected = NotFoundException.class)
  public void saveNotFoundDepartment() {
    employee = new Employee();
    employee.setName("Luis");
    employee.setSalary(2000.00);
    employee.setTelephone(999888777);
    employee.setDepartment(new Department(1L, "Technology", "2B"));
    employee.setBoss("Yes");
    employeeService.save(employee);
  }

  @Test
  public void update() {
    Department department = new Department(1L, "Technology", "2B");
    employee = new Employee();
    employee.setName("Luis");
    employee.setSalary(2000.00);
    employee.setTelephone(999888777);
    employee.setDepartment(department);
    employee.setBoss("Yes");
    given(employeeRepository.findById(employee.getEmployeId())).willReturn(Optional.of(employee));
    given(departmentRepository.findById(employee.getDepartment().getDepartmentId())).willReturn(Optional.of(department));
    given(employeeRepository.save(employee)).willReturn(employee);
    assertEquals(employeeService.update(employee), employee);
  }

  @Test(expected = NotFoundException.class)
  public void updateNotFound() {
    employee = new Employee();
    employee.setName("Luis");
    employee.setSalary(2000.00);
    employee.setTelephone(999888777);
    employee.setDepartment(new Department(1L, "Technology", "2B"));
    employee.setBoss("Yes");
    employeeService.save(employee);
  }

  @Test
  public void delete() {
    given(employeeRepository.findById(1L)).willReturn(Optional.of(new Employee()));
    employeeService.delete(1L);
  }

  @Test(expected = NotFoundException.class)
  public void deleteNotFound() {
    employeeService.delete(1L);
  }

  @Test
  public void findBySalary() {
    employeeService.findBySalary(2000.00);
  }
}