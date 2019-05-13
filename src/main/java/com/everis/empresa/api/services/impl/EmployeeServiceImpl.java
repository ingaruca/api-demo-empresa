package com.everis.empresa.api.services.impl;

import com.everis.empresa.api.entities.Employee;
import com.everis.empresa.api.exceptions.NotFoundException;
import com.everis.empresa.api.repositories.DepartmentRepository;
import com.everis.empresa.api.repositories.EmployeeRepository;
import com.everis.empresa.api.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

  private final EmployeeRepository employeeRepository;

  private final DepartmentRepository departmentRepository;

  @Autowired
  public EmployeeServiceImpl(EmployeeRepository employeeRepository, DepartmentRepository departmentRepository) {
    this.employeeRepository = employeeRepository;
    this.departmentRepository = departmentRepository;
  }

  @Override
  public List<Employee> findAll() {
    return employeeRepository.findAll();
  }

  @Override
  public Employee findById(Long id) throws NotFoundException {
    return employeeRepository.findById(id)
            .orElseThrow(() ->
                    new NotFoundException("0001", "Could not found employee with id " + id));
  }

  @Override
  public Employee save(Employee employee) throws NotFoundException {
    Long departmentId = employee.getDepartment().getDepartmentId();
    departmentRepository.findById(departmentId)
            .orElseThrow(() ->
                    new NotFoundException("0001", "Could not found department id " + departmentId));
    return employeeRepository.save(employee);
  }

  @Override
  public Employee update(Employee employee) {
    Long employeeId = employee.getEmployeId();
    Employee currentEmployee = employeeRepository.findById(employeeId)
            .orElseThrow(() ->
                    new NotFoundException("0001", "Could not found employee with id " + employeeId));
    Long departmentId = employee.getDepartment().getDepartmentId();
    departmentRepository.findById(departmentId)
            .orElseThrow(() ->
                    new NotFoundException("0001", "Could not found department id " + departmentId));
    currentEmployee.setName(employee.getName());
    currentEmployee.setSalary(employee.getSalary());
    currentEmployee.setTelephone(employee.getTelephone());
    currentEmployee.setDepartment(employee.getDepartment());
    currentEmployee.setBoss(employee.getBoss());
    return employeeRepository.save(currentEmployee);
  }

  @Override
  public void delete(Long id) {
    employeeRepository.findById(id)
            .orElseThrow(() -> new NotFoundException("0001", "Could not found employee with id " + id));
    employeeRepository.deleteById(id);
  }
}
