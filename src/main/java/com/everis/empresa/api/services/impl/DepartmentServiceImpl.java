package com.everis.empresa.api.services.impl;

import com.everis.empresa.api.entities.Department;
import com.everis.empresa.api.exceptions.NotFoundException;
import com.everis.empresa.api.repositories.DepartmentRepository;
import com.everis.empresa.api.services.DepartmentService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DepartmentServiceImpl implements DepartmentService {

  private final DepartmentRepository departmentRepository;

  @Autowired
  public DepartmentServiceImpl(DepartmentRepository departmentRepository) {
    this.departmentRepository = departmentRepository;
  }

  @Override
  public List<Department> findAll() {
    return departmentRepository.findAll();
  }

  @Override
  public Department findById(Long id) throws NotFoundException {
    return departmentRepository.findById(id)
            .orElseThrow(() -> new NotFoundException("0001", "Could not found department with id " + id));
  }

  @Override
  public void save(Department department) {
    departmentRepository.save(department);
  }

  @Override
  public void update(Department department, Long id) {
    Department currentDepartment = departmentRepository.findById(id)
            .orElseThrow(() -> new NotFoundException("0001", "Could not found department with id " + id));
    currentDepartment.setName(department.getName());
    currentDepartment.setUbication(department.getUbication());
    departmentRepository.save(currentDepartment);
  }

  @Override
  public void delete(Long id) {
    departmentRepository.findById(id)
            .orElseThrow(() -> new NotFoundException("0001", "Could not found department with id " + id));
    departmentRepository.deleteById(id);
  }
}
